package com.les4elefantastiq.les4elefantcowork.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;
import com.les4elefantastiq.les4elefantcowork.models.Coworker;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoworkersActivity extends BaseActivity {

    // -------------- Objects, Variables -------------- //

    private CoworkersAsyncTask mCoworkersAsyncTask;


    // -------------------- Views --------------------- //

    private ListView mListView;


    // ------------------ LifeCycle ------------------- //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coworkers_activity);

        mListView = (ListView) findViewById(R.id.listview);

        mCoworkersAsyncTask = new CoworkersAsyncTask();
        mCoworkersAsyncTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCoworkersAsyncTask != null)
            mCoworkersAsyncTask.cancel(false);
    }


    // ------------------ Listeners ------------------- //

    // ------------------- Methods -------------------- //

    // ------------------ AsyncTasks ------------------ //

    private class CoworkersAsyncTask extends AsyncTask<Void, Void, List<Coworker>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(CoworkersActivity.this, null, "Please wait ...", true, false);
        }

        @Override
        protected List<Coworker> doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(List<Coworker> coworkers) {
            super.onPostExecute(coworkers);

            progressDialog.dismiss();

            if (coworkers != null)
                mListView.setAdapter(new Adapter(coworkers));
            else
                Toast.makeText(CoworkersActivity.this, R.string.Whoops_an_error_has_occured__Check_your_internet_connection, Toast.LENGTH_LONG).show();
        }

    }


    // ----------------- GUI Adapter ------------------ //

    private class Adapter extends BaseAdapter {

        private List<Coworker> coworkers;

        private class ObjectsHolder {
            ImageView imageView;
            TextView textView_Name;
            TextView textView_Description;
        }

        public Adapter(List<Coworker> coworkers) {
            this.coworkers = coworkers;
        }

        @Override
        public int getCount() {
            return coworkers.size();
        }

        @Override
        public Coworker getItem(int position) {
            return coworkers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Coworker coworker = getItem(position);
            ObjectsHolder objectsHolder;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.coworkers_activity_item, parent, false);
                objectsHolder = new ObjectsHolder();
                convertView.setTag(objectsHolder);

                objectsHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
                objectsHolder.textView_Name = (TextView) convertView.findViewById(R.id.textview_name);
                objectsHolder.textView_Description = (TextView) convertView.findViewById(R.id.textview_description);
            } else
                objectsHolder = (ObjectsHolder) convertView.getTag();


                Picasso.with(getBaseContext())
                        .load(coworker.pictureUrl)
                        .into(objectsHolder.imageView);

            objectsHolder.textView_Name.setText(coworker.firstName + " " + coworker.lastName);

            return convertView;
        }

    }


    // --------------------- Menu --------------------- //

}