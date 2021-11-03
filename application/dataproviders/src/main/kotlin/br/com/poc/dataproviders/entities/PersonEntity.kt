package br.com.poc.dataproviders.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "person")
data class PersonEntity(
    @Id
    val cpf: Long,

    @NotNull
    @Column(name = "name", nullable = false)
    val name: String?
)