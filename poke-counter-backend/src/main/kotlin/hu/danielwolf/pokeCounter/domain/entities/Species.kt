package hu.danielwolf.pokeCounter.domain.entities

import hu.danielwolf.pokeCounter.config.JsonMapConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
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
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Column(name = "order")
    var order: Int?,

    @Column(name = "gender_rate")
    var genderRate: Int?,

    @Column(name = "capture_rate")
    var captureRate: Int?,

    @Column(name = "base_happiness")
    var baseHappiness: Int?,

    @Column(name = "is_baby")
    var isBaby: Boolean?,

    @Column(name = "is_legendary")
    var isLegendary: Boolean?,

    @Column(name = "is_mythical")
    var isMythical: Boolean?,

    @Column(name = "hatch_counter")
    var hatchCounter: Int?,

    @Column(name = "has_gender_differences")
    var hasGenderDifferences: Boolean?,

    @Column(name = "forms_switchable")
    var formsSwitchable: Boolean?,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "flavor_text_entries", columnDefinition = "jsonb")
    var flavorTextEntries: Map<String, String>? = emptyMap(),

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "form_descriptions", columnDefinition = "jsonb")
    var formDescriptions: Map<String, String>? = emptyMap(),

    @OneToMany
    @JoinColumn(name = "species_id", referencedColumnName = "id")
    var pokemon: Set<Pokemon> = emptySet(),

    @OneToMany
    @JoinColumn(name = "species_id", referencedColumnName = "id")
    var pokedexEntries: Set<PokedexPokemon> = emptySet(),
)

