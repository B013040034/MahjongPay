package com.kingwaytek.mahjongpay.model

/**
 * Created by AslanYan on 2018/2/23.
 */
open class Member(
    var name: String?
) {
    companion object {
        fun getNameList (memberList :MutableList<Member>): MutableList<String> {
            var list = mutableListOf<String>()
            for(i in 0 until memberList.size) {
                list.add(memberList[i].name!!)
            }
            return list
        }
    }
}
