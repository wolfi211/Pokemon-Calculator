package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "species")
data class Species(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "order")
    val order: Int?,

    @Column(name = "gender_rate")
    val genderRate: Int?,

    @Column(name = "capture_rate")
    val captureRate: Int?,

    @Column(name = "base_happiness")
    val baseHappiness: Int?,

    @Column(name = "is_baby")
    val isBaby: Boolean?,

    @Column(name = "is_legendary")
    val isLegendary: Boolean?,

    @Column(name = "is_mythical")
    val isMythical: Boolean?,

    @Column(name = "hatch_counter")
    val hatchCounter: Int?,

    @Column(name = "has_gender_differences")
    val hasGenderDifferences: Boolean?,

    @Column(name = "forms_switchable")
    val formsSwitchable: Boolean?,

    @Column(name = "names", columnDefinition = "jsonb")
    val names: Map<String, String>?,

    @Column(name = "flavor_text_entries", columnDefinition = "jsonb")
    val flavorTextEntries: Map<String, String>?,

    @Column(name = "form_descriptions", columnDefinition = "jsonb")
    val formDescriptions: Map<String, String>?,

    @OneToMany
    @JoinColumn(name = "species_id", referencedColumnName = "id")
    val pokemon: Set<Pokemon> = emptySet(),

    @OneToMany
    @JoinColumn(name = "species_id", referencedColumnName = "id")
    val pokedexEntries: Set<PokedexPokemon> = emptySet(),
)

