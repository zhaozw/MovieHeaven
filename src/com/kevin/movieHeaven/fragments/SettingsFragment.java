package com.kevin.movieHeaven.fragments;

import java.util.Set;

import com.kevin.movieHeaven.R;
import com.kevin.movieHeaven.MovieHeavenApplication;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.widget.Toast;

public class SettingsFragment extends PreferenceFragment implements OnPreferenceChangeListener {

    private MultiSelectListPreference menuPref;
    private ListPreference sitePref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        menuPref = (MultiSelectListPreference) findPreference("menu_list");
        sitePref = (ListPreference)findPreference("site_list");

        menuPref.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        MovieHeavenApplication app = (MovieHeavenApplication) getActivity().getApplication();
        if (preference == menuPref) {
            Set<String> newMenus = (Set<String>) newValue;
            if (newMenus.size() == 0) {
                Toast.makeText(getActivity(), "应最少选择一项，请重新选择", Toast.LENGTH_LONG).show();
                return false;
            }
            app.preferencesChanged();
        } else if (preference == sitePref){
            app.preferencesChanged();
        }
        return true;
    }
}
