package com.example.mpagos.ui.selectedFee.util

import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.example.mpagos.ui.main.domain.model.PaymentMethodElement
import com.example.mpagos.ui.selectedBank.domain.model.Bank
import com.example.mpagos.ui.selectedFee.domain.model.Fee
import com.example.mpagos.ui.selectedFee.presentation.adapter.SelectedAdapter
import com.example.mpagos.ui.selectedFee.presentation.fragment.SelectedFeeFragment

fun SelectedFeeFragment.validateFee(
    paymentMethod: PaymentMethodElement?,
    bank: Bank?,
    _listFee: List<Fee>?
) {
    listFee = _listFee
    val findListfee =
        listFee?.find { it.paymentMethodID.equals(paymentMethod?.id) && it.issuer.id.equals(bank?.id) }
    findListfee?.let {
        val feeAdapter = SelectedAdapter(
            binding!!.root.context,
            findListfee.payerCosts,
            findListfee.issuer.secureThumbnail
        )
        binding!!.spinnerSelectedMethod.adapter = feeAdapter
    }
}

fun SelectedFeeFragment.selectedItem() {
    binding!!.spinnerSelectedMethod.onItemSelectedListener = (object :
        AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            view?.let {
                listFee?.let {
                    viewModel.setFee(it[position])
                    binding!!.btnSave.isEnabled = true
                    parent?.setSelection(position)
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    })
}