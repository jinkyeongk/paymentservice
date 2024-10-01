package com.example.paymentservice.common

import org.springframework.stereotype.Component

@Component
@Target(AnnotationTarget.CLASS)
annotation class UseCase() //어플리케이션이 제공하는 핵심 기능들의 작업 흐름
