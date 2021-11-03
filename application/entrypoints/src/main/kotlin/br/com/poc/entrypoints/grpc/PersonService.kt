package br.com.poc.entrypoints.grpc

import br.com.poc.PersonDetails

interface PersonService {
    fun savePerson(cpf: Long, name: String)
    fun findBy(cpf: Long): PersonDetails
}