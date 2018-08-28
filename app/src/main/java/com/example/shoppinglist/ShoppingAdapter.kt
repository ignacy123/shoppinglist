package com.example.shoppinglist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.shoppinglist_item.view.*

/**
 * Created by ignacy on 23.08.18.
 */

class ShoppingAdapter(var items: ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.shoppinglist_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.let {
            with(holder) {
                it.product?.text = items.get(position)
                it.itemView.setOnClickListener {
                    Toast.makeText(context, "List ${items.get(position)} was clicked", Toast.LENGTH_SHORT).show()
                    true
                }
            }

        }


    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val product = view.shoppinglist_item
}