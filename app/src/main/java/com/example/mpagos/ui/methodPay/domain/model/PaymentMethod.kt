package com.example.mpagos.ui.main.domain.model

typealias PaymentMethod = ArrayList<PaymentMethodElement>

data class PaymentMethodElement (
    val id: String,
    val name: String,
    val paymentTypeId: String,
    val status: String,
    val secureThumbnail: String,
    val thumbnail: String,
    val deferredCapture: String,
    val settings: List<Setting>,
    val additionalInfoNeeded: List<String>,
    val minAllowedAmount: Long,
    val maxAllowedAmount: Long,
    val accreditationTime: Long,
    val financialInstitutions: List<String> = emptyList(),
    val processingModes: List<String>
)

data class Setting (
    val cardNumber: CardNumber,
    val bin: Bin,
    val securityCode: SecurityCode
)

data class Bin (
    val pattern: String,
    val installmentsPattern: String,
    val exclusionPattern: String? = null
)

data class CardNumber (
    val validation: String,
    val length: Long
)

data class SecurityCode (
    val length: Long,
    val cardLocation: String,
    val mode: String
)