package br.com.poc.core.usecases.person

import br.com.poc.core.models.Person

interface PersonGetterUseCase {
    fun findById(cpf: Long): Person
}