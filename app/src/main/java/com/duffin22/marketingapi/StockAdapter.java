package com.duffin22.marketingapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by erikrudie on 8/17/16.
 */



public class StockAdapter extends
        RecyclerView.Adapter<StockAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView VIEW;

        public ViewHolder(View itemView) {
            super(itemView);

//            VIEW = (TextView) itemView.findViewById(R.id.contact_name);
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

        TextView textView = viewHolder.nameTextView;
        textView.setText(model.getName());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mModel.size();
    }
}