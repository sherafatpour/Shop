package net.sherafatpour.shop.model.orders

data class OrderModelItem(
    val Authority: String,
    val code_pardakht: String,
    val id: String,
    val idaddress: String,
    val iduser: String,
    val price: String,
    val status: String
)