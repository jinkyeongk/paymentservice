package com.example.paymentservice.payment.adapter.`in`.web.view

import com.example.paymentservice.common.IdempotencyCreator
import com.example.paymentservice.common.WebAdapter;
import com.example.paymentservice.payment.adapter.`in`.web.request.CheckoutRequest
import com.example.paymentservice.payment.application.port.`in`.CheckoutCommand
import com.example.paymentservice.payment.application.port.`in`.CheckoutUseCase
import com.example.paymentservice.payment.application.service.CheckoutService
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@WebAdapter
@Controller
class CheckoutController (
    private val checkoutUseCase: CheckoutUseCase
){

    @GetMapping("/")
    fun checkoutPage(request: CheckoutRequest,Model model): Mono<String> {
        CheckoutCommand(
            cartId = request.cartId,
            buyerId = request.buyerId,
            productIds = request.productId,
            idempotencyKey = IdempotencyCreator.create(request.seed)
        )

        checkoutUseCase.checkout(command)
            .map{
                model.addAttribute("orderId", it.orderId)
                model.addAttribute("orderName", it.orderName)
                model.addAttribute("amount", it.amount)
                "checkout"
            }
        return Mono.just("checkout")

    }
}
