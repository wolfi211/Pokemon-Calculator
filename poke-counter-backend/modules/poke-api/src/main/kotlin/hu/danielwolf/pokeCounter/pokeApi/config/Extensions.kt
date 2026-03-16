@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.config

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import java.net.URI

fun String.toURI() = URI(this)

fun List<ExternalName>.toEntityMap(): Map<String, String> = this.associate { it.language.name to it.name }