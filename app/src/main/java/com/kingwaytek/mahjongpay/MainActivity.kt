package com.kingwaytek.mahjongpay

import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kingwaytek.mahjongpay.model.Member
import com.kingwaytek.mahjongpay.util.SettingsManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(SettingsManager.Data.getGameRecord(this).memberList.size != 0) {
            buttonResumeGame.visibility = View.VISIBLE
        }
        setListener()
        setFakeData()

    }

    private fun setListener() {
        buttonMember.setOnClickListener {
            startActivity(Intent(this, MemberActivity::class.java))
        }
        buttonNewGame.setOnClickListener {
            startActivity(Intent(this, NewGameSettingActivity::class.java))
        }
        buttonResumeGame.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
    }
    private fun setFakeData() {
        var memberList = mutableListOf<Member>()
        memberList.add(Member("顏肇宏"))
        memberList.add(Member("邱宣凱"))
        memberList.add(Member("許書瑋"))
        memberList.add(Member("許傑"))
        SettingsManager.Data.setMemberList(this, memberList)
    }
}
