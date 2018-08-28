package com.example.shoppinglist

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.shoppinglist.sql.AppDatabase
import kotlinx.android.synthetic.main.activity_list_adding.*

class ListAddingActivity : AppCompatActivity() {
    var itemsListHelper: MutableList<ShoppingItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_adding)
    }

    fun addItemToHelperList(view: View) {
        itemsListHelper.add(ShoppingItem(item.text.toString()))
        Toast.makeText(this, "${item.text.toString()} has been added", Toast.LENGTH_SHORT).show()
        item.setText("")
    }

    fun saveList(view: View) {
        val shoppingList: ShoppingList = ShoppingList(
                date = System.currentTimeMillis(),
                items = itemsListHelper,
                isArchived = false,
                name = name.text.toString()
        )
        val dao = Room.databaseBuilder(this, AppDatabase::class.java, "ShoppingList").allowMainThreadQueries().build()
        dao.listDao().persist(shoppingList)
        itemsListHelper.clear()
        Toast.makeText(this, "${name.text.toString()} has been saved", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, MainActivity::class.java))
    }


}
