package com.duffin22.marketingapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by erikrudie on 8/17/16.
 */

public class StockAdapter extends
        RecyclerView.Adapter<StockAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvStockName, tvStockQuantity;

        public ViewHolder(View itemView) {
            super(itemView);

            tvStockName = (TextView) itemView.findViewById(R.id.textView_stockName_recyclerItem);
            tvStockQuantity = (TextView) itemView.findViewById(R.id.textView_stockQuantity_recyclerItem);
        }
    }

    private List<Stock> mModel;
    private Context mContext;

    public StockAdapter(Context context, List<Stock> model) {
        mModel = model;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public StockAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StockAdapter.ViewHolder viewHolder, int position) {
        Stock model = mModel.get(position);

        TextView tvStockName = viewHolder.tvStockName;
        tvStockName.setText(model.getName());
        TextView tvStockQuantity = viewHolder.tvStockQuantity;
        tvStockQuantity.setText(model.getQuantity());


    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mModel.size();
    }
}