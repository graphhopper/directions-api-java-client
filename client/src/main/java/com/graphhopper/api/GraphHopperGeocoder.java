package com.graphhopper.api;

import com.graphhopper.PathWrapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Robin Boldt
 */
public class GraphHopperGeocoder
{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private OkHttpClient downloader;
    private String serviceUrl;
    private String key = "";

    public GraphHopperGeocoder() {
        this("https://graphhopper.com/api/1/geocode");
    }

    public GraphHopperGeocoder(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        downloader = new OkHttpClient.Builder().
                connectTimeout(5, TimeUnit.SECONDS).
                readTimeout(5, TimeUnit.SECONDS).
                build();
    }

    public GHGResponse geocode(String search, String locale) {
        String str = "Creating request failed";
        try {
            String url = serviceUrl
                    + "?";

            if (search != null || !search.isEmpty()) {
                url += "&q=" + WebHelper.encodeURL(search);
            }

            if (locale != null || !locale.isEmpty()) {
                url += "&locale=" + WebHelper.encodeURL(locale);
            }

            if (!key.isEmpty()) {
                url += "&key=" + WebHelper.encodeURL(key);
            }

            Request okRequest = new Request.Builder().url(url).build();
            str = downloader.newCall(okRequest).execute().body().string();

            JSONObject json = new JSONObject(str);
            JSONArray hits = json.getJSONArray("hits");

            for (int index = 0; index < hits.length(); index++) {
                GHGEntry entry = new GHGEntry();
                JSONObject jsonHit = hits.getJSONObject(index);
                JSONObject jsonPoint = jsonHit.getJSONObject("point");

            }
            return new GHGResponse();
        } catch (Exception ex) {
            throw new RuntimeException("Problem while fetching geocoding "+ ", error: " + ex.getMessage() + " response: " + str, ex);
        }
    }

}
