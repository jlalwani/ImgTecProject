package com.example.artistinformation.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artistinformation.R;
import com.example.artistinformation.structures.ArtistBean;
import com.example.artistinformation.structures.ImageLoader;

public class ArtistAdapter extends ArrayAdapter<ArtistBean> {

	private Context context;
	private ArrayList<ArtistBean> ArtistBeans;
	public ImageLoader imageLoader;

	public ArtistAdapter(Context context, int resource,
			ArrayList<ArtistBean> ArtistBeans) {
		super(context, resource, ArtistBeans);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.ArtistBeans = ArtistBeans;
		imageLoader = new ImageLoader(context);
	}

	public int getCount() {
		return ArtistBeans.size();
	}

	public void updateArtistBeans(ArrayList<ArtistBean> ArtistBeans) {
		this.ArtistBeans = ArtistBeans;
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
		viewHolder.artistNameTextView.setText(ArtistBeans.get(position)
				.getName());
		viewHolder.artistDescriptionTextView.setText(ArtistBeans.get(position)
				.getDescription());
		viewHolder.artistGenreTextView.setText("Genre: "+ArtistBeans.get(position)
				.getGenres());

		// DisplayImage function from ImageLoader Class
		imageLoader.DisplayImage(ArtistBeans.get(position).getPicture(),
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
			// Intent localIntent1 = new Intent(context, ReviewActivity.class);
			// localIntent1.putExtra("number", ArtistBeans.get(position)
			// .getNumber());
			// localIntent1.putExtra("title",
			// ArtistBeans.get(position).getName());
			// localIntent1.putExtra("channel", ArtistBeans.get(position)
			// .getChannel());
			// localIntent1.putExtra("releaseDate", ArtistBeans.get(position)
			// .getReleaseDate());
			//
			// context.startActivity(localIntent1);
			// Activity activity = (Activity) context;
			// if (activity != null) {
			// activity.overridePendingTransition(R.anim.slide_in_left,
			// R.anim.slide_out_left);
			// }
		}
	}
}
