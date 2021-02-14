import data.*

// https://medium.com/@vasilyf/lets-implement-a-cryptocurrency-in-kotlin-part-1-blockchain-8704069f8580
// https://medium.com/@vasilyf/lets-implement-a-cryptocurrency-in-kotlin-part-2-wallets-and-transactions-7aa55239c0d9
fun main(args: Array<String>) {

    val blockChain = BlockChain()
    val wallet1 = Wallet.create(blockChain)
    val wallet2 = Wallet.create(blockChain)

    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")

    val tx1 = Transaction.create(
            sender = wallet1.publicKey,
            recipient = wallet1.publicKey,
            amount = 100
    )
    tx1.outputs.add(
            TransactionOutput(
                    recipient = wallet1.publicKey,
                    amount = 100,
                    transactionHash = tx1.hash
            )
    )
    tx1.sign(wallet1.privateKey)

    var genesisBlock = Block(previousHash = "0")
    genesisBlock.addTransaction(tx1)
    genesisBlock = blockChain.add(genesisBlock)

    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")

    val tx2 = wallet1.sendFundsTo(
            recipient = wallet2.publicKey,
            amountToSend = 33
    )
    val secondBlock = blockChain.add(Block(genesisBlock.hash).addTransaction(tx2))

    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")
}