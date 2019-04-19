package com.example.tickerauto


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tickerStringArray : Array<String>
    var displayList : MutableList<String> = ArrayList()
    var filteredList : MutableList<String> = ArrayList()
    var page = 1
    var isLoading = false
    val limit = 10

    lateinit var adapter: TickerAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tickerStringArray =  resources.getStringArray(R.array.ticker_list)

        filteredList.addAll(tickerStringArray)
        layoutManager = LinearLayoutManager(this)
        recyclerViewTicker.layoutManager = layoutManager
        getPage()

        recyclerViewTicker.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //if (dy > 0 ) {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount

                    if(!isLoading){
                        if((visibleItemCount + pastVisibleItem) >= total){
                            page++
                            getPage()
                        }
                    }
                //}
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        val searchItem = menu.findItem(R.id.ticker_search)
        if(searchItem != null){
            val searchView = searchItem.actionView as SearchView
            val editText = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
            editText.hint = "Search here..."
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {

                    if(p0!!.isNotEmpty()){
                        displayList.clear()
                        filteredList.clear()
                        val search = p0.toLowerCase()
                        tickerStringArray.forEach {
                            if(it.toLowerCase().contains(search)){
                                filteredList.add(it)
                            }
                        }
                        page = 1
                        getPage()
                    }else{
                        displayList.clear()
                        filteredList.clear()
                        filteredList.addAll(tickerStringArray)
                        page = 1
                        getPage()
                    }
                    return true
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    fun getPage(){
        isLoading = true
        progressBarTicker.visibility = View.VISIBLE
        val start  = (page-1) * limit
        val end = (page) * limit

        for (i in start..end){
            if(i >= filteredList.size){
                break
            }
            displayList.add(filteredList[i])
        }
        Handler().postDelayed({
            if(::adapter.isInitialized){
                adapter.notifyDataSetChanged()
            }else{
                adapter = TickerAdapter(this)
                recyclerViewTicker.adapter = adapter
            }
            isLoading = false
            progressBarTicker.visibility = View.GONE
        }, 500)
    }

    class TickerAdapter(val activity : MainActivity) : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TickerViewHolder {
            return TickerViewHolder(LayoutInflater.from(activity).inflate(R.layout.ticker,p0,false))
        }

        override fun getItemCount(): Int {
            return activity.displayList.size
        }

        override fun onBindViewHolder(p0: TickerViewHolder, p1: Int) {
            p0.tickerNo.text = activity.displayList[p1]
        }

        class TickerViewHolder(v : View) : RecyclerView.ViewHolder(v){
            val tickerNo = v.findViewById<TextView>(R.id.ticker)
        }
    }
}
