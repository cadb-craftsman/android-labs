package com.woowrale.rxdiretrofit.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.woowrale.rxdiretrofit.R;
import com.woowrale.rxdiretrofit.data.model.Crypto;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Crypto.Market> marketList;

    public RecyclerViewAdapter() {
        marketList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Crypto.Market market = marketList.get(position);
        holder.txtCoin.setText(market.coinName);
        holder.txtMarket.setText(market.market);
        holder.txtPrice.setText("$" + String.format("%.2f", Double.parseDouble(market.price)));
        //if (market.coinName.equalsIgnoreCase("btc")) {
            holder.cardView.setCardBackgroundColor(Color.GRAY);
        //} else {
        //    holder.cardView.setCardBackgroundColor(Color.GREEN);
        //}
    }

    @Override
    public int getItemCount() {
        return marketList.size();
    }

    public void setData(List<Crypto.Market> data) {
        this.marketList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCoin;
        public TextView txtMarket;
        public TextView txtPrice;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);

            txtCoin = view.findViewById(R.id.txtCoin);
            txtMarket = view.findViewById(R.id.txtMarket);
            txtPrice = view.findViewById(R.id.txtPrice);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
