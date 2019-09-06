package com.kingwaytek.mahjongpay.model

import android.os.Parcelable

/**
 * Created by AslanYan on 2018/2/23.
 */
class Game(
    var base: Int?,
    var plus: Int?,
    var memberList: MutableList<MemberInGame>
)
