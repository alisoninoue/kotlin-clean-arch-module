package br.com.poc.core.usecases.person.impl

import br.com.poc.core.dataproviders.PersonRepository
import br.com.poc.core.exceptions.InvalidArgumentException
import br.com.poc.core.models.Person
import br.com.poc.core.usecases.person.PersonProducerEvent
import br.com.poc.core.usecases.person.PersonRegisterUseCase

class PersonRegisterUseCaseImpl(private val repository: PersonRepository, private val producer: PersonProducerEvent) :
    PersonRegisterUseCase {

    override fun registerPerson(person: Person) : Person {
        repository.findBy(person.cpf)
            .ifPresent { throw InvalidArgumentException("Person ${person.cpf} already exists!") }
        repository.register(person)

        producer.sendEventPersonCreated(person)
        return person
    }
}