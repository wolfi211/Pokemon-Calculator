package hu.danielwolf.pokeCounter.config

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import tools.jackson.core.type.TypeReference
import tools.jackson.module.kotlin.jacksonObjectMapper

val jsonMapper = jacksonObjectMapper()

@Converter
class JsonMapConverter : AttributeConverter<Map<String, String>, String> {

  override fun convertToDatabaseColumn(attribute: Map<String, String>?): String? {
    return attribute?.let { jsonMapper.writeValueAsString(it) }
  }

  override fun convertToEntityAttribute(dbData: String?): Map<String, String> {
    if (dbData.isNullOrBlank()) return emptyMap()
    return jsonMapper.readValue(dbData, object : TypeReference<Map<String, String>>() {})
  }
}