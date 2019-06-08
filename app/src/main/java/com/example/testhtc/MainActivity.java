package com.example.testhtc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.gson.*;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements DownloadCallback<String> {
    private NetworkFragment mNetworkFragment;
    private boolean mDownloading = false;
    private RecyclerView employeeList;
    private RecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(),
                getResources().getText(R.string.url).toString());
        employeeList = findViewById(R.id.employeesList);
        employeeList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        employeeList.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(employeeList.getContext(),
                mLayoutManager.getOrientation());
        employeeList.addItemDecoration(dividerItemDecoration);
        mAdapter = new RecyclerViewAdapter();
        employeeList.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startDownload();
    }

    private void startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            mDownloading = true;
            mNetworkFragment.startDownload();
        }
    }

    @Override
    public void updateFromDownload(String result) {
        Company company = new Company();
        try {
            JsonParser parser = new JsonParser();
            JsonObject mainObject = parser.parse(result).getAsJsonObject();
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Company.class, new CustomConverter());
            Gson gson = builder.create();
            company = gson.fromJson(mainObject.getAsJsonObject("company"),Company.class);
            Collections.sort(company.getEmployees());
        } catch (Exception e) {
            if (result != null) {
                status = result.length()>100?result.substring(0,100):result;
            } else {
                status = getResources().getString(R.string.noNetworkConnection);
            }
        }
        mAdapter.setData(company,status);
        mAdapter.notifyDataSetChanged();
        finishDownloading();
    }

    @Override
    public void onProgressUpdate(int progress) {
        switch(progress) {
            case DownloadCallback.Progress.ERROR:
                status = getResources().getString(R.string.Error);
                break;
            case DownloadCallback.Progress.CONNECT_SUCCESS:
                status = getResources().getString(R.string.CONNECT_SUCCESS);
                break;
            case DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS:
                status = getResources().getString(R.string.GET_INPUT_STREAM_SUCCESS);
                break;
            case DownloadCallback.Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                status = getResources().getString(R.string.PROCESS_INPUT_STREAM_IN_PROGRESS);
                break;
            case DownloadCallback.Progress.PROCESS_INPUT_STREAM_SUCCESS:
                status = getResources().getString(R.string.PROCESS_INPUT_STREAM_SUCCESS);
                break;
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void finishDownloading() {
        mDownloading = false;
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
    }
}
