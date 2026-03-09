package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "learn_method_version_group")
data class LearnMethodVersionGroup(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "learn_method_id")
    val learnMethodId: Int,

    @Column(name = "version_group_id")
    val versionGroupId: Int,
)

