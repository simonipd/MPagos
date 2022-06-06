package com.example.mpagos.ui.selectedFee.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mpagos.R
import com.example.mpagos.databinding.FragmentSelectedFeeBinding
import com.example.mpagos.ui.main.domain.model.PaymentMethodElement
import com.example.mpagos.ui.selectedBank.domain.model.Bank
import com.example.mpagos.ui.selectedFee.domain.model.Fee
import com.example.mpagos.ui.selectedFee.presentation.adapter.SelectedAdapter
import com.example.mpagos.ui.util.FunctionsUtils.Companion.launchAndCollect
import com.example.mpagos.ui.util.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectedFeeFragment : Fragment() {

    companion object {
        fun newInstance() = SelectedFeeFragment()
    }

    val viewModel: MainViewModel by activityViewModels()
    var _binding: FragmentSelectedFeeBinding? = null
    val binding get() = _binding
    lateinit var mContext: Context
    var _listBank: List<Bank>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            activity?.actionBar?.setTitle(R.string.fragment_title_selected_bank)
            _binding = FragmentSelectedFeeBinding.inflate(inflater)
            binding?.root?.context.also { mContext = it!! }
            viewModel.getFee()
            observer()
            selectedItem()
        }
        return binding!!.root
    }

    private fun observer() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            validateFee(it.paymentMethod, it.bank, it.listFee)
        }
    }

    private fun validateFee(
        paymentMethod: PaymentMethodElement?,
        bank: Bank?,
        listFee: List<Fee>?
    ) {
        val findListfee =
            listFee?.find { it.paymentMethodID.equals(paymentMethod?.id) && it.issuer.id.equals(bank?.id) }
        findListfee?.let {
            val feeAdapter = SelectedAdapter(binding!!.root.context, findListfee.payerCosts, findListfee.issuer.secureThumbnail)
            binding!!.spinnerSelectedMethod.adapter = feeAdapter
        }
        Log.i("validateFee", findListfee.toString())
    }

    private fun selectedItem() {
        binding!!.spinnerSelectedMethod.onItemSelectedListener = (object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                view?.let {
                    _listBank?.let {
                        viewModel.setBank(it[position])
                        binding!!.btnSave.isEnabled = true
                        parent?.setSelection(position)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }


}