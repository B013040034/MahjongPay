package com.kingwaytek.mahjongpay

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.kingwaytek.mahjongpay.adapter.MemberSelectAdapter
import com.kingwaytek.mahjongpay.model.Game
import com.kingwaytek.mahjongpay.model.Member
import com.kingwaytek.mahjongpay.model.MemberInGame
import com.kingwaytek.mahjongpay.util.SettingsManager
import kotlinx.android.synthetic.main.activity_new_game_setting_acitvity.*

class NewGameSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setting_acitvity)

        init()
        setListener()
    }

    private fun init() {
        val adapter = MemberSelectAdapter(this, Member.getNameList(SettingsManager.Data.getMemberList(this)))
        val adapter2 = MemberSelectAdapter(this, Member.getNameList(SettingsManager.Data.getMemberList(this)))
        val adapter3 = MemberSelectAdapter(this, Member.getNameList(SettingsManager.Data.getMemberList(this)))
        val adapter4 = MemberSelectAdapter(this, Member.getNameList(SettingsManager.Data.getMemberList(this)))
        spinner.adapter = adapter
        spinner2.adapter = adapter2
        spinner3.adapter = adapter3
        spinner4.adapter = adapter4
    }

    private fun setListener() {
        buttonStartGame.setOnClickListener {
            if(editText.text.toString().equals("") || editText2.text.toString().equals("")) {
                Toast.makeText(this, "請填寫底和台的金額", Toast.LENGTH_SHORT).show()
            } else {
                var memberList = mutableListOf(MemberInGame(spinner.selectedItem.toString()))
                memberList.add(MemberInGame(spinner2.selectedItem.toString()))
                memberList.add(MemberInGame(spinner3.selectedItem.toString()))
                memberList.add(MemberInGame(spinner4.selectedItem.toString()))
                val bundle = Bundle()
                bundle.putString("key", Gson().toJson(Game(editText.text.toString().toInt(), editText2.text.toString().toInt(), memberList)))
                var intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", bundle)
                startActivity(intent)
            }
        }
    }
}
