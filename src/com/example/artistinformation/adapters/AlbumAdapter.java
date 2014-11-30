package com.example.artistinformation.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artistinformation.R;
import com.example.artistinformation.structures.AlbumBean;
import com.example.artistinformation.structures.ImageLoader;

public class AlbumAdapter extends ArrayAdapter<AlbumBean> {

	private Context context;
	private ArrayList<AlbumBean> albumBeans;
	public ImageLoader imageLoader;

	public AlbumAdapter(Context context, int resource,
			ArrayList<AlbumBean> albumBeans) {
		super(context, resource, albumBeans);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.albumBeans = albumBeans;
		imageLoader = new ImageLoader(context);
	}

	static class ViewHolder {
		public ImageView albumPictureImageView;
		public TextView albumNameTextView;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;

		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.album_item_view, parent,
					false);

			viewHolder = new ViewHolder();
			viewHolder.albumPictureImageView = (ImageView) convertView
					.findViewById(R.id.albumPictureImageView);
			viewHolder.albumNameTextView = (TextView) convertView
					.findViewById(R.id.albumNameTextView);

			// The tag can be any Object, this just happens to be the ViewHolder
			convertView.setTag(viewHolder);
		} else {
			// Get the ViewHolder back to get fast access to the TextView
			// and the ImageView.
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.albumPictureImageView
				.setScaleType(ImageView.ScaleType.FIT_XY);

		Animation animation = AnimationUtils.loadAnimation(context,
				android.R.anim.fade_in);
		viewHolder.albumPictureImageView.startAnimation(animation);
		viewHolder.albumNameTextView.setText(albumBeans.get(position)
				.getTitle());

		// DisplayImage function from ImageLoader Class
		imageLoader.DisplayImage(albumBeans.get(position).getPicture(),
				viewHolder.albumPictureImageView);

		return convertView;
	}

}
