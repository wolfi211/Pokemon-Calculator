package hu.danielwolf.pokeCounter.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "regions")
data class Region(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "names", columnDefinition = "jsonb")
    val names: Map<String, String>?,

    @Column(name = "main_generation")
    val mainGenerationId: Int?,

    @OneToMany
    @JoinColumn(name = "region", referencedColumnName = "id")
    val pokedexes: Set<Pokedex> = emptySet(),
)

