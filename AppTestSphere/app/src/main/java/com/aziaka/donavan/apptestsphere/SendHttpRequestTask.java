package com.aziaka.donavan.apptestsphere;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by donavan on 7/16/17.
 */

class SendHttpRequestTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        return getWeatherData(params[0]);
    }

    public String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;
        String test = "http://api.openweathermap.org/data/2.5/weather?q=Strasbourg&mode=json&appid=26dc476bba4387e6503d21181fdb4878";

        System.err.println("Failed to lose hahaha");

        try {
            con = (HttpURLConnection) ( new URL(test)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }
}
