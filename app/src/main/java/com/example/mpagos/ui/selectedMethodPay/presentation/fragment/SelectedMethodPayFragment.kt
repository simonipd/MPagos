package com.example.mpagos.ui.selectedMethodPay.presentation.fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mpagos.R
import com.example.mpagos.databinding.FragmentSelectedMethodPayBinding
import com.example.mpagos.ui.main.domain.model.PaymentMethodElement
import com.example.mpagos.ui.selectedMethodPay.presentation.adapter.SelectedAdapter
import com.example.mpagos.util.FunctionsUtils.Companion.launchAndCollect
import com.example.mpagos.util.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectedMethodPayFragment : Fragment() {

    companion object {
        fun newInstance() = SelectedMethodPayFragment()
    }

    val viewModel: MainViewModel by activityViewModels()
    var _binding: FragmentSelectedMethodPayBinding? = null
    val binding get() = _binding
    lateinit var mContext: Context
    lateinit var paymentAdapter: SelectedAdapter
    var _listPaymentMethodElement: List<PaymentMethodElement>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            activity?.actionBar?.setTitle(R.string.fragment_title_selected_method_pay)
            _binding = FragmentSelectedMethodPayBinding.inflate(inflater)
            binding?.root?.context.also { mContext = it!! }
            viewModel.getPaymentMethod()
            observer()
            selectedItem()
            binding?.btnSave?.setOnClickListener {
                findNavController().navigate(R.id.selectedBankFragment)
            }
        }
        return binding!!.root
    }

    private fun observer() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            it.listPaymentMethodElement?.let {
                _listPaymentMethodElement = it
                paymentAdapter =
                    SelectedAdapter(binding!!.root.context, _listPaymentMethodElement!!)
                binding!!.spinnerSelectedMethod.adapter = paymentAdapter
            }
        }
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
                    _listPaymentMethodElement?.let {
                        viewModel.setPaymentMethod(it[position])
                        parent?.setSelection(position)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }
}