package br.com.poc.entrypoints.rest.dto

import io.micronaut.core.annotation.Introspected

@Introspected
data class ErrorDto(
    val request: Any?,
    val errors: List<String>
)