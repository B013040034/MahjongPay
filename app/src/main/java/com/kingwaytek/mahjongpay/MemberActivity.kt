package com.kingwaytek.mahjongpay

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.kingwaytek.mahjongpay.adapter.MembersAdapter
import com.kingwaytek.mahjongpay.util.RecycleViewBuilder
import kotlinx.android.synthetic.main.activity_member.*
import android.content.DialogInterface
import android.text.Editable
import android.widget.EditText
import com.kingwaytek.mahjongpay.model.Member
import com.kingwaytek.mahjongpay.util.SettingsManager


class MemberActivity : AppCompatActivity() {
    lateinit var memberList: MutableList<Member>
    lateinit var exchangeAdapter: MembersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)
        supportActionBar!!.title = "人員名單"
        init()
    }

    private fun init() {
        memberList = SettingsManager.Data.getMemberList(this)
        exchangeAdapter = MembersAdapter(this, memberList)
        val recycleViewBuilder = RecycleViewBuilder(recycleMember, exchangeAdapter, 1)
        recycleViewBuilder.build()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_map_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_new_member -> {
                showEditTextDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showEditTextDialog() {
        val alert = AlertDialog.Builder(this)
        val editText = EditText(this)
        alert.setTitle("輸入成員名稱")
        alert.setView(editText)
        alert.setPositiveButton("New") { dialog, whichButton ->
            memberList = SettingsManager.Data.getMemberList(this)
            memberList.add(Member(editText.text.toString()))
            exchangeAdapter.refreshList(memberList)
            SettingsManager.Data.setMemberList(this, memberList)
        }
        alert.setNegativeButton("Back") { dialog, whichButton -> }
        alert.show()
    }
}
