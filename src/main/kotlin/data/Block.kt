package data

import utils.hash
import java.time.Instant

/**
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : chukimmuoi@gmail.com
 * @Project : kmCoin
 * Created by chukimmuoi on 09/02/2021.
 */

data class Block(
    val previousHash: String,
    val transactions: MutableList<Transaction> = mutableListOf(),
    val timestamp: Long = Instant.now().toEpochMilli(),
    var nonce: Long = 0,
    var hash: String = "") {

    init {
        hash = calculateHash()
    }

    fun calculateHash(): String {
        val data = "$previousHash$transactions$timestamp$nonce"
        val hashData = data.hash()

        return hashData
    }

    fun addTransaction(transaction: Transaction): Block {
        if (transaction.isSignatureValid()) {
            transactions.add(transaction)
        }

        return this
    }

    override fun toString(): String {
        return "{" +
                "\n\tpreviousHash: \"$previousHash\", \n" +
                "\ttransactions: \"$transactions\", \n" +
                "\ttimestamp: \"$timestamp\", \n" +
                "\tnonce: \"$nonce\", \n" +
                "\thash: \"$hash\"\n" +
                "}"
    }
}