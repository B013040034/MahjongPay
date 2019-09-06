package com.kingwaytek.mahjongpay.model

import android.os.Parcelable

/**
 * Created by AslanYan on 2018/2/23.
 */
class MemberInGame(
    name: String,
    var money: Int = 0
): Member(name) {
    companion object {
        fun MemberListToMemberInGameList(memberList: MutableList<Member>): MutableList<MemberInGame> {
            var list = mutableListOf<MemberInGame>()
            for(i in 0 until memberList.size) {
                list.add(MemberInGame(memberList[i].name!!, 0))
            }
            return list
        }
    }
}
