package br.com.poc.core.usecases.person.impl

import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.messageContains
import br.com.poc.core.dataproviders.PersonRepository
import br.com.poc.core.models.Person
import br.com.poc.core.usecases.person.PersonProducerEvent
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*

internal class PersonRegisterUseCaseImplTest {
    @Test
    internal fun `should save person`() {
        val repository = mockk<PersonRepository>()
        val producer = mockk<PersonProducerEvent>()
        val personRegisterUseCaseImpl = PersonRegisterUseCaseImpl(repository, producer)
        val person = Person(1L, "Alison")

        every { repository.findBy(person.cpf) } returns Optional.empty()
        every { repository.register(person) } returns person
        every { producer.sendEventPersonCreated(person) } returns Unit

        personRegisterUseCaseImpl.registerPerson(person)

        verify(atMost = 1) { repository.findBy(person.cpf) }
        verify(atMost = 1) { repository.register(person) }
        verify(atMost = 1) { producer.sendEventPersonCreated(person) }
    }

    @Test
    internal fun `should throw exception person already exists`() {
        val repository = mockk<PersonRepository>()
        val producer = mockk<PersonProducerEvent>()
        val personRegisterUseCaseImpl = PersonRegisterUseCaseImpl(repository, producer)
        val person = Person(1L, "Alison")

        every { repository.findBy(person.cpf) } returns Optional.of(person)

        assertThat {
            personRegisterUseCaseImpl.registerPerson(person)
        }.isFailure().messageContains("already exists!")

        verify(atMost = 1) { repository.findBy(person.cpf) }
        verify(exactly = 0) { repository.register(person) }
        verify(exactly = 0) { producer.sendEventPersonCreated(person) }
    }
}