package com.example.mpagos.ui.main.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mpagos.R
import com.example.mpagos.databinding.FragmentMainBinding
import com.example.mpagos.util.FunctionsUtils.Companion.launchAndCollect
import com.example.mpagos.util.FunctionsUtils.Companion.toastDefault
import com.example.mpagos.util.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentMainBinding.inflate(inflater)
            binding?.root?.context.also { mContext = it!! }
            viewModel.getPaymentMethod()
            observer()
            binding?.btnSave?.setOnClickListener {
               if (!binding!!.tieEditReceivesName.text!!.isEmpty()){
                   viewModel.setAmount(binding!!.tieEditReceivesName.text.toString())
                   findNavController().navigate(R.id.selectedMethodPayFragment)
               }else{
                   toastDefault(getString(R.string.msj_toast_invalid_message), requireActivity())
               }
            }
        }
        return binding!!.root
    }

    private fun observer() {
        viewLifecycleOwner.launchAndCollect(viewModel.state){ response ->
            if (response.payerCost!=null){
                toastDefault(getString(R.string.msj_toast_pay_successful),requireActivity())
                viewModel.setPayerCost(null)
            }
        }
    }

}