package br.com.poc.entrypoints.grpc

import br.com.poc.PersonDetails
import br.com.poc.core.models.Person
import br.com.poc.core.usecases.person.PersonGetterUseCase
import br.com.poc.core.usecases.person.PersonRegisterUseCase
import io.micronaut.tracing.annotation.ContinueSpan
import io.micronaut.tracing.annotation.SpanTag
import io.micronaut.transaction.annotation.ReadOnly
import mu.KotlinLogging
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
open class PersonMicronautService(
    private val personRegisterUseCase: PersonRegisterUseCase,
    private val personGetterUseCase: PersonGetterUseCase,
) : PersonService {
    private val logger = KotlinLogging.logger {}

    @Transactional
    @ContinueSpan
    override fun savePerson(@SpanTag("cpf") cpf: Long, name: String) {
        logger.info("Saving person - cpf: $cpf + name: $name")
        personRegisterUseCase.registerPerson(Person(cpf, name))
    }

    @ReadOnly
    @ContinueSpan
    override fun findBy(cpf: Long): PersonDetails {
        val person = personGetterUseCase.findById(cpf)
        logger.info("Find $person")

        return PersonDetails.newBuilder()
            .setCpf(person.cpf)
            .setName(person.name)
            .build()
    }
}