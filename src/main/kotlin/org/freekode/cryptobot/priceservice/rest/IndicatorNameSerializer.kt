package org.freekode.cryptobot.priceservice.rest

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import org.springframework.boot.jackson.JsonComponent


@JsonComponent
class IndicatorNameSerializer : JsonSerializer<IndicatorName>() {

    override fun serialize(value: IndicatorName, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
        jsonGenerator.writeString(value.name)
    }
}
