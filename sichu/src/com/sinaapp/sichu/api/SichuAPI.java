package com.sinaapp.sichu.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.sinaapp.sichu.R;
import com.sinaapp.sichu.net.ApiBase;
import com.sinaapp.sichu.net.ApiRequest;
import com.sinaapp.sichu.net.ApiResponse;
import com.sinaapp.sichu.net.HttpEntityWithProgress.ProgressListener;
import com.sinaapp.sichu.utils.Preferences;
import com.sinaapp.sichu.utils.Utils;

public class SichuAPI extends ApiBase implements ISichuAPI {

	private Context context;
	private static ISichuAPI INSTANCE;

	public SichuAPI(Context context) {
		super(context);
		this.context = context;
	}

	public static ISichuAPI getInstance(Context context) {
		if (INSTANCE == null) {
			INSTANCE = new SichuAPI(context);
		}
		return INSTANCE;
	}

	@Override
	public ApiResponse execute(ApiRequest request, ProgressListener listener)
			throws ClientProtocolException, IOException {
		if (!Utils.isNetworkAvailable(context)) {
			throw new IOException("Network not available!");
		}
		
		if (!request.getPath().equals("/v1/account/login/")) {
			request.addHeader("AUTHORIZATION",
					"Bearer " + Preferences.getToken(context));
		}
		
		ApiResponse response = super.execute(request, listener);		
		if (response.getStatusCode() == 401) {
			Preferences.expireToken(context);
		}
		
		return response;
	}

	@Override
	public JSONObject account_login(String username, String password,
			ProgressListener progressListener) throws ClientProtocolException,
			IOException, JSONException {
		ApiRequest request = new ApiRequest(ApiRequest.POST,
				"/v1/account/login/");
		request.addParameter("username", username);
		request.addParameter("password", password);
		request.addParameter("apikey", context.getString(R.string.apikey));

		ApiResponse response = execute(request, progressListener);

		return new JSONObject(response.getContentAsString());
	}

	@Override
	public JSONObject bookown(String next, ProgressListener progressListener)
			throws ClientProtocolException, IOException, JSONException {
		ApiRequest request = new ApiRequest(ApiRequest.GET,
				next == null ? "/v1/bookown/" : next);

		ApiResponse response = execute(request, progressListener);

		return new JSONObject(response.getContentAsString());
	}

}