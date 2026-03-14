package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table

@Entity
@Table(name = "pokedex_pokemon")
data class PokedexPokemon(
    @EmbeddedId
    var id: PokedexPokemonId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pokedexId")
    @JoinColumn(name = "pokedex_id")
    var pokedex: Pokedex,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("versionGroupId")
    @JoinColumn(name = "version_group_id")
    var versionGroup: VersionGroup,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pokemonId")
    @JoinColumn(name = "pokemon_id")
    var pokemon: Pokemon,

    @Column(name = "entry_number")
    var entryNumber: Int,
)
