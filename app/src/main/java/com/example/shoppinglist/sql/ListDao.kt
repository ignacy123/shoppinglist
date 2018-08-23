package com.example.shoppinglist.sql

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.shoppinglist.ShoppingList

/**
 * Created by ignacy on 17.07.18.
 */
@Dao
interface ListDao {

    @Query("DELETE FROM ShoppingList")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun persist(list: ShoppingList)

    @Query("SELECT * FROM ShoppingList")
    fun fetch(): LiveData<List<ShoppingList>>

    @Delete
    fun delete(list: ShoppingList)

}