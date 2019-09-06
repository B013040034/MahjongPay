package com.kingwaytek.mahjongpay

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kingwaytek.mahjongpay.databinding.ActivityGameBinding
import com.kingwaytek.mahjongpay.model.Game
import com.kingwaytek.mahjongpay.model.Member
import com.kingwaytek.mahjongpay.model.MemberInGame
import com.kingwaytek.mahjongpay.util.SettingsManager
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.item_game_member_status.view.*

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    lateinit var game: Game
    var host = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)
        init()
        setListener()
    }

    fun init() {
        game = if(intent.extras != null && intent.extras.getBundle("name") != null) {
            var string = intent.extras.getBundle("name").getString("key")
            Gson().fromJson<Game>(string, object : TypeToken<Game>() {}.type)
        } else {
            SettingsManager.Data.getGameRecord(this)
        }
        editText.setText(game.base.toString())
        editText2.setText(game.plus.toString())
        binding.member1 = game.memberList[0]
        binding.member2 = game.memberList[1]
        binding.member3 = game.memberList[2]
        binding.member4 = game.memberList[3]
        binding.host = 0
        SettingsManager.Data.saveGame(this, game)
    }

    fun setListener() {

    }
}
