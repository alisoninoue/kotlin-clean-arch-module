package br.com.poc.dataproviders

import br.com.poc.core.dataproviders.PersonRepository
import br.com.poc.core.models.Person
import br.com.poc.dataproviders.entities.PersonEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.transaction.annotation.ReadOnly
import java.util.*
import javax.transaction.Transactional

@Repository
open class PersonRepositoryImpl(private val jpaRepository: PersonJpaRepository) : PersonRepository {
    @Transactional
    override fun register(person: Person) : Person {
        return jpaRepository.save(person.toEntity()).toDomain()
    }

    @ReadOnly
    override fun findBy(cpf: Long): Optional<Person> {
        val optional = jpaRepository.findById(cpf)
        if (optional.isPresent) {
            return Optional.of(optional.get().toDomain())
        }
        return Optional.empty()
    }

    private fun Person.toEntity(): PersonEntity {
        return PersonEntity(cpf, name)
    }

    private fun PersonEntity.toDomain(): Person {
        return Person(cpf, name)
    }
}