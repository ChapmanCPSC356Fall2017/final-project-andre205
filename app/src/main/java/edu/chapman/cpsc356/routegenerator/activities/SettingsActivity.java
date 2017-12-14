package edu.chapman.cpsc356.routegenerator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.chapman.cpsc356.routegenerator.R;
import edu.chapman.cpsc356.routegenerator.SharedPrefHelper;

public class SettingsActivity extends AppCompatActivity
{
    public static final String KEY_SHARE_TEXT = "share_text";
    private EditText shareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.shareTextEditText = findViewById(R.id.et_share_text);

        String defaultShareText = SharedPrefHelper.GetDefaultShareText(this);

        this.shareTextEditText.setText(defaultShareText);

    }

    public void onClickSave(View view)
    {
        String defaultShareText = this.shareTextEditText.getText().toString();

        if (!defaultShareText.isEmpty())
        {
            SharedPrefHelper.SetDefaultShareText(defaultShareText, this);
        }

        Toast.makeText(this, R.string.settings_saved, Toast.LENGTH_SHORT).show();
        finish();
    }
}
