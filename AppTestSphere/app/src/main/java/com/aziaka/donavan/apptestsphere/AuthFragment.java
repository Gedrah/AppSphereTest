package com.aziaka.donavan.apptestsphere;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AuthFragment extends Fragment {

    private String userName = "";
    private String userCity = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_auth, container, false);
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
                    System.out.println("Name of the city : " + userCity + "\nName of the user : " + userName);
                    if (!userName.isEmpty() && !userCity.isEmpty())
                    {
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.detail_fragment, new DetailFragment(), "DetailFragmentTag");
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
}