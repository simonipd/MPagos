package com.example.mpagos.ui.selectedFee.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mpagos.R
import com.example.mpagos.databinding.FragmentSelectedFeeBinding
import com.example.mpagos.ui.selectedBank.domain.model.Bank
import com.example.mpagos.ui.selectedFee.domain.model.Fee
import com.example.mpagos.ui.selectedFee.util.selectedItem
import com.example.mpagos.ui.selectedFee.util.validateFee
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
    var _listFee: List<Fee>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
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
}