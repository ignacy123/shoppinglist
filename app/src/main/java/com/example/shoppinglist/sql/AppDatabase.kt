package com.example.shoppinglist.sql

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.shoppinglist.ShoppingList

/**
 * Created by ignacy on 17.07.18.
 */
@Database(entities = arrayOf(ShoppingList::class), version = 1)
@TypeConverters(ShoppingItemConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun listDao(): ListDao

    companion object {

        private val instance: AppDatabase? = null
    }

}