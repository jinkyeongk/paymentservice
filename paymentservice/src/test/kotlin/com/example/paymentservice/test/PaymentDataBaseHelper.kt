package com.example.paymentservice.test

import com.example.paymentservice.payment.domain.PaymentEvent

interface PaymentDataBaseHelper {
    fun getPayments(orderId: String):PaymentEvent
}