package com.example.mpagos.ui.selectedQuota.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mpagos.R
import com.example.mpagos.databinding.FragmentSelectedFeeBinding
import com.example.mpagos.ui.selectedQuota.domain.model.PayerCostResponse
import com.example.mpagos.ui.selectedQuota.domain.model.PayerCost
import com.example.mpagos.ui.selectedQuota.util.selectedItem
import com.example.mpagos.ui.selectedQuota.util.validateFee
import com.example.mpagos.util.FunctionsUtils.Companion.launchAndCollect
import com.example.mpagos.util.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PayerCostFragment : Fragment() {

    companion object {
        fun newInstance() = PayerCostFragment()
    }

    val viewModel: MainViewModel by activityViewModels()
    var _binding: FragmentSelectedFeeBinding? = null
    val binding get() = _binding
    lateinit var mContext: Context
    var listFee: List<PayerCostResponse>? = null
    var payerCosts: List<PayerCost>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentSelectedFeeBinding.inflate(inflater)
            binding?.root?.context.also { mContext = it!! }
            viewModel.getQuota()
            observer()
            selectedItem()
            binding?.btnSave?.setOnClickListener {
                findNavController().navigate(R.id.mainFragment)
            }
        }
        return binding!!.root
    }

    private fun observer() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            validateFee(it.paymentMethod, it.bank, it.listFee)
        }
    }
}