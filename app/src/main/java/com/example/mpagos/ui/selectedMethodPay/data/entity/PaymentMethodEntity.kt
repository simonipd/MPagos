package com.example.mpagos.ui.main.data.entity

typealias PaymentMethodEntity = ArrayList<PaymentMethodElementEntity>

data class PaymentMethodElementEntity (
    val id: String,
    val name: String,
    val payment_type_id: String,
    val status: String,
    val secure_thumbnail: String,
    val thumbnail: String,
    val deferred_capture: String,
    val settings: List<SettingEntity>,
    val additional_info_needed: List<String>,
    val min_allowed_amount: Long,
    val max_allowed_amount: Long,
    val accreditation_time: Long,
    val financial_institutions: List<String> = emptyList(),
    val processing_modes: List<String>
)

data class SettingEntity (
    val card_number: CardNumberEntity,
    val bin: BinEntity,
    val security_code: SecurityCodeEntity
)

data class BinEntity (
    val pattern: String,
    val installments_pattern: String,
    val exclusion_pattern: String? = null
)

data class CardNumberEntity (
    val validation: String,
    val length: Long
)

data class SecurityCodeEntity (
    val length: Long,
    val card_location: String,
    val mode: String
)

