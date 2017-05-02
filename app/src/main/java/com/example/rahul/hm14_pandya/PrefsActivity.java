package com.example.rahul.hm14_pandya;

// Rahul Pandya
//L20355202

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import java.util.List;

public class PrefsActivity extends PreferenceActivity {


    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
            return true;
        else if (PrefsFragmentSettings.class.getName().equals(fragmentName))
            return true;
        return false;
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        // Use this to load an XML file containing references to multiple fragments
        //  (a multi-screen preference screen)
        // loadHeadersFromResource(R.xml.prefs_header, target);

        // Use this load an XML file containing a single preference screen
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PrefsFragmentSettings()).commit();
    }
}
