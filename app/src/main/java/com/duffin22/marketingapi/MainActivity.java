package com.duffin22.marketingapi;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
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

public class MainActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    public final String TAG = getClass().getCanonicalName();
    FloatingActionButton mFab;
    RecyclerView recyclerView;
    List<Stock> stockList;

    public static final Uri CONTENT_URI = Uri.parse("content://com.duffin22.marketingapi.MyContentProvider/supersicks");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab = (FloatingActionButton) findViewById(R.id.add_fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Pop up dialog fragment that will take in input name and quantity
            }
        });

        String url = "http://dev.markitondemand.com/MODApis/Api/v2/Lookup/json?input=%22microsoft%22&name=%22microsoft%22";
        String response = "";
//        new OkHTTPTask().execute(url);

        Log.i(TAG,response);

        recyclerView = (RecyclerView) findViewById(R.id.rvStocks_activityMain);
        stockList = new ArrayList<>();  // TODO: actually create this list
        StockAdapter adapter = new StockAdapter(this, stockList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        System.out.println();

        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        addProduct();

    }





    public void addProduct () {
        ContentResolver cr = getContentResolver();
         ContentValues values = new ContentValues();
         values.put(MyDBHandler.COLUMN_EXCHANGE, "NASDAQ");
        values.put(MyDBHandler.COLUMN_NAME, "North Dakota Detroits");
        values.put(MyDBHandler.COLUMN_QUANTITY, 1);
        values.put(MyDBHandler.COLUMN_SYMBOL, "NDD");
         Uri uri = cr.insert(CONTENT_URI,values);
         Log.d(MainActivity.class.getName(),uri.toString());
//             mResultTextView.setText(mInputName.getText().toString()+ " has been added!");
        }

    }

//    private class OkHTTPTask extends AsyncTask<String, Void, String> {
//
//
//        protected String doInBackground(String... values) {
//            Log.i(TAG,"OkHTTP doInBackground");
//            Request request = new Request.Builder()
//                    .url(values[0])
//                    .build();
//            String s = "";
//
//            try {
//                Response response = client.newCall(request).execute();
//                s = response.body().string();
//                Log.i(TAG,"Response as string: "+s);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return s;
//        }
//
//        protected void onPostExecute(String i) {
//            Log.i(TAG,"OkHTTP onPostExecute");
//
//            //TODO: Update adapter
//
//        }
//
//    }

//}
