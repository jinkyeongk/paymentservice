package com.example.paymentservice.payment.adapter.out.web.product

import com.example.paymentservice.common.WebAdapter
import com.example.paymentservice.payment.application.port.out.LoadProductPort
import com.example.paymentservice.payment.domain.Product
import reactor.core.publisher.Flux

// 실제 서비스가 마이크로 서비스로 구성되었다는 전제 하에 고려한 설계
@WebAdapter
class ProductWebAdapter : LoadProductPort{
    override fun getProducts(cartId: Long, productIds: List<Long>): Flux<Product> {
        TODO()
    }

}