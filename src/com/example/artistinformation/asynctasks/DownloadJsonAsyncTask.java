package com.example.artistinformation.asynctasks;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.artistinformation.R;
import com.example.artistinformation.adapters.ArtistAdapter;
import com.example.artistinformation.structures.ArtistBean;
import com.example.artistinformation.structures.JSONParser;
import com.example.artistinformation.structures.Utils;

public class DownloadJsonAsyncTask extends AsyncTask<Void, String, JSONObject> {

	private Context context;
	private ListView artistDetailsListView;
	private Button retryButton;

	public DownloadJsonAsyncTask(Context context,
			ListView artistDetailsListView, Button retryButton) {
		this.context = context;
		this.artistDetailsListView = artistDetailsListView;
		this.retryButton = retryButton;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected JSONObject doInBackground(Void... args) {
		JSONParser jParser = new JSONParser();
		// Getting JSON from URL
		JSONObject jsonObject = jParser.getJSONFromUrl(Utils.jsonURL);
		return jsonObject;
	}

	@Override
	protected void onPostExecute(JSONObject jsonObject) {

		if (jsonObject != null) {
			ArrayList<ArtistBean> artistBeans = Utils
					.returnArtistBeans(jsonObject);
			ArtistAdapter artistAdapter = new ArtistAdapter(context,
					R.layout.artist_item_view, artistBeans);
			artistDetailsListView.setAdapter(artistAdapter);
			artistDetailsListView.setVisibility(View.VISIBLE);
			retryButton.setVisibility(View.GONE);
		} else {
			artistDetailsListView.setVisibility(View.GONE);
			retryButton.setVisibility(View.VISIBLE);
			Toast.makeText(context,
					context.getResources().getString(R.string.json_error),
					Toast.LENGTH_LONG).show();
		}

	}
}