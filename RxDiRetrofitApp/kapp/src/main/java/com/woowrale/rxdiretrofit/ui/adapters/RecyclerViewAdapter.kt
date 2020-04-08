package com.woowrale.rxdiretrofit.ui.adapters

import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.woowrale.rxdiretrofit.R
import com.woowrale.rxdiretrofit.data.model.Market


import java.util.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val marketList: MutableList<Market>

    init {
        marketList = ArrayList<Market>()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val market = marketList[position]
        holder.txtCoin.setText(market.coinName)
        holder.txtMarket.setText(market.market)
        holder.txtPrice.text = "$" + String.format("%.2f", java.lang.Double.parseDouble(market.price))
        if (market.coinName.equals("eth")) {
            holder.cardView.setCardBackgroundColor(Color.GRAY)
        } else {
            holder.cardView.setCardBackgroundColor(Color.GREEN)
        }
    }

    override fun getItemCount(): Int {
        return marketList.size
    }

    fun setData(data: List<Market>) {
        this.marketList.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var txtCoin: TextView
        var txtMarket: TextView
        var txtPrice: TextView
        var cardView: CardView

        init {

            txtCoin = view.findViewById(R.id.txtCoin)
            txtMarket = view.findViewById(R.id.txtMarket)
            txtPrice = view.findViewById(R.id.txtPrice)
            cardView = view.findViewById(R.id.cardView)
        }
    }
}