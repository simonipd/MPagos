package com.example.mpagos.ui.selectedBank.presentation.fragment

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
import com.example.mpagos.databinding.FragmentSelectedBankBinding
import com.example.mpagos.ui.selectedBank.domain.model.Bank
import com.example.mpagos.util.FunctionsUtils.Companion.launchAndCollect
import com.example.mpagos.ui.selectedBank.presentation.adapter.SelectedAdapter
import com.example.mpagos.util.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectedBankFragment : Fragment() {

    companion object {
        fun newInstance() = SelectedBankFragment()
    }

    val viewModel: MainViewModel by activityViewModels()
    var _binding: FragmentSelectedBankBinding? = null
    val binding get() = _binding
    lateinit var mContext: Context
    var _listBank: List<Bank>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentSelectedBankBinding.inflate(inflater)
            binding?.root?.context.also { mContext = it!! }
            viewModel.getBank()
            observer()
            selectedItem()
            binding?.btnSave?.setOnClickListener {
                findNavController().navigate(R.id.selectedFeeFragment)
            }
        }
        return binding!!.root
    }

    private fun observer() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            it.listBank?.let {
                _listBank = it
                val bankAdapter = SelectedAdapter(binding!!.root.context, _listBank!!)
                binding!!.spinnerSelectedMethod.adapter = bankAdapter
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
                    _listBank?.let {
                        viewModel.setBank(it[position])
                        binding!!.btnSave.isEnabled = true
                        parent?.setSelection(position)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        })
    }


}