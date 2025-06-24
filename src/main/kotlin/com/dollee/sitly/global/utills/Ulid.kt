package com.dollee.sitly.global.utills

import org.hibernate.annotations.IdGeneratorType

@IdGeneratorType(UlidGenerator::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class Ulid
