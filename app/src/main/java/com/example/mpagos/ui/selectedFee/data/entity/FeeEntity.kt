package com.example.mpagos.ui.selectedFee.data.entity

data class FeeEntity (
    val payment_method_id: String,
    val payment_type_id: String,
    val issuer: IssuerEntity,
    val processing_mode: String,
    val merchant_account_id: Any? = null,
    val payer_costs: List<PayerCostEntity>,
    val agreements: Any? = null
)

data class IssuerEntity (
    val id: String,
    val name: String,
    val secure_thumbnail: String,
    val thumbnail: String
)

data class PayerCostEntity (
    val installments: Long,
    val installment_rate: Double,
    val discount_rate: Long,
    val reimbursement_rate: Any? = null,
    val labels: List<String>,
    val installment_rate_collector: List<String>,
    val min_allowed_amount: Long,
    val max_allowed_amount: Long,
    val recommended_message: String,
    val installment_amount: Double,
    val total_amount: Float,
    val payment_method_option_id: String
)