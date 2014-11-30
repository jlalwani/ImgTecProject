package com.example.artistinformation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.artistinformation.asynctasks.DownloadJsonAsyncTask;
import com.example.artistinformation.structures.Utils;

public class ArtistListActivity extends Activity {

	private Button retryButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.artist_activity);

		retryButton = (Button) findViewById(R.id.retryButton);

		downloadJson();

		retryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				downloadJson();
			}
		});

	}

	private void downloadJson() {

		ListView artistDetailsListView = (ListView) findViewById(android.R.id.list);

		if (Utils.internetConnectionAvailable(this)) {
			new DownloadJsonAsyncTask(this, artistDetailsListView, retryButton).execute();

		} else {
			artistDetailsListView.setVisibility(View.GONE);
			retryButton.setVisibility(View.VISIBLE);
			Toast.makeText(this,
					getResources().getString(R.string.internet_error),
					Toast.LENGTH_LONG).show();
		}
	}

}
