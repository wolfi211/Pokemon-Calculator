package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "learn_method_version_group")
data class LearnMethodVersionGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "learn_method_version_group_id_seq")
    @SequenceGenerator(name = "learn_method_version_group_id_seq", sequenceName = "learn_method_version_group_id_seq", allocationSize = 1)
    var id: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "learn_method_id")
    var learnMethod: MoveLearnMethod,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_group_id")
    var versionGroup: VersionGroup,
)

