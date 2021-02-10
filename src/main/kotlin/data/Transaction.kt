package data

import utils.encodeToString
import utils.hash
import utils.sign
import utils.verifySignature
import java.security.PrivateKey
import java.security.PublicKey

/**
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : chukimmuoi@gmail.com
 * @Project : kmCoin
 * Created by chukimmuoi on 10/02/2021.
 */

data class Transaction(
        val sender: PublicKey,
        val recipient: PublicKey,
        val amount: Int,
        var hash: String = "",
        val inputs: MutableList<TransactionOutput> = mutableListOf(),
        val outputs: MutableList<TransactionOutput> = mutableListOf()) {

    private var signature: ByteArray = ByteArray(0)

    init {
        hash = "${sender.encodeToString()}${recipient.encodeToString()}$amount$salt".hash()
    }

    companion object {
        fun create(sender: PublicKey, recipient: PublicKey, amount: Int): Transaction {
            return Transaction(sender, recipient, amount)
        }

        var salt: Long = 0
            get() {
                field += 1
                return field
            }
    }

    fun sign(privateKey: PrivateKey): Transaction {
        signature = "${sender.encodeToString()}${recipient.encodeToString()}$amount".sign(privateKey)

        return this
    }

    fun isSignatureValid(): Boolean {
        return "${sender.encodeToString()}${recipient.encodeToString()}$amount".verifySignature(sender, signature)
    }

    override fun toString(): String {
        return "{" +
                "\n\tsender: \"$sender\", \n" +
                "\trecipient: \"$recipient\", \n" +
                "\tamount: \"$amount\", \n" +
                "\thash: \"$hash\", \n" +
                "\tinputs: \"$inputs\"\n" +
                "\toutputs: \"$outputs\"\n" +
                "}"
    }
}