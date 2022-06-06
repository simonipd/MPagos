package com.example.mpagos.ui.selectedBank.data.repository.mapper

import com.example.mpagos.ui.selectedBank.data.entity.BankEntity
import com.example.mpagos.ui.selectedBank.domain.model.Bank


fun BankEntity.bankEntityToDomain(): Bank =
    Bank(
        id,
        name,
        secure_thumbnail,
        thumbnail,
        processing_mode,
        merchant_account_id,
        status
    )