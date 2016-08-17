package com.duffin22.marketingapi;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    public final String TAG = getClass().getCanonicalName();
    FloatingActionButton mFab;

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
        new OkHTTPTask().execute(url);

        Log.i(TAG,response);

    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    private class OkHTTPTask extends AsyncTask<String, Void, String> {


        protected String doInBackground(String... values) {
            Log.i(TAG,"OkHTTP doInBackground");
            Request request = new Request.Builder()
                    .url(values[0])
                    .build();
            String s = "";

            try {
                Response response = client.newCall(request).execute();
                s = response.body().string();
                Log.i(TAG,"Response as string: "+s);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return s;
        }

        protected void onPostExecute(String i) {
            Log.i(TAG,"OkHTTP onPostExecute");


        }

    }

}
