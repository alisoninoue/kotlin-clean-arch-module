package br.com.poc.entrypoints.rest

import br.com.poc.core.exceptions.InvalidArgumentException
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [InvalidArgumentException::class, ExceptionHandler::class])
class InvalidArgumentExceptionHandler : ExceptionHandler<InvalidArgumentException, HttpResponse<*>>{

    override fun handle(request: HttpRequest<*>?, exception: InvalidArgumentException): HttpResponse<*> {
        return HttpResponse.badRequest(exception.message)
    }
}