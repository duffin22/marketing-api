package com.duffin22.marketingapi;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddStockFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddStockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddStockFragment extends DialogFragment {
    Context mContext;
    Button mButton;
    EditText mQuantityEdit, mNameEdit;
    public final String TAG = getClass().getCanonicalName();
    String mName;
    int mQuantity;

    private OnFragmentInteractionListener mListener;

    public AddStockFragment() {
        //required empty constructor
    }

    public static AddStockFragment newInstance() {
        AddStockFragment fragment = new AddStockFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_stock, container, false);
        Button mButton = (Button) view.findViewById(R.id.stock_button);
        final EditText mQuantityEdit = (EditText) view.findViewById(R.id.stock_quantity);
        final EditText mNameEdit = (EditText) view.findViewById(R.id.stock_name);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://dev.markitondemand.com/MODApis/Api/v2/Lookup/json?input=";
                mName = mNameEdit.getText().toString();
                mQuantity = Integer.parseInt(mQuantityEdit.getText().toString());
                new OkHTTPTask().execute(url+mName);

            }
        });


        return view;
    }

    private class OkHTTPTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... values) {
            Log.i(TAG, "OkHTTP doInBackground");
            OkHttpClient client = MainActivity.client;
            Request request = new Request.Builder()
                    .url(values[0])
                    .build();
            String s = "";

            try {
                Response response = client.newCall(request).execute();
                s = response.body().string();
                Log.i(TAG, "Response as string: " + s);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return s;
        }

        protected void onPostExecute(String i) {
            Log.i(TAG, "OkHTTP onPostExecute");

            OnFragmentInteractionListener listener = (OnFragmentInteractionListener) mContext;
            listener.onFragmentInteraction(new Stock("NSDF",i,"FTSE",mQuantity));
            dismiss();

        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Stock stock);
    }
}
