package com.example.mpagos.ui.selectedFee.data.repository

import com.example.mpagos.ui.selectedFee.domain.model.Fee

interface FeeRepository {
    suspend fun getFee(amount: String, payment_method_id: String): List<Fee>
}
