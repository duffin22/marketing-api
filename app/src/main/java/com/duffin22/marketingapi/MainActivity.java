package com.duffin22.marketingapi;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddStockFragment.OnFragmentInteractionListener {
    public static OkHttpClient client = new OkHttpClient();
    public final String TAG = getClass().getCanonicalName();
    FloatingActionButton mFab;
    RecyclerView recyclerView;
    List<Stock> stockList;
    StockAdapter adapter;

    public static final Uri CONTENT_URI = Uri.parse("content://com.duffin22.marketingapi.MyContentProvider/supersicks");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab = (FloatingActionButton) findViewById(R.id.add_fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fraggyMan = getSupportFragmentManager();
                AddStockFragment fraggy = AddStockFragment.newInstance();
                fraggy.show(fraggyMan, TAG);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rvStocks_activityMain);
        stockList = new ArrayList<>();  // TODO: actually create this list
        adapter = new StockAdapter(this, stockList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        System.out.println();

    }


    public void addProduct() {
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(MyDBHandler.COLUMN_EXCHANGE, "NASDAQ");
        values.put(MyDBHandler.COLUMN_NAME, "North Dakota Detroits");
        values.put(MyDBHandler.COLUMN_QUANTITY, 1);
        values.put(MyDBHandler.COLUMN_SYMBOL, "NDD");
        Uri uri = cr.insert(CONTENT_URI, values);
        Log.d(MainActivity.class.getName(), uri.toString());
//             mResultTextView.setText(mInputName.getText().toString()+ " has been added!");
    }


    @Override
    public void onFragmentInteraction(Stock stock) {
        stockList.add(stock);
        adapter.notifyDataSetChanged();
    }
}

