package com.kingwaytek.mahjongpay.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.kingwaytek.mahjongpay.R
import com.kingwaytek.mahjongpay.databinding.ItemMemberSpinnerBinding
import com.kingwaytek.mahjongpay.model.Member

/**
 * Created by AslanYan on 2018/6/26.
 */

class MemberSelectAdapter(context: Context, objects: MutableList<String>) : ArrayAdapter<String>(context, R.layout.item_member_spinner, objects) {
    lateinit var itemBinding: ItemMemberSpinnerBinding
    var memberList: MutableList<String>? = null

    init {
        memberList = objects
    }

    override fun getDropDownView(position: Int, convertView: View?,
                                 parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemBinding = DataBindingUtil.inflate<ItemMemberSpinnerBinding>(layoutInflater, R.layout.item_member_spinner, parent, false)
        itemBinding.text = memberList?.get(position)
        return itemBinding.root
    }
}
