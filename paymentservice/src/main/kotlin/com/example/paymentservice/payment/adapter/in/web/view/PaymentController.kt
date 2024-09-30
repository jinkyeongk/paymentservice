package com.example.paymentservice.payment.adapter.`in`.web.view

import com.example.paymentservice.common.WebAdapter
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Mono


@Controller
@WebAdapter //웹 요청을 받아서 웹어플리케이션에 명령을 전달하는 웹어댑터 어노테이션
@RequestMapping("/v1/toss") //결제 성공과 실패에 대한 리다이렉션 렌더링 코드
class PaymentController {

    // 페이지 리다이렉션 렌더링 코드
    @PostMapping("/success")
    fun successPage(): Mono<String>
    {
        return Mono.just("success")
    }

    @PostMapping("/fail")
    fun failPage(): Mono<String>
    {
        return Mono.just("fail")
    }
}
