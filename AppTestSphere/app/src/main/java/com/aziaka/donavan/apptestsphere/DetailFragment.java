package com.aziaka.donavan.apptestsphere;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    private int valueCelsius;
    private int valueFahr;
    private int valueKelvin;
    private TextView nameView;
    private TextView cityView;
    private TextView messView;
    private TextView tempView;
    private TabLayout tabs;


    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setDatas(int currentTemperature)
    {
        valueKelvin = currentTemperature;
        valueCelsius = valueKelvin - (int)273.15;
        valueFahr = (int)(valueKelvin * 1.8) - (int)459.67;
        tempView.setText(valueCelsius + "°C");
        if (valueCelsius > 20) {
            messView.setHint(getString(R.string.hot));
        }
        else if (valueCelsius < 10) {
            messView.setHint(getString(R.string.cold));
        }
        else {
            messView.setHint(getString(R.string.fine));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        nameView = (TextView) view.findViewById(R.id.name_info);
        cityView = (TextView) view.findViewById(R.id.city_infos);
        tempView = (TextView) view.findViewById(R.id.nbr_data);
        messView = (TextView) view.findViewById(R.id.weather_info);
        tabs = (TabLayout) view.findViewById(R.id.tabs);

        String city = cityView.getHint().toString() + " " + getArguments().getString("userCity");
        String name = nameView.getHint().toString() + " " +getArguments().getString("userName");

        valueKelvin = 0;
        valueCelsius = 0;
        valueFahr = 0;

        nameView.setText(name);
        cityView.setText(city);
        tempView.setText(valueCelsius + "°C");


        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0)
                    tempView.setText(valueCelsius + "°C");
                else if (tab.getPosition() == 1)
                    tempView.setText(valueFahr + "°F");
                else
                    tempView.setText(valueKelvin + "°K");
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        return view;
    }

}
