package com.example.artistinformation;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.artistinformation.structures.AlbumBean;
import com.example.artistinformation.structures.FileCache;
import com.example.artistinformation.structures.ImageLoader;
import com.example.artistinformation.structures.Utils;

public class AlbumActivity extends Activity {

	private ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.album_activity);

		imageLoader = new ImageLoader(this);

		String artistId = getIntent().getExtras().getString("artistId");
		String name = getIntent().getExtras().getString("name");
		String description = getIntent().getExtras().getString("description");
		String genres = getIntent().getExtras().getString("genres");
		String picture = getIntent().getExtras().getString("picture");

		ImageView artistImageView = (ImageView) findViewById(R.id.artistImageView);

		FileCache fileCache = new FileCache(this);
		File f = fileCache.getFile(picture);

		Bitmap bitmap = Utils.decodeFile(f);

		if (bitmap != null) {
			artistImageView.setImageBitmap(bitmap);
		}

		TextView artistNameTextView = (TextView) findViewById(R.id.artistNameTextView);

		TextView artistDescriptionTextView = (TextView) findViewById(R.id.artistDescriptionTextView);

		TextView artistGenreTextView = (TextView) findViewById(R.id.artistGenreTextView);

		artistNameTextView.setText("Artist Name: " + name);
		artistDescriptionTextView.setText(description);
		artistGenreTextView.setText("Genres: " + genres);

		ArrayList<AlbumBean> albumBeans = Utils.returnAlbumBeans(artistId);
		LinearLayout albumLinearLayout = (LinearLayout) findViewById(R.id.albumLinearLayout);
		for (int i = 0; i < albumBeans.size(); i++) {
			LinearLayout linearLayout = new LinearLayout(this);
			linearLayout.setOrientation(LinearLayout.HORIZONTAL);

			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

			linearLayout.setLayoutParams(layoutParams);

			ImageView imageView = new ImageView(this);

			layoutParams = new LayoutParams(100, 100);

			imageLoader.DisplayImage(albumBeans.get(i).getPicture(), imageView);

			layoutParams.setMargins(0, 10, 0, 0);

			linearLayout.addView(imageView);

			imageView.setLayoutParams(layoutParams);

			TextView textView = new TextView(this);

			layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);

			textView.setText(albumBeans.get(i).getTitle());

			layoutParams.setMargins(20, 30, 0, 0);

			textView.setLayoutParams(layoutParams);

			linearLayout.addView(textView);

			albumLinearLayout.addView(linearLayout);
		}

	}

}
