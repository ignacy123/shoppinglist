package com.example.shoppinglist.sql

import android.support.test.runner.AndroidJUnit4
import com.example.shoppinglist.ShoppingItem
import com.example.shoppinglist.ShoppingList
import com.example.shoppinglist.sql.LiveDataTestUtil.getValue
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by ignacy on 17.07.18.
 */
@RunWith(AndroidJUnit4::class)
class ListDaoTest : DbTest() {

    @Test
    fun fetchesList() {
        val scores = getValue(db.listDao()
                .fetch())
        assertTrue(scores.isEmpty())
    }

    @Test
    fun insertsAndFetchesList() {
        val shoppingList = ShoppingList(0, 1L, listOf(ShoppingItem("mleko"), ShoppingItem("chleb")), false, "test")
        db.listDao().persist(shoppingList)
        val list = getValue(db.listDao()
                .fetch())
        assertEquals(shoppingList, list.get(0))
    }

    @Test
    fun insertsAndDeletesList() {
        val shoppingList = ShoppingList(0, 1L, listOf(ShoppingItem("mleko"), ShoppingItem("chleb")), false, "test")
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
        val shoppingList = ShoppingList(0, 1L, listOf(ShoppingItem("mleko"), ShoppingItem("chleb")), false, "test")
        db.listDao().persist(shoppingList)
        db.listDao().clear()
        val list = getValue(db.listDao()
                .fetch())
        assertTrue(list.isEmpty())


    }

    @Test
    fun changesArchivedStatus() {
        val shoppingList = ShoppingList(0, 1L, listOf(ShoppingItem("mleko"), ShoppingItem("chleb")), false, "test")
        db.listDao().persist(shoppingList)
        val archivedShoppingList = with(shoppingList) { copy(isArchived = !isArchived) }
        db.listDao().persist(archivedShoppingList)
        val list = getValue(db.listDao()
                .fetch())
        assertTrue(list.get(0).isArchived)
    }

    @Test
    fun autoIncrementWithIdEqualToZero() {
        val shoppingList = ShoppingList(0, 0L, listOf(ShoppingItem("mleko"), ShoppingItem("chleb")), false, "test1")
        val shoppingList2 = ShoppingList(0, 0L, listOf(ShoppingItem("mleko"), ShoppingItem("cukier")), false, "test2")
        db.listDao().persist(shoppingList)
        db.listDao().persist(shoppingList2)
        val list = getValue(db.listDao()
                .fetch())
        assertEquals(list.size, 2)

        val shoppingList3 = ShoppingList(0, 123L, listOf(ShoppingItem("mleko"), ShoppingItem("chleb")), false, "test1")
        val shoppingList4 = ShoppingList(0, 123L, listOf(ShoppingItem("mleko"), ShoppingItem("cukier")), false, "test2")
        db.listDao().persist(shoppingList3)
        db.listDao().persist(shoppingList4)
        val anotherList = getValue(db.listDao()
                .fetch())
        assertEquals(anotherList.size, 3)

    }


}