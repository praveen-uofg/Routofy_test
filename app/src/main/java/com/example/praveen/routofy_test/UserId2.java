package com.example.praveen.routofy_test;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by praveen on 5/31/2015.
 */
public class UserId2 extends ListFragment {
    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://jsonplaceholder.typicode.com/posts";

    // JSON Node names
    private static final String TAG_USERID = "userId";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_BODY = "body";
    ArrayList<HashMap<String, String>> sampleList;
    ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_tab, container, false);

        sampleList = new ArrayList<HashMap<String, String>>();
        actionBar = getActivity().getActionBar();
        actionBar.getTabAt(1).setText("userId:2");

        new GetContacts().execute();

        return rootview;
    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage(getActivity().getResources().getString(R.string.progress_string));
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONArray j = new JSONArray(jsonStr);
                    // Getting JSON Array node

                    // looping through All Contacts
                    for (int i = 0; i < j.length(); i++) {
                        JSONObject c = j.getJSONObject(i);
                        String userId = c.getString(TAG_USERID);
                        if(userId == "2") {
                            String uid = c.getString(TAG_ID);
                            String title = c.getString(TAG_TITLE);
                            String body = c.getString(TAG_BODY);


                            // tmp hashmap for single contact
                            HashMap<String, String> sample = new HashMap<String, String>();

                            // adding each child node to HashMap key => value
                            sample.put(TAG_ID, uid);
                            sample.put(TAG_TITLE, title);
                            sample.put(TAG_BODY, body);

                            // adding contact to contact list
                            sampleList.add(sample);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), sampleList,
                    R.layout.list, new String[] { TAG_ID, TAG_TITLE,
                    TAG_BODY}, new int[] { R.id.uid,
                    R.id.title, R.id.body });

            setListAdapter(adapter);

        }

    }
}
