package com.example.paymentservice.payment.application.service

import com.example.paymentservice.common.UseCase
import com.example.paymentservice.payment.application.port.`in`.CheckoutCommand
import com.example.paymentservice.payment.application.port.`in`.CheckoutUseCase
import com.example.paymentservice.payment.domain.CheckoutResult
import reactor.core.publisher.Mono


@UseCase
class CheckoutService : CheckoutUseCase{
    override fun checkout(command: CheckoutCommand): Mono<CheckoutResult> {
        TODO("Not yet implemented")
    }
}