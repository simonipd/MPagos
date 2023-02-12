package com.example.mpagos.ui.selectedPayerCost.util

import android.view.View
import android.widget.AdapterView
import com.example.mpagos.ui.main.domain.model.PaymentMethodElement
import com.example.mpagos.ui.selectedBank.domain.model.Bank
import com.example.mpagos.ui.selectedPayerCost.domain.model.PayerCostResponse
import com.example.mpagos.ui.selectedPayerCost.presentation.adapter.SelectedAdapter
import com.example.mpagos.ui.selectedPayerCost.presentation.fragment.PayerCostFragment

fun PayerCostFragment.validateFee(
    paymentMethod: PaymentMethodElement?,
    bank: Bank?,
    _listFee: List<PayerCostResponse>?
) {
    listFee = _listFee
    val findListfee =
        listFee?.find { it.paymentMethodID.equals(paymentMethod?.id) && it.issuer.id.equals(bank?.id) }
    findListfee?.let {
        payerCosts = findListfee.payerCosts
        payerCosts?.let { payerCosts ->
            val feeAdapter = SelectedAdapter(
                binding!!.root.context,
                payerCosts,
                findListfee.issuer.secureThumbnail
            )
            binding!!.spinnerSelectedMethod.adapter = feeAdapter
        }
    }
}

fun PayerCostFragment.selectedItem() {
    binding!!.spinnerSelectedMethod.onItemSelectedListener = (object :
        AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            view?.let {
                payerCosts?.let { payerCosts ->
                    val value = payerCosts[position]
                    viewModel.setPayerCost(value)
                    binding!!.btnSave.isEnabled = true
                    parent?.setSelection(position)
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    })
}