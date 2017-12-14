package edu.chapman.cpsc356.routegenerator;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import edu.chapman.cpsc356.routegenerator.activities.SettingsActivity;

public class SharedPrefHelper
{
    private static final String KEY_SHARE_TEXT = "share_text";


    public static String GetDefaultShareText(Context ctx)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return prefs.getString(SettingsActivity.KEY_SHARE_TEXT, "");
    }

    public static void SetDefaultShareText(String text, Context ctx)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
        editor.putString(KEY_SHARE_TEXT, text);
        editor.apply();
    }
}
