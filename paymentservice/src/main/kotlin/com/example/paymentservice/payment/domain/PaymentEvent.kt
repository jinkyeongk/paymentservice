package com.example.paymentservice.payment.domain

import java.time.LocalDateTime

data class PaymentEvent(
    val id : Long? = null,
    val buyerId: Long,
    val orderName: String,
    val orderId: String,
    val paymentKey: String? = null,
    val paymentType: PaymentType? = null,
    val paymentMethod: PaymentMethod? = null,
    val approvedAt: LocalDateTime? = null,
    val paymentOrders: List<PaymentOrder> = emptyList(),
    private val isPaymentDone: Boolean = false //결제 완료 여부
){
    fun totalAmount(): Long{
        return paymentOrders.sumOf{it.amount}.toLong()
    }

    fun isPaymentDone(): Boolean = isPaymentDone
}
