package br.com.poc.dataproviders.kafka.producer

import br.com.poc.PersonCreated
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import org.apache.kafka.common.header.Headers

@KafkaClient
interface PersonKafkaProducer  {

    @Topic("person-created")
    fun sendPersonCreated(@KafkaKey key: String, person: PersonCreated, headers: Headers)
}