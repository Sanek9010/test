package com.example.testhtc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.*;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements DownloadCallback<String> {
    private NetworkFragment mNetworkFragment;
    private boolean mDownloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(),
                getResources().getText(R.string.url).toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        startDownload();
    }

    private void startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            mNetworkFragment.startDownload();
            mDownloading = true;
        }
    }

    @Override
    public void updateFromDownload(String result) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject mainObject = parser.parse(result).getAsJsonObject();
            Gson g = new Gson();
            Company c = g.fromJson(mainObject.getAsJsonObject("company"),Company.class);
            Collections.sort(c.getEmployees());
            StringBuilder competences = new StringBuilder();
            for (String competence : c.getCompetences()) {
                competences.append(competence);
                competences.append(", ");
            }

            TextView name =  findViewById(R.id.companyName);
            TextView age =  findViewById(R.id.companyAge);
            TextView competencesView =  findViewById(R.id.competencesList);
            ListView employeeList =  findViewById(R.id.employeesList);

            name.setText(c.getName());
            age.setText(String.format("%d",c.getAge()));
            competencesView.setText(competences.substring(0,competences.length()-2));
            EmployeeAdapter employeeAdapter = new EmployeeAdapter(this,c.getEmployees());
            employeeList.setAdapter(employeeAdapter);
        } catch (Exception e) {
            TextView resultMessage = findViewById(R.id.textView);
            if (result != null) {
                resultMessage.setText(result);
            } else {
                resultMessage.setText(R.string.noNetworkConnection);
            }
        }
    }

    @Override
    public void onProgressUpdate(int progress) {
        TextView resultMessage = findViewById(R.id.textView);
        switch(progress) {
            case DownloadCallback.Progress.ERROR:
                resultMessage.setText(R.string.Error);
                break;
            case DownloadCallback.Progress.CONNECT_SUCCESS:
                resultMessage.setText(R.string.CONNECT_SUCCESS);
                break;
            case DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS:
                resultMessage.setText(R.string.GET_INPUT_STREAM_SUCCESS);
                break;
            case DownloadCallback.Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                resultMessage.setText(R.string.PROCESS_INPUT_STREAM_IN_PROGRESS);
                break;
            case DownloadCallback.Progress.PROCESS_INPUT_STREAM_SUCCESS:
                resultMessage.setText(R.string.PROCESS_INPUT_STREAM_SUCCESS);
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
