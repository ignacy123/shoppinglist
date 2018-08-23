package com.example.shoppinglist

import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.shoppinglist.sql.AppDatabase
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val shoppingAdapter = ShoppingAdapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this);

        val dao = Room.databaseBuilder(this, AppDatabase::class.java, "ShoppingList").allowMainThreadQueries().build()
        dao.listDao().fetch().observe(this, Observer {
            if (it != null) {
                val ids = it.map { it.id.toString() }
                shoppingAdapter.items = ArrayList(ids)
                shoppingAdapter.notifyDataSetChanged()
            }
        })
        //TODO tutaj się wywala, nie mam pojęcia czemu
        rc_shopping_list.layoutManager = LinearLayoutManager(this)
        rc_shopping_list.adapter = shoppingAdapter



        fab.setOnClickListener {
            startActivity(Intent(this, ListAddingActivity::class.java))
        }

    }
}
