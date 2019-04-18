package com.example.tickerauto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val tickerStringArray : Array<String> = resources.getStringArray(R.array.ticker_list)
    var tickerList : MutableList<String> = ArrayList()

    var page = 1
    var isLoading = false
    val limit = 10

    lateinit var adapter: TickerAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    fun getPage(){
        isLoading = true
        progressBarTicker.visibility = View.VISIBLE
        val start  = (page-1) * limit
        val end = (page) * limit

        for (i in start..end){
            tickerList.add("안녕")
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
        }, 1000)
    }

    class TickerAdapter(val activity : MainActivity) : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TickerViewHolder {
            return TickerViewHolder(LayoutInflater.from(activity).inflate(R.layout.ticker,p0,false))
        }

        override fun getItemCount(): Int {
            return activity.tickerList.size
        }

        override fun onBindViewHolder(p0: TickerViewHolder, p1: Int) {
            p0.tickerNo.text = activity.tickerList[p1]
        }

        class TickerViewHolder(v : View) : RecyclerView.ViewHolder(v){
            val tickerNo = v.findViewById<TextView>(R.id.ticker)
        }
    }
}
