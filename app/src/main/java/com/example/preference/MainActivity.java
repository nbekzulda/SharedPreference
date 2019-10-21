package com.example.preference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText editText;
    Button save, load, share;
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";
    private int cnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
        load = (Button) findViewById(R.id.load);
        load.setOnClickListener(this);
        share = (Button) findViewById(R.id.share);
        share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save:
                savetext();
                break;
            case R.id.load:
                loadtext();
                break;
            case R.id.share:
                sharetext();
                break;
            default:
                break;
        }
    }



    private void sharetext() {
        Intent mSharingIntent = new Intent(Intent.ACTION_SEND);
        mSharingIntent.setType("text/plain");
        mSharingIntent.putExtra(Intent.EXTRA_SUBJECT, "text");
        mSharingIntent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());
        startActivity(Intent.createChooser(mSharingIntent,"Share text via"));

    }


    private void savetext() {

            sPref = getPreferences( MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(SAVED_TEXT, editText.getText().toString());
            ed.commit();
            Toast.makeText(MainActivity.this, "Text saved", Toast.LENGTH_LONG).show();
        }




    private void loadtext() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        editText.setText(savedText);
        Toast.makeText(MainActivity.this, " Text loaded", Toast.LENGTH_LONG).show();

    }


}
