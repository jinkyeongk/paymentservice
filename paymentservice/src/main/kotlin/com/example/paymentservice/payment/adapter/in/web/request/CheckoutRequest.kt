package com.example.paymentservice.payment.adapter.`in`.web.request

import java.time.LocalDateTime

data class CheckoutRequest(
    val cartId :Long = 1,       //장바구니 ID
    val productId:List<Long> = listOf(1,2,3), //상품 ID
    val buyerId : Long =1 , //구매자 ID
    val seed:String =LocalDateTime.now().toString()//동일한 물건을 재구매할 때 구분하는 역할
)
