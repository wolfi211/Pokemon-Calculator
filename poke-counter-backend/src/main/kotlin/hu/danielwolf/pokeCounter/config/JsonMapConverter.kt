package hu.danielwolf.pokeCounter.config

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class JsonMapConverter : AttributeConverter<Map<String, String>, String> {

  override fun convertToDatabaseColumn(attribute: Map<String, String>?): String? {
    if (attribute.isNullOrEmpty()) return null
    return jsonMapper.writeValueAsString(attribute)
  }

  override fun convertToEntityAttribute(dbData: String?): Map<String, String> {
    if (dbData.isNullOrBlank()) return emptyMap()
    return jsonMapper.readValue(dbData, object : TypeReference<Map<String, String>>() {})
  }

  companion object {
    private val jsonMapper = jacksonObjectMapper()
  }
}
