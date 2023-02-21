package com.example.mpagos.ui.selectedQuota.domain.model

data class PayerCostResponse (
    val paymentMethodID: String,
    val paymentTypeID: String,
    val issuer: Issuer,
    val processingMode: String,
    val merchantAccountID: Any? = null,
    val payerCosts: List<PayerCost>,
    val agreements: Any? = null
)

data class Issuer (
    val id: String,
    val name: String,
    val secureThumbnail: String,
    val thumbnail: String
)

data class PayerCost (
    val installments: Long,
    val installmentRate: Double,
    val discountRate: Long,
    val reimbursementRate: Any? = null,
    val labels: List<String>,
    val installmentRateCollector: List<String>,
    val minAllowedAmount: Long,
    val maxAllowedAmount: Long,
    val recommendedMessage: String,
    val installmentAmount: Double,
    val totalAmount: Float,
    val paymentMethodOptionID: String
)