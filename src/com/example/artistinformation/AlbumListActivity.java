package com.example.artistinformation;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.artistinformation.adapters.AlbumAdapter;
import com.example.artistinformation.asynctasks.DownloadJsonAsyncTask;
import com.example.artistinformation.structures.AlbumBean;
import com.example.artistinformation.structures.Utils;

public class AlbumListActivity extends Activity {

	private String artistId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.album_activity);

		artistId = getIntent().getExtras().getString("artistId");

		ListView albumDetailsListView = (ListView) findViewById(android.R.id.list);
		ArrayList<AlbumBean> albumBeans = Utils.returnAlbumBeans(artistId);
		AlbumAdapter albumAdapter = new AlbumAdapter(this,
				R.layout.album_item_view, albumBeans);
		albumDetailsListView.setAdapter(albumAdapter);

	}

}
