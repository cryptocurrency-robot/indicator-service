package org.freekode.cryptobot.indicatorservice.rest

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import org.springframework.boot.jackson.JsonComponent


@JsonComponent
class IndicatorNameSerializer : JsonSerializer<IndicatorId>() {

    override fun serialize(value: IndicatorId, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
        jsonGenerator.writeString(value.value)
    }
}
