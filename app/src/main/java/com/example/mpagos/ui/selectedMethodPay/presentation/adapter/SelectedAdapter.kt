package com.example.mpagos.ui.selectedMethodPay.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mpagos.R
import com.example.mpagos.databinding.ItemDataBinding
import com.example.mpagos.ui.main.domain.model.PaymentMethodElement


class SelectedAdapter(val context: Context, var dataSource: List<PaymentMethodElement>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_data, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = dataSource.get(position).name

        Glide.with(vh.binding.root)
            .load(dataSource.get(position).secureThumbnail)
            .error(R.drawable.gif_loader)
            .fallback(R.drawable.gif_loader)
            .into(vh.img)

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position]
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private class ItemHolder(row: View?) {
        val label: TextView
        val img: ImageView
        val binding = ItemDataBinding.bind(row!!)

        init {
            label = row?.findViewById(R.id.tvBank) as TextView
            img = row.findViewById(R.id.ivBank) as ImageView
        }
    }

}