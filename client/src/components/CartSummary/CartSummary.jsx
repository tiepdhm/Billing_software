import React, { useContext, useState } from "react";
import "./CartSummary.css";
import { AppContext } from "../../context/AppContext";
import ReceiptPopup from "../ReceiptPopup/ReceiptPopup";
import { createOrder, deleteOrder } from "../../Service/OrderService";
import { toast } from "react-hot-toast";
import { createStripeOrder, verifyPayment } from "../../Service/PaymentService"; // backend Stripe
import { AppConstants } from "../../util/constants";
import { loadStripe } from "@stripe/stripe-js";
import { CardElement, useStripe, useElements } from "@stripe/react-stripe-js";

const stripePromise = loadStripe(AppConstants.STRIPE_PUBLIC_KEY);

const CartSummary = ({
                         customerName,
                         mobileNumber,
                         setMobileNumber,
                         setCustomerName,
                     }) => {
    const { cartItems, clearCart } = useContext(AppContext);
    const totalAmount = cartItems.reduce(
        (total, item) => total + item.price * item.quantity,
        0
    );

    const tax = totalAmount * 0.01;
    const grandTotal = totalAmount + tax;

    const [isProcessing, setIsProcessing] = useState(false);
    const [orderDetails, setOrderDetails] = useState(null);
    const [showPopup, setShowPopup] = useState(false);

    const stripe = useStripe();
    const elements = useElements();

    const [showStripeModal, setShowStripeModal] = useState(false);

    // Xóa order khi lỗi
    const deleteOrderOnFailure = async (orderId) => {
        try {
            await deleteOrder(orderId);
        } catch (error) {
            console.log(error);
            toast.error("Something went wrong");
        }
    };

    const clearAll = () => {
        setCustomerName("");
        setMobileNumber("");
        clearCart();
    };

    const placeOrder = () => {
        setShowPopup(true);
        clearAll();
    };

    const handlePrintReceipt = () => {
        window.print();
    };

    const verifyStripePayment = async (orderId, paymentIntentId) => {
        try {
            const paymentResponse = await verifyPayment({
                orderId,
                paymentIntentId,
            });
            if (paymentResponse.status !== 200) {
                toast.error("Payment verification failed");
            }
        } catch (error) {
            toast.error("Payment verification failed");
            console.log(error);
        }
    };

    // ------------------ Main Payment Flow ------------------
    const completePayment = async (paymentMode) => {
        if (!customerName || !mobileNumber) {
            toast.error("Please enter customer details to make payments");
            return;
        }

        if (cartItems.length === 0) {
            toast.error("Your cart is empty");
            return;
        }

        const orderData = {
            customerName,
            phoneNumber: mobileNumber,
            cartItems,
            subtotal: totalAmount,
            tax,
            grandTotal,
            paymentMethod: paymentMode.toUpperCase(),
        };

        setIsProcessing(true);
        try {
            const response = await createOrder(orderData);
            const savedData = response.data;

            // ---- CASH ----
            if (response.status === 201 && paymentMode === "cash") {
                toast.success("Cash Received");
                setOrderDetails(savedData);
                return;
            }

            // ---- CARD / STRIPE ----
            if (response.status === 201 && paymentMode === "card") {
                // 1️⃣ Create PaymentIntent
                const stripeOrder = await createStripeOrder({
                    amount: grandTotal,
                    currency: "USD"
                });

                console.log("StripeOrder response:", stripeOrder.data);

                const { clientSecret, paymentIntentId } = stripeOrder.data;

                // 2️⃣ Confirm Payment
                const cardElement = elements.getElement(CardElement);
                const result = await stripe.confirmCardPayment(clientSecret, {
                    payment_method: {
                        card: cardElement,
                        billing_details: {
                            name: customerName,
                            phone: mobileNumber,
                        },
                    },
                });

                if (result.error) {
                    toast.error(result.error.message);
                    await deleteOrderOnFailure(savedData.orderId);
                    return;
                }

                if (result.paymentIntent.status === "succeeded") {
                    await verifyStripePayment(savedData.orderId, paymentIntentId);
                    toast.success("Payment successful");
                    setOrderDetails({
                        ...savedData,
                        paymentDetails: {
                            paymentIntentId: paymentIntentId,
                        },
                    });
                }
            }
        } catch (error) {
            console.log(error);
            toast.error("Payment Processing failed");
        } finally {
            setIsProcessing(false);
        }
    };

    return (
        <div className="mt-2">
            <div className="card-summary-details">
                <div className="d-flex justify-content-between mb-2">
                    <span className="text-light">Item:</span>
                    <span className="text-light">$ {totalAmount.toFixed(2)}</span>
                </div>

                <div className="d-flex justify-content-between mb-2">
                    <span className="text-light">Tax (1%):</span>
                    <span className="text-light">$ {tax.toFixed(2)}</span>
                </div>

                <div className="d-flex justify-content-between mb-4">
                    <span className="text-light">Total:</span>
                    <span className="text-light">$ {grandTotal.toFixed(2)}</span>
                </div>
            </div>


            <div className="d-flex gap-3">
                <button
                    className="btn btn-success flex-grow-1"
                    onClick={() => completePayment("cash")}
                    disabled={isProcessing}
                >
                    {isProcessing ? "Processing.." : "CASH"}
                </button>

                <button
                    className="btn btn-warning flex-grow-1"
                    onClick={() => setShowStripeModal(true)}
                    disabled={isProcessing}
                >
                    {isProcessing ? "Processing.." : "CARD"}
                </button>

            </div>

            <div className="d-flex gap-3 mt-2">
                <button
                    className="btn btn-primary flex-grow-1"
                    onClick={placeOrder}
                    disabled={isProcessing || !orderDetails}
                >
                    Place Order
                </button>
            </div>

            {showStripeModal && (
                <div className="stripe-modal-backdrop">
                    <div className="stripe-modal-content">
                        <h3 style={{ marginBottom: "10px", color: "#333", fontWeight: "600" }}>
                            Enter Card Details
                        </h3>
                        <p style={{ color: "#555", fontWeight: "500" }}>Total: ${grandTotal.toFixed(2)}</p>

                        <div style={{ margin: "15px 0", padding: "12px", border: "1px solid #ccc", borderRadius: "6px", background: "#f8f8f8" }}>
                            <CardElement options={{ hidePostalCode: true }} />
                        </div>

                        <div className="d-flex gap-2">
                            <button
                                className="btn btn-success flex-grow-1"
                                onClick={() => completePayment("card")}
                                disabled={isProcessing}
                            >
                                {isProcessing ? "Processing..." : "Pay Now"}
                            </button>
                            <button
                                className="btn btn-secondary flex-grow-1"
                                onClick={() => setShowStripeModal(false)}
                            >
                                Cancel
                            </button>
                        </div>
                    </div>
                </div>
            )}



            {showPopup && (
                <ReceiptPopup
                    orderDetails={{
                        ...orderDetails,
                        stripePaymentId: orderDetails?.paymentDetails?.paymentIntentId,
                    }}
                    onClose={() => setShowPopup(false)}
                    onPrint={handlePrintReceipt}
                />
            )}
        </div>
    );
};

export default CartSummary;


