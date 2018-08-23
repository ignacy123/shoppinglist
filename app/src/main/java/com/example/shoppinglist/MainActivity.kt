package com.example.shoppinglist

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.shoppinglist.sql.AppDatabase
import com.example.shoppinglist.sql.LiveDataTestUtil.getValue
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this);


        val dao = Room.databaseBuilder(this, AppDatabase::class.java, "ShoppingList").allowMainThreadQueries().build()
        val shoppingLists = getValue(dao.listDao().fetch())
        //TODO tutaj się wywala, nie mam pojęcia czemu
        val ids = shoppingLists.map { it.toString() }.toSet()
        rc_shopping_list.layoutManager = LinearLayoutManager(this)
        rc_shopping_list.adapter = ShoppingAdapter(ArrayList(ids), this)



        fab.setOnClickListener {
            dao.listDao().persist(ShoppingList(0, 0, listOf(ShoppingItem("cukier")), false))
        }

    }
}
