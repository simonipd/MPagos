package com.example.mpagos.ui.selectedBank.data.entity

data class BankEntity (
    val id: String,
    val name: String,
    val secure_thumbnail: String,
    val thumbnail: String,
    val processing_mode: String,
    val merchant_account_id: Any? = null,
    val status: String
)