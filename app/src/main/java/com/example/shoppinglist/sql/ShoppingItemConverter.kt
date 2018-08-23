package com.example.shoppinglist.sql

import android.arch.persistence.room.TypeConverter
import com.example.shoppinglist.ShoppingItem

/**
 * Created by ignacy on 17.07.18.
 */
class ShoppingItemConverter {
    @TypeConverter
    fun stringToShoppingItems(string: String): List<ShoppingItem> = string.split("@").map { ShoppingItem(it) }


    @TypeConverter
    fun shoppingItemsToString(items: List<ShoppingItem>): String = items.joinToString("@", transform = {it.name} )
}