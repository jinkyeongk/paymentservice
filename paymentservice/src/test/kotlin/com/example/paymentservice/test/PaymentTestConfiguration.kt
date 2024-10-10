package com.example.paymentservice.test

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.transaction.reactive.TransactionalOperator

@TestConfiguration
class PaymentTestConfiguration {

    @Bean
    fun paymentDatabaseHelper(databaseClient: DatabaseClient,transactionalOperator: TransactionalOperator ): PaymentDataBaseHelper{
        return R2DBCPaymentDatabaseHelper(databaseClient, transactionalOperator)

    }
}