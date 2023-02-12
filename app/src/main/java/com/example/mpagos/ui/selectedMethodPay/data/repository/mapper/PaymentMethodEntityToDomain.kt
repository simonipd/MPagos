package com.example.mpagos.ui.main.data.reporitory.mapper

import com.example.mpagos.R
import com.example.mpagos.ui.main.data.entity.*
import com.example.mpagos.ui.main.domain.model.*




//fun PaymentMethodEntity = ArrayList<PaymentMethodElementEntity>
/*fun PaymentMethodEntity.paymentMethodEntityToDomain(): PaymentMethod =
    PaymentMethod()*/

fun PaymentMethodElementEntity.paymentMethodElementEntityToDomain(): PaymentMethodElement =
    PaymentMethodElement(
        id,
        name,
        payment_type_id,
        status,
        secure_thumbnail,
        thumbnail,
        deferred_capture,
        settings.map { it.settingsEntityToDomain() },
        additional_info_needed,
        min_allowed_amount,
        max_allowed_amount,
        accreditation_time,
        financial_institutions,
        processing_modes
    )

fun SettingEntity.settingsEntityToDomain(): Setting =
    Setting(
        card_number.cardNumberEntityToDomain(),
        bin.binEntityToDomain(),
        security_code.securityCodeEntity()
    )

fun CardNumberEntity.cardNumberEntityToDomain(): CardNumber =
    CardNumber(
        validation,
        length
    )

fun BinEntity.binEntityToDomain(): Bin =
    Bin(
        pattern,
        installments_pattern,
        exclusion_pattern
    )

fun SecurityCodeEntity.securityCodeEntity(): SecurityCode =
    SecurityCode(
        length,
        card_location,
        mode
    )
