package com.example.artistinformation.structures;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

	public static String jsonURL = "http://i.img.co/data/data.json";
	public static String TAG_ARTISTS = "artists";
	public static String TAG_ID = "id";
	public static String TAG_NAME = "name";
	public static String TAG_DESCRIPTION = "description";
	public static String TAG_GENRES = "genres";
	public static String TAG_PICTURE = "picture";

	public static String TAG_ALBUMS = "albums";
	public static String TAG_ARTIST_ID = "artistId";
	public static String TAG_TITLE = "title";
	public static String TAG_TYPE = "type";
	public static JSONObject jsonObject;

	public static boolean internetConnectionAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}

	public static void CopyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024;
		try {

			byte[] bytes = new byte[buffer_size];
			for (;;) {
				// Read byte from input stream

				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;

				// Write byte from output stream
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {
		}
	}

	public static ArrayList<ArtistBean> returnArtistBeans() {
		ArrayList<ArtistBean> artistBeans = new ArrayList<ArtistBean>();
		try {
			JSONArray artistsJsonArray = jsonObject.getJSONArray(TAG_ARTISTS);
			for (int i = 0; i < artistsJsonArray.length(); i++) {
				JSONObject artistJsonObject = artistsJsonArray.getJSONObject(i);
				ArtistBean artistBean = new ArtistBean();
				artistBean.setId(artistJsonObject.getString(TAG_ID));
				artistBean.setName(artistJsonObject.getString(TAG_NAME));
				artistBean.setDescription(artistJsonObject
						.getString(TAG_DESCRIPTION));
				artistBean.setGenres(artistJsonObject.getString(TAG_GENRES));
				artistBean.setPicture(artistJsonObject.getString(TAG_PICTURE));
				artistBeans.add(artistBean);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return artistBeans;
	}

	public static void saveJSONObject(JSONObject resultJsonObject) {
		jsonObject = resultJsonObject;
	}

	public static ArrayList<AlbumBean> returnAlbumBeans(String artistId) {
		ArrayList<AlbumBean> albumBeans = new ArrayList<AlbumBean>();
		try {
			JSONArray artistsJsonArray = jsonObject.getJSONArray(TAG_ALBUMS);
			for (int i = 0; i < artistsJsonArray.length(); i++) {
				JSONObject artistJsonObject = artistsJsonArray.getJSONObject(i);
				if (artistJsonObject.getString(TAG_ARTIST_ID).equals(artistId)) {
					AlbumBean albumBean = new AlbumBean();
					albumBean.setId(artistJsonObject.getString(TAG_ID));
					albumBean.setArtistId(artistJsonObject
							.getString(TAG_ARTIST_ID));
					albumBean.setTitle(artistJsonObject.getString(TAG_TITLE));
					albumBean.setType(artistJsonObject.getString(TAG_TYPE));
					albumBean.setPicture(artistJsonObject
							.getString(TAG_PICTURE));
					albumBeans.add(albumBean);
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albumBeans;
	}
}