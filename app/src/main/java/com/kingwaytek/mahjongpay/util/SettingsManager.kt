package com.kingwaytek.mahjongpay.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kingwaytek.mahjongpay.model.Game
import com.kingwaytek.mahjongpay.model.Member
import com.kingwaytek.mahjongpay.model.MemberInGame

object SettingsManager {
    internal var TAG = "SettingsManager"
    private val PREFS_NAME_COMMON_SETTINGS = "COMMON_SETTINGS"
    private var mSettingPrefs: SharedPreferences? = null

    fun clearSharedPreferences(context: Context) {
        val editor = getInstance(context).edit()
        editor.clear().apply()
    }

    private fun getInstance(context: Context?): SharedPreferences {
        if (mSettingPrefs == null) {
            mSettingPrefs = context!!.getSharedPreferences(
                PREFS_NAME_COMMON_SETTINGS, Context.MODE_PRIVATE)
        }
        return mSettingPrefs!!
    }

    object Data {
        val MEMBER = "member"
        val GAME = "game"

        fun setMemberList(context: Context?, list: MutableList<Member>) {
            val editor = getInstance(context).edit()
            editor.putString(MEMBER, Gson().toJson(list))
            editor.apply()
        }

        fun getMemberList(context: Context): MutableList<Member> {
            val preference = getInstance(context)
            var result = preference.getString(MEMBER, null)
            return if(result != null) {
                Gson().fromJson<MutableList<Member>>(result, object : TypeToken<MutableList<Member>>() { }.type)
            } else {
                mutableListOf()
            }
        }

        fun saveGame(context: Context?, game: Game) {
            val editor = getInstance(context).edit()
            editor.putString(GAME, Gson().toJson(game))
            editor.apply()
        }

        fun getGameRecord(context: Context): Game {
            val preference = getInstance(context)
            var result = preference.getString(GAME, null)
            return if(result != null) {
                Gson().fromJson<Game>(result, object : TypeToken<Game>() { }.type)
            } else {
                Game(0, 0, mutableListOf())
            }
        }
    }
}