package br.com.poc.core.usecases.person.impl

import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import br.com.poc.core.dataproviders.PersonRepository

import br.com.poc.core.models.Person
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.util.*

internal class PersonGetterUseCaseImplTest {
    @Test
    internal fun `should return person`() {
        val repository = mockk<PersonRepository>()
        val personGetterUseCaseImpl = PersonGetterUseCaseImpl(repository)
        val person = Person(1L, "Alison")
        every {
            repository.findBy(ofType(Long::class))
        } returns Optional.of(person)

        val optionalReturn = personGetterUseCaseImpl.findById(1L)
        assertThat(optionalReturn).isEqualTo(person)
    }

    @Test
    internal fun `should throw exception`() {
        val repository = mockk<PersonRepository>()
        val personGetterUseCaseImpl = PersonGetterUseCaseImpl(repository)

        every {
            repository.findBy(ofType(Long::class))
        } returns Optional.empty()

        assertThat {
            personGetterUseCaseImpl.findById(1L)
        }.isFailure().hasMessage("Person 1 not found!")
    }
}