package data

import utils.encodeToString
import utils.hash
import java.security.PublicKey

/**
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : chukimmuoi@gmail.com
 * @Project : kmCoin
 * Created by chukimmuoi on 10/02/2021.
 */

data class TransactionOutput(
        val recipient: PublicKey,
        val amount: Int,
        val transactionHash: String,
        var hash: String = "") {

    init {
        hash = "${recipient.encodeToString()}$amount$transactionHash".hash()
    }

    fun isMine(me: PublicKey): Boolean {
        return recipient == me
    }

    override fun toString(): String {
        return "{" +
                "\n\trecipient: \"$recipient\", \n" +
                "\tamount: \"$amount\", \n" +
                "\ttransactionHash: \"$transactionHash\", \n" +
                "\thash: \"$hash\"\n" +
                "}"
    }
}