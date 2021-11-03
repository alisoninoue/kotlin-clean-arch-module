package br.com.poc.entrypoints.grpc

import assertk.assertThat
import assertk.assertions.isIn
import br.com.poc.PersonReply
import br.com.poc.PersonRequest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class PersonEndpointTest {
    @Test
    internal fun `should create new person`() {
        val service = mockk<PersonMicronautService>()

        val reply = PersonReply.newBuilder().setMessage("Person created!").build()
        val request = PersonRequest.newBuilder()
            .setCpf(123456L)
            .setName("Alison").build()

        every {
            service.savePerson(any(), any())
        } returns Unit

        runBlocking {
            val response = PersonEndpoint(service).createPerson(request)
            assertThat(response).isIn(reply)
        }
    }
}