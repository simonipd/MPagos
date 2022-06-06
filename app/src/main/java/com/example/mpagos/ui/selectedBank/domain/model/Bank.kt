package com.example.mpagos.ui.selectedBank.domain.model

data class Bank (
    val id: String,
    val name: String,
    val secureThumbnail: String,
    val thumbnail: String,
    val processingMode: String,
    val merchantAccountID: Any? = null,
    val status: String
)