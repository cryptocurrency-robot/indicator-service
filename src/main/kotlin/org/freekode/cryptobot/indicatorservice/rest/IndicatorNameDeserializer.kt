package org.freekode.cryptobot.indicatorservice.rest

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.TextNode
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import org.springframework.boot.jackson.JsonComponent


@JsonComponent
class IndicatorNameDeserializer : JsonDeserializer<IndicatorId>() {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): IndicatorId {
        val textNode: TextNode = parser.codec.readTree(parser)
        return IndicatorId(textNode.textValue())
    }
}
