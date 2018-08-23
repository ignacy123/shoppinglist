package com.example.shoppinglist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shoppinglist_item.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val testList = arrayListOf("mleko", "cukier", "chleb")
        rc_shopping_list.layoutManager = LinearLayoutManager(this)
        rc_shopping_list.adapter = ShoppingAdapter(testList, this)

    }
}
