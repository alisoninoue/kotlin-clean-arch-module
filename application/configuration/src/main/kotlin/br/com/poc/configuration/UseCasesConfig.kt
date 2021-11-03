package br.com.poc.configuration

import br.com.poc.core.dataproviders.PersonRepository
import br.com.poc.core.usecases.person.PersonGetterUseCase
import br.com.poc.core.usecases.person.PersonProducerEvent
import br.com.poc.core.usecases.person.PersonRegisterUseCase
import br.com.poc.core.usecases.person.impl.PersonGetterUseCaseImpl
import br.com.poc.core.usecases.person.impl.PersonRegisterUseCaseImpl
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
open class UseCasesConfig {

    @Singleton
    open fun loadGetterUseCase(personRepository: PersonRepository): PersonGetterUseCase =
        PersonGetterUseCaseImpl(personRepository)

    @Singleton
    open fun loadRegisterUseCase(personRepository: PersonRepository, personProducerEvent: PersonProducerEvent): PersonRegisterUseCase =
        PersonRegisterUseCaseImpl(personRepository, personProducerEvent)
}