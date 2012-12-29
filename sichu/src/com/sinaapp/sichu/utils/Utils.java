package com.sinaapp.sichu.utils;

import java.io.File;
import java.util.Date;
import java.util.TimeZone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.sinaapp.sichu.R;

public class Utils {

	// private static String TAG = Utils.class.getSimpleName();
	private static ImageLoader img_loader;
	private static DisplayImageOptions cloud_options;

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		// if no network is available networkInfo will be null
		// otherwise check if we are connected
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}
	
	public static long getUTCTimeStamp() {
		TimeZone tz = TimeZone.getDefault();
		Date now = new Date();
		int offset = tz.getOffset(now.getTime());
		return (now.getTime() - offset) / 1000;
	}
	
	public static Date getDateFromUTCTimeStamp(long timestamp) {
		TimeZone tz = TimeZone.getDefault();
		int offset = tz.getOffset(timestamp * 1000);			
		long lcl_timestamp = timestamp * 1000 + offset; 		
		return new Date(lcl_timestamp);
	}
	
	public static DisplayImageOptions getCloudOptions() {
		if (cloud_options == null) {
			cloud_options = new DisplayImageOptions.Builder()
					.showStubImage(R.drawable.ic_book)
					.showImageForEmptyUri(R.drawable.ic_book)
					.resetViewBeforeLoading().cacheInMemory().cacheOnDisc()
					.delayBeforeLoading(100).build();
		}
		return cloud_options;
	}	
	
	public static ImageLoader getImageLoader(Context context) {
		if (img_loader == null) {
			img_loader = ImageLoader.getInstance();
			File cacheDir = StorageUtils.getOwnCacheDirectory(context,
					"sichu/cache");
			DisplayImageOptions options = new DisplayImageOptions.Builder()
					.showStubImage(R.drawable.ic_book)
					.showImageForEmptyUri(R.drawable.ic_book)
					.resetViewBeforeLoading().cacheInMemory().build();
			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
					context).memoryCacheExtraOptions(80, 80)
					.discCache(new UnlimitedDiscCache(cacheDir))
					.defaultDisplayImageOptions(options).build();
			img_loader.init(config);
		}
		return img_loader;
	}	
}