package com.example.paymentservice.payment.domain

import java.math.BigDecimal

data class PaymentOrder(
    val id: Long? = null,
    val paymentEventId: Long? = null,
    val sellerId: Long,
    val productId: Long,
    val orderId: String,
    val amount: BigDecimal,
    val paymentStatus: PaymentStatus,
    private var isLefgerUpdated: Boolean = false, // 장부기입여부
    private var isWalletUpdated: Boolean = false //정산처리여부
)
