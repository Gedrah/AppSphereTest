package com.aziaka.donavan.apptestsphere;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class AuthFragment extends Fragment {

    private String userName = "";
    private String userCity = "";
    private String weatherApiData;
    private int temperature;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            final View view = inflater.inflate(R.layout.fragment_auth, container, false);
            final Button button = (Button) view.findViewById(R.id.validButton);
            final EditText name = (EditText) view.findViewById(R.id.username);
            final EditText city = (EditText) view.findViewById(R.id.usercity);

        button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    userCity = city.getText().toString();
                    userName = name.getText().toString();
                    //System.out.println("Name of the city : " + userCity + "\nName of the user : " + userName);
                    if (!userName.isEmpty() && !userCity.isEmpty())
                    {
                        //getTemp();
                        Bundle i = new Bundle();
                        i.putString("userName", userName);
                        i.putString("userCity", userCity);
                        i.putInt("temp", temperature);

                        DetailFragment detail = new DetailFragment();
                        detail.setArguments(i);

                        button.setVisibility(View.INVISIBLE);
                        name.setVisibility(View.INVISIBLE);
                        city.setVisibility(View.INVISIBLE);
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.detail_fragment, detail, "DetailFragmentTag");
                        ft.commit();
                        ft.addToBackStack(null);
                    }
                    else
                    {
                        // put error banner
                    }
                }
            });
            return view;
        }


        boolean callWeatherApi(String city) {

            String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=Strasbourg";
            String API_KEY = "&mode=json&appid=26dc476bba4387e6503d21181fdb4878";
            String test = "http://api.openweathermap.org/data/2.5/weather?q=Strasbourg&mode=json&appid=26dc476bba4387e6503d21181fdb4878";
            InputStream is = null;
            HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(test);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();

                    // Let's read the response
                    StringBuffer buffer = new StringBuffer();
                    is = urlConnection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    while ( (line = br.readLine()) != null )
                        buffer.append(line + "\n");

                    is.close();
                    urlConnection.disconnect();
                    weatherApiData = buffer.toString();
                    return true;
                }
                catch(Exception t) {
                    t.printStackTrace();
                    System.err.println(t.getMessage());
                }
                finally {
                    try { is.close(); } catch(Throwable t) {}
                    try { urlConnection.disconnect(); } catch(Throwable t) {}
                }
                return false;
        }

        void getTemp() {
            try {
                JSONObject jObj = new JSONObject(weatherApiData);
                JSONObject mainObj = getObject("main", jObj);
                temperature = getInt("temp", mainObj);
            } catch (JSONException e) {
                System.err.println("Failed to parse args.");
            }
        }

    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return (int) jObj.getDouble(tagName);
    }
}
