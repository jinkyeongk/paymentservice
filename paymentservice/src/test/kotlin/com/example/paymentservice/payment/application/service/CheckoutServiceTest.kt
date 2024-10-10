package com.example.paymentservice.payment.application.service

import com.example.paymentservice.payment.application.port.`in`.CheckoutCommand
import com.example.paymentservice.payment.application.port.`in`.CheckoutUseCase
import com.example.paymentservice.test.PaymentDataBaseHelper
import com.example.paymentservice.test.PaymentTestConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.dao.DataIntegrityViolationException
import reactor.test.StepVerifier
import java.util.*
import kotlin.test.assertFalse

//통합테스트 위주의 테스트

@SpringBootTest
@Import(PaymentTestConfiguration::class)
class CheckoutServiceTest(
    @Autowired private val checkoutUseCase: CheckoutUseCase,
    @Autowired private val paymentDatabaseHelper: PaymentDataBaseHelper
){

    //테스트할 때마다 쌓이는 데이터들을 정리해주는 클렌징 코드
    @BeforeEach
    fun setup() {
        paymentDatabaseHelper.clean().block()
    }
    //페이먼트 이벤트와 페이먼트 오더가 DB에 정상 저장되는 지 확인
    @Test
    fun `should save PaymentEvent and PaymentOrder successfully`() {
        val orderId = UUID.randomUUID().toString()
        val checkoutCommand = CheckoutCommand(
            cartId  = 1,
            buyerId = 1,
            productIds = listOf(1,2,3),
            idempotencyKey =  orderId
        )

        StepVerifier.create(checkoutUseCase.checkout(checkoutCommand))
            .expectNextMatches {
                println(it.amount)
                it.amount.toInt() == 60000 && it.orderId == orderId
            }
            .verifyComplete()

        val paymentEvent = paymentDatabaseHelper.getPayments(orderId)!!

        assertThat(paymentEvent.orderId).isEqualTo(orderId)
        assertThat(paymentEvent.totalAmount()).isEqualTo(60000)
        assertThat(paymentEvent.paymentOrders.size).isEqualTo(checkoutCommand.productIds.size)
        assertFalse(paymentEvent.isPaymentDone())
        assertThat(paymentEvent.paymentOrders.all{ !it.isLedgerUpdated()})
        assertThat(paymentEvent.paymentOrders.all{ !it.isWalletUpdated()})

    }

    @Test
    fun `should fail to save PaymentEvent and PaymentOrder when trying to save for th second time`() {
        val orderId = UUID.randomUUID().toString()
        val checkoutCommand = CheckoutCommand(
            cartId  = 1,
            buyerId = 1,
            productIds = listOf(1,2,3),
            idempotencyKey =  orderId
        )

        checkoutUseCase.checkout(checkoutCommand).block()

        assertThrows<DataIntegrityViolationException>{
            checkoutUseCase.checkout(checkoutCommand).block()
        }
    }
}