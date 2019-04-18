package com.example.myapplication3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
        var tickers: Array<String> = resources.getStringArray(R.array.ticker)
        var displayList:MutableList<String> = ArrayList()
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val data:Array<String> = resources.getStringArray(R.array.ticker)

            var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
            autocompletetextview.threshold = 0
            autocompletetextview.setAdapter(adapter)
            autolistview.adapter = adapter
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if(searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        displayList.addAll(tickers)


                    }else{

                    }
                    return true
                }
            })

        }
        return super.onCreateOptionsMenu(menu)
    }
}
