package com.undencem.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSingle = (Button) findViewById(R.id.btnPlaySingle);
        Button btnStandalone = (Button) findViewById(R.id.btnStandAlone);
        btnSingle.setOnClickListener(this);
        btnStandalone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null ;
        switch(view.getId()){
            case R.id.btnPlaySingle:
                intent = new Intent(this,YoutubeActivity.class);
                break;

            case R.id.btnStandAlone:
                intent = new Intent(this,StandaloneActivity.class);
                break;

            default:
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}

