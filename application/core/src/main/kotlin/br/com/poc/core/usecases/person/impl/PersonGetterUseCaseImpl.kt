package br.com.poc.core.usecases.person.impl

import br.com.poc.core.dataproviders.PersonRepository
import br.com.poc.core.exceptions.PersonNotFoundException
import br.com.poc.core.models.Person
import br.com.poc.core.usecases.person.PersonGetterUseCase

class PersonGetterUseCaseImpl(private val repository: PersonRepository) :
    PersonGetterUseCase {

    override fun findById(cpf: Long): Person {
        return repository.findBy(cpf).orElseThrow { PersonNotFoundException("Person $cpf not found!") }
    }
}