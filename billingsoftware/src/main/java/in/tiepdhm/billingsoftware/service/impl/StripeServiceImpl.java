package in.tiepdhm.billingsoftware.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import in.tiepdhm.billingsoftware.io.StripeOrderResponse;
import in.tiepdhm.billingsoftware.service.StripeService;
import in.tiepdhm.billingsoftware.stripe.StripeCheckoutSessionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Override
    public StripeOrderResponse createOrder(Double amount, String currency) throws StripeException {
//        Stripe.apiKey = stripeSecretKey;
//
//        SessionCreateParams params = StripeCheckoutSessionFactory.createSessionParams(amount, currency);
//        Session session = Session.create(params);
//
//        return convertToResponse(session);
        Stripe.apiKey = stripeSecretKey;

        // Tạo PaymentIntent
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(Math.round(amount * 100)) // cents
                        .setCurrency(currency.toLowerCase()) // stripe yêu cầu lowercase
                        .setDescription("Order " + System.currentTimeMillis())
                        .putMetadata("receipt", "order_rcptid_" + System.currentTimeMillis())
                        .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.AUTOMATIC)
//                        .setConfirm(true) // auto confirm
                        .setPaymentMethod("pm_card_visa") // test card method
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                        .setEnabled(true)
                                        .setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER) // ⚡ fix
                                        .build()
                        )
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return convertToResponse(paymentIntent);
    }

    private StripeOrderResponse convertToResponse(PaymentIntent paymentIntent) {
        return StripeOrderResponse.builder()
                .id("order_" + paymentIntent.getId())
                .paymentIntentId(paymentIntent.getId())
                .clientSecret(paymentIntent.getClientSecret())
                .entity("payment_intent") // truoc la order
                .amount(paymentIntent.getAmount() != null ? paymentIntent.getAmount().intValue() : 0)
                .currency(paymentIntent.getCurrency() != null ? paymentIntent.getCurrency().toUpperCase() : null)
                .status(paymentIntent.getStatus())
                .created_at(new Date(paymentIntent.getCreated() * 1000))
                .receipt(paymentIntent.getMetadata().get("receipt"))
                .build();
    }

//    private StripeOrderResponse convertToResponse(Session session) {
//        return StripeOrderResponse.builder()
//                .id("order_" + session.getId())
//                .entity("order") // Stripe entity type
//                .amount(session.getAmountTotal() != null ? session.getAmountTotal().intValue() : 0)
//                .currency(session.getCurrency().toUpperCase())
//                .status(session.getStatus())
//                .created_at(new Date(session.getCreated() * 1000)) // epoch -> Date
//                .receipt(session.getClientReferenceId()) // giả lập receipt qua client_reference_id
//                .build();
}
