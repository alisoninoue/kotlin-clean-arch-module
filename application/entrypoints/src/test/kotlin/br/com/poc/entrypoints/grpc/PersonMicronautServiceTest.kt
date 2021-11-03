package br.com.poc.entrypoints.grpc

import assertk.assertThat
import br.com.poc.PersonDetails
import br.com.poc.core.models.Person
import br.com.poc.core.usecases.person.PersonGetterUseCase
import br.com.poc.core.usecases.person.PersonRegisterUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class PersonMicronautServiceTest {
    private val registerUseCase = mockk<PersonRegisterUseCase>()
    private val getterUseCase = mockk<PersonGetterUseCase>()
    private val service = PersonMicronautService(registerUseCase, getterUseCase)

    @Test
    internal fun `should save person`() {
        val person = Person(2L, "Alison")
        every { registerUseCase.registerPerson(ofType(Person::class)) } returns person

        service.savePerson(person.cpf, person.name!!)
        verify(exactly = 1) {
            registerUseCase.registerPerson(ofType(Person::class))
        }
    }

    @Test
    internal fun `should find person`() {
        val person = Person(1L, "Alison")
        every { getterUseCase.findById(ofType(Long::class)) } returns person

        val personDetailsExpected = PersonDetails.newBuilder()
            .setCpf(person.cpf)
            .setName(person.name)
            .build()

        val replyMessage = service.findBy(1L)

        assertThat(replyMessage).equals(personDetailsExpected)
    }
}