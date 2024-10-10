package com.example.paymentservice.test

import com.example.paymentservice.payment.domain.PaymentEvent
import reactor.core.publisher.Mono

//OrderId를 받았을 때 페이먼트 이벤트를 가져오도록 하는 기능
interface PaymentDataBaseHelper {
    fun getPayments(orderId: String):PaymentEvent?

    fun clean(): Mono<Void>
}