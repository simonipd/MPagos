package com.example.mpagos.util.presentation.viewmodel

import CoroutinesTestRule
import app.cash.turbine.test
import com.example.mpagos.testShared.samplePaymentMethod
import com.example.mpagos.ui.main.domain.usecase.GetPaymentMethodUseCase
import com.example.mpagos.ui.selectedBank.domain.usecase.GetBankUseCase
import com.example.mpagos.ui.selectedQuota.domain.usecase.GetQuotaUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getPaymentMethodUseCase: GetPaymentMethodUseCase

    @Mock
    lateinit var getBankUseCase: GetBankUseCase

    @Mock
    lateinit var getPayerCost: GetQuotaUseCase

    private lateinit var vm: MainViewModel

    private val listPaymentMethodElement = listOf(samplePaymentMethod.copy(id = "argencard"))

    @Before
    suspend fun setup() {
        whenever(getPaymentMethodUseCase()).thenReturn(listPaymentMethodElement)
        vm = MainViewModel(getPaymentMethodUseCase, getBankUseCase, getPayerCost)
    }

    //****************************************************************
    /// Get PaymentMethod
    //****************************************************************

    @Test
    fun getPaymentMethod() = runTest {
        vm.getPaymentMethod()

        vm.state.test {
            assertEquals(MainViewModel.UiState(), awaitItem())
            assertEquals(
                MainViewModel.UiState(listPaymentMethodElement = listPaymentMethodElement),
                awaitItem()
            )
            cancel()
        }
    }

    @Test
    fun `Payments are requested when UI screen starts`() = runTest {
        vm.getPaymentMethod()
        runCurrent()

        verify(getPaymentMethodUseCase).invoke()
    }

    //****************************************************************
    /// Get banks
    //****************************************************************

    @Test
    fun getBank() = runTest {
        vm.getBank()

        vm.state.test {
            assertEquals(MainViewModel.UiState(), awaitItem())
            assertEquals(
                MainViewModel.UiState(bank = null),
                awaitItem()
            )
            cancel()
        }
    }

    @Test
    fun `Banks are requested`() = runTest {
        vm.getBank()
        runCurrent()

        verify(getBankUseCase).invoke()
    }

    //****************************************************************
    /// Get Quota
    //****************************************************************

    @Test
    fun getQuota() = runTest {
        vm.getQuota()

        vm.state.test {
            assertEquals(MainViewModel.UiState(), awaitItem())
            assertEquals(
                MainViewModel.UiState(bank = null),
                awaitItem()
            )
            cancel()
        }
    }

    @Test
    fun `Quota are requested`() = runTest {
        vm.getBank()
        runCurrent()

        verify(getPayerCost).invoke()
    }

}