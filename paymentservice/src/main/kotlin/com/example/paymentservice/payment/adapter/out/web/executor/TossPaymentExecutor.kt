package com.example.paymentservice.payment.adapter.out.web.executor

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class TossPaymentExecutor (
    private val tossPaymentWebClient: WebClient,
    private val uri: String = "/v1/payments/confirm"
){
    //외부로 API 요청을 보냄 (실제 승인 API 호출 로직)
    fun execute(paymentKey: String, orderId: String, amount:String ): Mono<String> {
       return tossPaymentWebClient.post()
            .uri(uri) //결제 승인 API URI 값
            .bodyValue("""
              {  "paymentKey": "${paymentKey}",
                "orderId": "${orderId}",
                "amount": $amount
                }
            """.trimIndent())
            .retrieve()
            .bodyToMono(String::class.java)
    }
}