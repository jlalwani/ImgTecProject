package com.example.artistinformation.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artistinformation.AlbumActivity;
import com.example.artistinformation.R;
import com.example.artistinformation.structures.ArtistBean;
import com.example.artistinformation.structures.ImageLoader;

public class ArtistAdapter extends ArrayAdapter<ArtistBean> {

	private Context context;
	private ArrayList<ArtistBean> artistBeans;
	public ImageLoader imageLoader;

	public ArtistAdapter(Context context, int resource,
			ArrayList<ArtistBean> artistBeans) {
		super(context, resource, artistBeans);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.artistBeans = artistBeans;
		imageLoader = new ImageLoader(context);
	}

	static class ViewHolder {
		public ImageView artistPictureImageView;
		public TextView artistNameTextView;
		public TextView artistDescriptionTextView;
		public TextView artistGenreTextView;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;

		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.artist_item_view, parent,
					false);

			viewHolder = new ViewHolder();
			viewHolder.artistPictureImageView = (ImageView) convertView
					.findViewById(R.id.artistPictureImageView);
			viewHolder.artistNameTextView = (TextView) convertView
					.findViewById(R.id.artistNameTextView);

			viewHolder.artistDescriptionTextView = (TextView) convertView
					.findViewById(R.id.artistDescriptionTextView);

			viewHolder.artistGenreTextView = (TextView) convertView
					.findViewById(R.id.artistGenreTextView);

			// The tag can be any Object, this just happens to be the ViewHolder
			convertView.setTag(viewHolder);
		} else {
			// Get the ViewHolder back to get fast access to the TextView
			// and the ImageView.
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.artistPictureImageView
				.setScaleType(ImageView.ScaleType.FIT_XY);

		Animation animation = AnimationUtils.loadAnimation(context,
				android.R.anim.fade_in);
		viewHolder.artistPictureImageView.startAnimation(animation);
		viewHolder.artistNameTextView.setText(artistBeans.get(position)
				.getName());
		viewHolder.artistDescriptionTextView.setText(artistBeans.get(position)
				.getDescription());
		viewHolder.artistGenreTextView.setText("Genre: "
				+ artistBeans.get(position).getGenres());

		// DisplayImage function from ImageLoader Class
		imageLoader.DisplayImage(artistBeans.get(position).getPicture(),
				viewHolder.artistPictureImageView);
		convertView.setOnClickListener(new OnItemClickListener(position));

		return convertView;
	}

	private class OnItemClickListener implements OnClickListener {
		private int position;

		OnItemClickListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(context, AlbumActivity.class);
			intent.putExtra("artistId", artistBeans.get(position).getId());
			intent.putExtra("name", artistBeans.get(position).getName());
			intent.putExtra("description", artistBeans.get(position)
					.getDescription());
			intent.putExtra("genres", artistBeans.get(position).getGenres());
			intent.putExtra("picture", artistBeans.get(position).getPicture());

			context.startActivity(intent);
		}
	}
}
