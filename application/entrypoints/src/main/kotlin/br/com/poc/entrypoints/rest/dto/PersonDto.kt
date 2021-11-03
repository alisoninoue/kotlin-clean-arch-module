package br.com.poc.entrypoints.rest.dto

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Introspected
data class PersonDto(

    @field:NotNull
    @field:Positive
    val cpf: Long,

    @field:NotBlank
    val name: String?
)