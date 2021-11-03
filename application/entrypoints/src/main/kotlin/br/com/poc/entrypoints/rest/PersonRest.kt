package br.com.poc.entrypoints.rest

import br.com.poc.core.exceptions.PersonNotFoundException
import br.com.poc.core.models.Person
import br.com.poc.core.usecases.person.PersonGetterUseCase
import br.com.poc.core.usecases.person.PersonRegisterUseCase
import br.com.poc.entrypoints.rest.dto.ErrorDto
import br.com.poc.entrypoints.rest.dto.PersonDto
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.validation.ConstraintViolationException
import javax.validation.Valid


@Controller("/people")
open class PersonRest(
    private val personRegisterUseCase: PersonRegisterUseCase,
    private val personGetterUseCase: PersonGetterUseCase,
    private val messageSource: MessageSource
) {

    @Get("/{cpf}")
    open fun findByCpf(cpf: Long): HttpResponse<*> {
        val personDto = personGetterUseCase.findById(cpf).toDto()
        return HttpResponse.ok(personDto)
    }

    @Post
    open fun save(@Valid @Body person: PersonDto): HttpResponse<PersonDto> {
        val personDto = personRegisterUseCase.registerPerson(person.toDomain()).toDto()
        return HttpResponse.created(personDto)
    }

    @Error(exception = PersonNotFoundException::class)
    fun notFoundErrorHandler(
        request: HttpRequest<*>?,
        exception: PersonNotFoundException
    ): HttpResponse<*> {
        return HttpResponse.notFound("${exception.message} Request: $request")
    }

    @Error(exception = ConstraintViolationException::class)
    fun constraintViolationErrorHandler(
        request: HttpRequest<*>?,
        exception: ConstraintViolationException
    ): HttpResponse<ErrorDto> {
        val body = request?.body?.get()
        val errorDto = ErrorDto(body, messageSource.violationsMessages(exception.constraintViolations))
        return HttpResponse.badRequest(errorDto)
    }

    private fun Person.toDto(): PersonDto {
        return PersonDto(cpf, name)
    }

    private fun PersonDto.toDomain(): Person {
        return Person(cpf, name)
    }
}