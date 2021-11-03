package br.com.poc.entrypoints.rest

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
import io.micronaut.http.server.exceptions.ExceptionHandler
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [Exception::class, ExceptionHandler::class])
class GenericExceptionHandler : ExceptionHandler<Exception, HttpResponse<*>> {
    override fun handle(request: HttpRequest<*>?, exception: Exception): HttpResponse<JsonError> {
        val jsonError = JsonError(exception.message)
            .link(Link.SELF, Link.of(request!!.getUri()));
        return HttpResponse.serverError(jsonError)
    }
}