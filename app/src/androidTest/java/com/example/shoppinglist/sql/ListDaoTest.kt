package com.example.shoppinglist.sql

import com.example.shoppinglist.ShoppingItem
import com.example.shoppinglist.ShoppingList
import com.example.shoppinglist.sql.LiveDataTestUtil.getValue
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Created by ignacy on 17.07.18.
 */
class ListDaoTest : DbTest() {

    @Test
    fun fetchesList() {
        val scores = getValue(db.listDao()
                .fetch())
        assertTrue(scores.isEmpty())
    }

    @Test
    fun insertsAndFetchesList() {
        val shoppingList = ShoppingList(0, 1L, listOf(ShoppingItem("mleko"), ShoppingItem("chleb")), false)
        db.listDao().persist(shoppingList)
        val list = getValue(db.listDao()
                .fetch())
        assertEquals(shoppingList, list.get(0))
    }

    @Test
    fun insertsAndDeletesList() {
        val shoppingList = ShoppingList(0, 1L, listOf(ShoppingItem("mleko"), ShoppingItem("chleb")), false)
        db.listDao().persist(shoppingList)
        var list = getValue(db.listDao()
                .fetch())
        assertEquals(shoppingList, list.get(0))
        db.listDao().delete(shoppingList)
        list = getValue(db.listDao()
                .fetch())
        assertTrue(list.isEmpty())

    }

    @Test
    fun clearsDb() {
        val shoppingList = ShoppingList(0, 1L, listOf(ShoppingItem("mleko"), ShoppingItem("chleb")), false)
        db.listDao().persist(shoppingList)
        db.listDao().clear()
        val list = getValue(db.listDao()
                .fetch())
        assertTrue(list.isEmpty())


    }

    @Test
    fun changesArchivedStatus() {
        val shoppingList = ShoppingList(0, 1L, listOf(ShoppingItem("mleko"), ShoppingItem("chleb")), false)
        db.listDao().persist(shoppingList)
        val archivedShoppingList = with(shoppingList) { copy(isArchived = !isArchived) }
        db.listDao().persist(archivedShoppingList)
        val list = getValue(db.listDao()
                .fetch())
        assertTrue(list.get(0).isArchived)
    }


}