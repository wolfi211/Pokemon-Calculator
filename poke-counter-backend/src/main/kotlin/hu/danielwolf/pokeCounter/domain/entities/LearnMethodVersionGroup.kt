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
    var id: Int,

    @Column(name = "learn_method_id")
    var learnMethodId: Int,

    @Column(name = "version_group_id")
    var versionGroupId: Int,
)

