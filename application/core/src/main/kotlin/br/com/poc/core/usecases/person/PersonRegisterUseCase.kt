package br.com.poc.core.usecases.person

import br.com.poc.core.models.Person

interface PersonRegisterUseCase {
    fun registerPerson(person: Person): Person
}