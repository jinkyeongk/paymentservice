package com.example.paymentservice.common

object IdempotencyCreator {

    fun create(data: Any):String{
        return UUID.nameUUIDFromBytes(data.toString().toByteArray()).toString()
    }
}