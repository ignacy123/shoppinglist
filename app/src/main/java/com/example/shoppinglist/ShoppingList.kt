package com.example.shoppinglist

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by ignacy on 17.07.18.
 */
@Entity
data class ShoppingList(val date: Long, @PrimaryKey(autoGenerate = true) val id: Long = 0L, val items: List<ShoppingItem>, val isArchived: Boolean, val name:String)