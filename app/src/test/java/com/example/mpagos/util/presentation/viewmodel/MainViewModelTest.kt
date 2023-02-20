package com.example.mpagos.util.presentation.viewmodel

import app.cash.turbine.test
import com.example.mpagos.ui.main.domain.model.PaymentMethodElement
import com.example.mpagos.ui.main.domain.usecase.GetPaymentMethodUseCase
import com.example.mpagos.ui.selectedBank.domain.usecase.GetBankUseCase
import com.example.mpagos.ui.selectedPayerCost.domain.usecase.GetPayerCostUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    lateinit var getPaymentMethodUseCase: GetPaymentMethodUseCase

    @Mock
    lateinit var getBankUseCase: GetBankUseCase

    @Mock
    lateinit var getPayerCost: GetPayerCostUseCase


    private lateinit var vm: MainViewModel

    private val listPaymentMethodElement = listOf<PaymentMethodElement>()

    @Before
    suspend fun setup() {
        whenever(getPaymentMethodUseCase()).thenReturn(listPaymentMethodElement)
        vm = MainViewModel(getPaymentMethodUseCase, getBankUseCase, getPayerCost)
    }

    @Test
    fun getBanks() = runTest {
        vm.state.test {
            assertEquals(MainViewModel.UiState(), awaitItem())
            assertEquals(MainViewModel.UiState(listPaymentMethodElement = listPaymentMethodElement), awaitItem())
            cancel()
        }
    }


}