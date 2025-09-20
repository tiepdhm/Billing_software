package in.tiepdhm.billingsoftware.controller;

import com.stripe.exception.StripeException;
import in.tiepdhm.billingsoftware.io.OrderResponse;
import in.tiepdhm.billingsoftware.io.PaymentRequest;
import in.tiepdhm.billingsoftware.io.PaymentVerificationRequest;
import in.tiepdhm.billingsoftware.io.StripeOrderResponse;
import in.tiepdhm.billingsoftware.service.OrderService;
import in.tiepdhm.billingsoftware.service.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final StripeService stripeService;
    private final OrderService orderService;

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    public StripeOrderResponse createStripeOrder(@RequestBody PaymentRequest request) throws StripeException
    {
        return stripeService.createOrder(request.getAmount(),request.getCurrency());
    }


    @PostMapping("/verify")
    public OrderResponse verifyPayment(@RequestBody PaymentVerificationRequest request)
    {
        return orderService.verifyPayment(request);

    }
}

