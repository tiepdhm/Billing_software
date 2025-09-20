package in.tiepdhm.billingsoftware.service;

import in.tiepdhm.billingsoftware.io.OrderRequest;
import in.tiepdhm.billingsoftware.io.OrderResponse;
import in.tiepdhm.billingsoftware.io.PaymentVerificationRequest;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    void deleteOrder(String orderId);

    List<OrderResponse> getlatestOrders();

    OrderResponse verifyPayment(PaymentVerificationRequest request);

    Double sumSalesPerDay(LocalDate date);

    Long countByOrderDate(LocalDate date);

    List<OrderResponse> findRecentOrders();
}
