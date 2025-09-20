package in.tiepdhm.billingsoftware.service;

import com.stripe.exception.StripeException;
import in.tiepdhm.billingsoftware.io.StripeOrderResponse;

public interface StripeService {

    StripeOrderResponse createOrder(Double amount, String currency) throws StripeException;
}
