package com.example.paymentservice.payment.adapter.out.persistent

import com.example.paymentservice.payment.domain.PaymentEvent
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono


interface PaymentRepository {
    fun save(paymentEvent: PaymentEvent): Mono<Void>
}