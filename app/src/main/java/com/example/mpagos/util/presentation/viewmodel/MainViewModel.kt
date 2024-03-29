package com.example.mpagos.util.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpagos.ui.main.domain.model.PaymentMethodElement
import com.example.mpagos.ui.main.domain.usecase.GetPaymentMethodUseCase
import com.example.mpagos.ui.selectedBank.domain.model.Bank
import com.example.mpagos.ui.selectedBank.domain.usecase.GetBankUseCase
import com.example.mpagos.ui.selectedQuota.domain.model.PayerCostResponse
import com.example.mpagos.ui.selectedQuota.domain.model.PayerCost
import com.example.mpagos.ui.selectedQuota.domain.usecase.GetQuotaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPaymentMethodUseCase: GetPaymentMethodUseCase,
    private val getBankUseCase: GetBankUseCase,
    private val getFeeUseCase: GetQuotaUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    data class UiState(
        var amount: String? = null,
        var paymentMethod: PaymentMethodElement? = null,
        var bank: Bank? = null,
        var payerCost: PayerCost? = null,
        var listPaymentMethodElement: List<PaymentMethodElement>? = null,
        var listBank: List<Bank>? = null,
        var listFee: List<PayerCostResponse>? = null
    )

    fun setAmount(_amount: String) {
        _state.value = _state.value.copy(amount = _amount)
    }

    fun setPaymentMethod(_paymentMethod: PaymentMethodElement) {
        _state.value = _state.value.copy(paymentMethod = _paymentMethod)
    }

    fun setBank(_bank: Bank) {
        _state.value = _state.value.copy(bank = _bank)
    }

    fun setPayerCost(_payerCost: PayerCost?) {
        if (_payerCost != null)
            _state.value = _state.value.copy(payerCost = _payerCost)
        else
            _state.value = _state.value.copy(payerCost = null)
    }

    fun getPaymentMethod() {
        viewModelScope.launch {
            getPaymentMethodUseCase.invoke().let {
                _state.value = _state.value.copy(listPaymentMethodElement = it)
                Log.i("getPaymentMethod()", it.toString())
            }
        }
    }

    fun getBank() {
        viewModelScope.launch {
            _state.value.paymentMethod?.let { _paymentMethod ->
                getBankUseCase.setData(_paymentMethod.id).invoke().let { _it ->
                    _state.value = _state.value.copy(listBank = _it)
                    Log.i("getPaymentMethod()", _it.toString())
                }
            }

        }
    }

    fun getQuota() {
        viewModelScope.launch {
            _state.value.amount?.let { _amount ->
                _state.value.paymentMethod?.let { _paymentMethod ->
                    getFeeUseCase.setData(_amount, _paymentMethod.id).invoke().let { _fee ->
                        _state.value = _state.value.copy(listFee = _fee)
                    }
                }
            }
        }
    }

}