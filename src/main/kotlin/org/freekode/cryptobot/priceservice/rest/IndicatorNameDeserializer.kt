package org.freekode.cryptobot.priceservice.rest

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.TextNode
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import org.freekode.cryptobot.priceservice.domain.price.PlatformName
import org.springframework.boot.jackson.JsonComponent


@JsonComponent
class IndicatorNameDeserializer : JsonDeserializer<IndicatorName>() {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): IndicatorName {
        val textNode: TextNode = parser.codec.readTree(parser)
        return IndicatorName(textNode.textValue())
    }
}
