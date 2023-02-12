package com.example.mpagos.ui.selectedPayerCost.data.repository.mapper

import com.example.mpagos.ui.selectedPayerCost.data.entity.FeeEntity
import com.example.mpagos.ui.selectedPayerCost.data.entity.IssuerEntity
import com.example.mpagos.ui.selectedPayerCost.data.entity.PayerCostEntity
import com.example.mpagos.ui.selectedPayerCost.domain.model.Fee
import com.example.mpagos.ui.selectedPayerCost.domain.model.Issuer
import com.example.mpagos.ui.selectedPayerCost.domain.model.PayerCost


fun FeeEntity.bankEntityToDomain(): Fee =
    Fee(
        payment_method_id,
        payment_type_id,
        issuer.issuerEntityToDomain(),
        processing_mode,
        merchant_account_id,
        payer_costs.map { it.payerCostEntityToDomain() },
        agreements
    )

fun IssuerEntity.issuerEntityToDomain(): Issuer =
    Issuer(
        id,
        name,
        secure_thumbnail,
        thumbnail
    )

fun PayerCostEntity.payerCostEntityToDomain(): PayerCost =
    PayerCost(
        installments,
        installment_rate,
        discount_rate,
        reimbursement_rate,
        labels,
        installment_rate_collector,
        min_allowed_amount,
        max_allowed_amount,
        recommended_message,
        installment_amount,
        total_amount,
        payment_method_option_id
    )