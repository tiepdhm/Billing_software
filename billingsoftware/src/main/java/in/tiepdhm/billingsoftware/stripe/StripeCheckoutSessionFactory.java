package in.tiepdhm.billingsoftware.stripe;

import com.stripe.param.checkout.SessionCreateParams;

public class StripeCheckoutSessionFactory {

    public static SessionCreateParams createSessionParams(Double amount, String currency) {
        return SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setClientReferenceId("order_rcptid_" + System.currentTimeMillis())
                .setSuccessUrl("https://example.com/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl("https://example.com/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(currency)
                                                .setUnitAmount(Math.round(amount * 100)) // cents
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Order " + System.currentTimeMillis())
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}
