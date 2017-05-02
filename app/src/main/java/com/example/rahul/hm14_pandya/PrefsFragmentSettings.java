package com.example.rahul.hm14_pandya;

// Rahul Pandya
// L20355202
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;


public class PrefsFragmentSettings extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    public PrefsFragmentSettings() {
    }

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        // Load preferences from an XML resource
        addPreferencesFromResource(R.xml.prefs_fragment_settings);


    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

        super.onPause();
        // Unregister the listener whenever a key changes
    }

    @Override
    public void onResume() {
        super.onResume();

        // Set a click listener whenever key changes

        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);


        // Set up a click listener
        Preference pref = getPreferenceScreen().findPreference("prefs_highscore");
        int highscore;
        //SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(null);
        SharedPreferences sharedPreferences=getPreferenceScreen().getSharedPreferences();
        highscore=sharedPreferences.getInt("prefs_highscore", 0);
        String s=""+ highscore;
        pref.setSummary(s);

        // Set up a click listener
        pref = getPreferenceScreen().findPreference("key_share");
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                // Launch the share so user can select a sharing method
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Check out this new app : https://play.google.com/store/apps/details?id=com.nikul.hm8_patel_patel";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this new app");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return true;
            }

        });

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
   /*
        if (key.equals("key_music")) {
            boolean b = sharedPreferences.getBoolean("key_music", true);
            if (b) {
            } else {
            }
        }

   */
    }

}

