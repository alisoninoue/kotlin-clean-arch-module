package br.com.poc.core.dataproviders

import br.com.poc.core.models.Person
import java.util.*

interface PersonRepository {
    fun register(person: Person) : Person
    fun findBy(cpf: Long) : Optional<Person>
}