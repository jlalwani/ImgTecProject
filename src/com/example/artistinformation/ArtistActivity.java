package com.example.artistinformation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.artistinformation.asynctasks.DownloadJsonAsyncTask;
import com.example.artistinformation.structures.Utils;

public class ArtistActivity extends Activity {

	private Button retryButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.artist_activity);

		retryButton = (Button) findViewById(R.id.retryButton);

		downloadJson();

		retryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				view.setVisibility(View.GONE);
				downloadJson();
			}
		});

	}

	private void downloadJson() {

		ListView artistDetailsListView = (ListView) findViewById(android.R.id.list);

		ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
		progressBar.setVisibility(View.VISIBLE);

		if (Utils.internetConnectionAvailable(this)) {
			new DownloadJsonAsyncTask(this, artistDetailsListView, retryButton,
					progressBar).execute();

		} else {
			artistDetailsListView.setVisibility(View.GONE);
			retryButton.setVisibility(View.VISIBLE);
			Toast.makeText(this,
					getResources().getString(R.string.internet_error),
					Toast.LENGTH_LONG).show();
			progressBar.setVisibility(View.GONE);
		}
	}

}
