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
    var tickers : MutableList<String> = ArrayList()
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
        //loadData()
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
                        filteredList.addAll(tickers)
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

    fun loadData(){
        tickers.add("Agilent Technologies")
        tickers.add("Alcoa Corp")
        tickers.add("Perth Mint Physical Gold ETF")
        tickers.add("Altaba Inc")
        tickers.add("Aac Holdings Inc")
        tickers.add("Advisorshares Dorsey Wright ADR")
        tickers.add("American Airlines Gp")
        tickers.add("Altisource Asset")
        tickers.add("Atlantic Amer Cp")
        tickers.add("Aaron\'s Inc")
        tickers.add("Applied Optoelect")
        tickers.add("Aaon Inc")
        tickers.add("Advance Auto Parts Inc")
        tickers.add("Apple Inc")
        tickers.add("American Assets Trust")
        tickers.add("Almaden Minerals")
        tickers.add("Atlas Air Ww")
        tickers.add("All Country Asia Ex Japan Ishares MSCI ETF")
        tickers.add("Axon Inc")
        tickers.add("Alliancebernstein Holding LP")
        tickers.add("Abb Ltd")
        tickers.add("Abbvie Inc")
        tickers.add("Amerisourcebergen Corp")
        tickers.add("Ameris Bancorp")
        tickers.add("Alcentra Capital Com")
        tickers.add("Abeona Therapeutics")
        tickers.add("Ambev S.A.")
        tickers.add("Asbury Automotive Group Inc")
        tickers.add("Ability Inc")
        tickers.add("Arca Biopharma Inc")
        tickers.add("ABM Industries Incorporated")
        tickers.add("Abiomed Inc")
        tickers.add("Arbor Realty Trust")
        tickers.add("Arbor Realty Trust Inc")
        tickers.add("Arbor Realty Trust Inc")
        tickers.add("Arbor Realty Trust")
        tickers.add("Abbott Laboratories")
        tickers.add("Allegiance Banc CS")
        tickers.add("Arbutus Biopharma Cp")
        tickers.add("Associated Capital Group Inc")
        tickers.add("Arcosa Inc")
        tickers.add("Acadia Pharmaceutica")
        tickers.add("Aurora Cannabis Inc")
        tickers.add("Atlantic Capital")
        tickers.add("American Campus Communities Inc")
        tickers.add("Acco Brands Corp")
        tickers.add("Acer Therapeutics Inc")
        tickers.add("Alps Clean Energy ETF")
        tickers.add("Arch Capital Grp Ltd")
        tickers.add("Arch Capital Group Ltd ADR")
        tickers.add("Arch Capital Group Ltd")
        tickers.add("Aluminum Corp of China Ltd")
        tickers.add("Acadia Healthcr CO")
        tickers.add("Achillion Pharmaceut")
        tickers.add("Achieve Life Sciences Inc")
        tickers.add("Acacia Communica")
        tickers.add("ACWI IMI MSCI ETF SPDR")
        tickers.add("AC Immune S.A.")
        tickers.add("Aci Worldwide Inc")
        tickers.add("Axcelis Tech Inc")
        tickers.add("Aecom Technology Corp")
        tickers.add("Acm Research Inc")
        tickers.add("Accenture Plc")
        tickers.add("Acnb Corp")
        tickers.add("Acorda Therapeutics")
        tickers.add("Avenue Income Credit Strategies")
        tickers.add("Ares Commercial Real Estate Cor")
        tickers.add("Aclaris Therapts")
        tickers.add("Acelrx Pharmaceutica")
        tickers.add("Xtrackers MSCI ACWI Ex USA ESG Leaders Equity Et")
        tickers.add("American Customer Satisfaction Core Alpha ETF")
        tickers.add("Acasti Pharma")
        tickers.add("Advisorshares Vice ETF")
        tickers.add("Acacia Res-Acacia")
        tickers.add("Acme United Corp")
        tickers.add("Allianzgi Diversified Income &amp;")
        tickers.add("Global Multifactor Ishares Edge MSCI ETF")
        tickers.add("ACWI Ishares MSCI ETF")
        tickers.add("Global Min Vol Ishares Edge MSCI ETF")
        tickers.add("ACWI Ex US Ishares MSCI ETF")
        tickers.add("Adaptimmune Ther Ads")
        tickers.add("Adobe Systems Inc")
        tickers.add("Agree Realty Corp")
        tickers.add("Advanced Emissions Solutions Inc")
        tickers.add("Analog Devices")
        tickers.add("Adial Pharmaceuticals Inc")
        tickers.add("Archer Daniels Midland Company")
        tickers.add("Adma Biologics")
        tickers.add("Adamis Pharmaceuticl")
        tickers.add("Adamas Pharma")
        tickers.add("Adient Plc")
        tickers.add("Adomani Inc")
        tickers.add("Automatic Data Procs")
        tickers.add("Bldrs Developed Markets 100 ADR Index Fund Inves")
        tickers.add("Bldrs Emerging Markets 50 ADR Index Fund Invesco")
        tickers.add("Aduro Biotech")
        tickers.add("Bldrs Europe Select ADR Index Fund Invesco")
        tickers.add("Alliance Data Systems Corp")
        tickers.add("Autodesk Inc")
        tickers.add("Advanced Disposal Services Inc")
        filteredList.addAll(tickers)
    }
}
