package br.com.poc.configuration

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("br.com.poc")
                .mainClass(Application.javaClass)
                .start()
    }
}