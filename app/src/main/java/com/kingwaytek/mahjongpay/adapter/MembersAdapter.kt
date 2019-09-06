package com.kingwaytek.mahjongpay.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kingwaytek.mahjongpay.R
import com.kingwaytek.mahjongpay.databinding.ItemMemberBinding
import com.kingwaytek.mahjongpay.model.Member

import java.util.Collections

/**
 * Created by AslanYan on 2018/3/2.
 */

class MembersAdapter(private val context: Context, memberList: MutableList<Member>) :
    RecyclerView.Adapter<MembersAdapter.ViewHolder>() {
    private var mMemberList: MutableList<Member> = memberList
    private var clickListener: RecyclerViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding =
            DataBindingUtil.inflate<ItemMemberBinding>(layoutInflater, R.layout.item_member, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mMemberList[position]
        holder.bind(item)
    }

    fun setClickListener(listener: RecyclerViewClickListener) {
        this.clickListener = listener
    }

    interface RecyclerViewClickListener {
        fun onItemClick(Member: Member)
    }

    fun refreshList(memberList: MutableList<Member>) {
        mMemberList = memberList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mMemberList.size
    }

    inner class ViewHolder(private val mBinding: ItemMemberBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item: Member) {
            mBinding.member = item
        }

    }

}
