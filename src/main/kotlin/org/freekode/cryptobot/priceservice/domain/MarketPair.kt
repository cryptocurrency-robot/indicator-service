package org.freekode.cryptobot.priceservice.domain


enum class MarketPair(
    private val left: Coin,
    private val right: Coin
) {
    BTC_USDT(Coin.BTC, Coin.USDT),
    ADA_BTC(Coin.ADA, Coin.BTC),
    BTC(Coin.BTC, Coin.NONE),
    NONE(Coin.NONE, Coin.NONE);

    companion object {
        fun getOneSidePair(coin: Coin): MarketPair {
            return values().first { it.left == coin }
        }
    }

    fun isOneSidePair(): Boolean {
        return right == Coin.NONE
    }

    fun getStringValue(): String {
        val rightString = if (right == Coin.NONE) "" else right.toString()
        return "${left}${rightString}"
    }
}
