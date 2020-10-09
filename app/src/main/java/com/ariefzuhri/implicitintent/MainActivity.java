package com.ariefzuhri.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText edtWebsite, edtLocation, edtShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWebsite = findViewById(R.id.edt_website);
        edtLocation = findViewById(R.id.edt_location);
        edtShare = findViewById(R.id.edt_share);
    }

    public void openWebsite(View view) {
        String url = edtWebsite.getText().toString();
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d(TAG, "Can't handle this action!");
        }
    }

    public void openLocation(View view) {
        String location = edtLocation.getText().toString();
        Uri uri = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d(TAG, "Can't handle this action!");
        }
    }

    public void shareText(View view) {
        String share = edtShare.getText().toString();
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this text with:")
                .setText(share)
                .startChooser();
    }
}