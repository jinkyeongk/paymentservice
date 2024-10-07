package com.example.paymentservice.payment.adapter.out.persistent

import com.example.paymentservice.common.PersistentAdapter
import com.example.paymentservice.payment.adapter.out.persistent.repository.PaymentRepository
import com.example.paymentservice.payment.application.port.out.SavePaymentPort
import com.example.paymentservice.payment.domain.PaymentEvent
import org.springframework.data.annotation.Persistent
import reactor.core.publisher.Mono

@PersistentAdapter
class PaymentPersistentAdapter (
    private val paymentRepository: PaymentRepository
): SavePaymentPort{
//DB와 연결시키기위한 어댑터
    override fun save(paymentEvent: PaymentEvent): Mono<Void> {
        return paymentRepository.save(paymentEvent);
    }
}