package br.com.poc.dataproviders


import br.com.poc.dataproviders.entities.PersonEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface PersonJpaRepository : CrudRepository<PersonEntity, Long> {
}