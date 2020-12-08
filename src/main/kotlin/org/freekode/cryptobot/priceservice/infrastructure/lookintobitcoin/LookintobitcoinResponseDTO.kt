package org.freekode.cryptobot.priceservice.infrastructure.lookintobitcoin

import com.fasterxml.jackson.databind.annotation.JsonDeserialize


@JsonDeserialize(using = LookintobitcoinResponseDeserializer::class)
class LookintobitcoinResponseDTO(
    val data: List<LookintobitcoinLineDTO>
) {
    fun getByName(name: String): LookintobitcoinLineDTO {
        return data.first { it.name == name }
    }
}
