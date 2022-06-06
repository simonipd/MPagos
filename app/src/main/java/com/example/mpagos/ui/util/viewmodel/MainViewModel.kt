package com.example.mpagos.ui.util.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpagos.ui.main.domain.model.PaymentMethod
import com.example.mpagos.ui.main.domain.model.PaymentMethodElement
import com.example.mpagos.ui.main.domain.usecase.GetPaymentMethodUseCase
import com.example.mpagos.ui.selectedBank.domain.model.Bank
import com.example.mpagos.ui.selectedBank.domain.usecase.GetBankUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPaymentMethodUseCase: GetPaymentMethodUseCase,
    private val getBankUseCase: GetBankUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    data class UiState(
        var amount: String? = null,
        var paymentMethod: PaymentMethodElement? = null,
        var bank: Bank? = null,

        var listPaymentMethodElement: List<PaymentMethodElement>? = null,
        var listBank: List<Bank>? = null
    )

    fun setAmount(_amount: String){
        _state.value = _state.value.copy(amount = _amount)
    }

    fun setPaymentMethod(_paymentMethod: PaymentMethodElement){
        _state.value = _state.value.copy(paymentMethod = _paymentMethod)
    }

    fun setBank(_bank: Bank){
        _state.value = _state.value.copy(bank = _bank)
    }

    fun getPaymentMethod() {
        viewModelScope.launch {
            getPaymentMethodUseCase.invoke().let {
                _state.value = _state.value.copy(listPaymentMethodElement = it)
                Log.i("getPaymentMethod()", it.toString())
            }
        }
    }

    fun getBank(){
        viewModelScope.launch {
            getBankUseCase.setData("visa").invoke().let { _it ->
                Log.i("getPaymentMethod()", _it.toString())
                _state.value = _state.value.copy(listBank = _it)
            }
        }
    }

}