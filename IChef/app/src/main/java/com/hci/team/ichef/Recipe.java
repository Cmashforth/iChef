package com.hci.team.ichef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Chris on 25/11/2017.
 */

public class Recipe extends AppCompatActivity {

    private Button startIChef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        startIChef = (Button) findViewById(R.id.ichef);
    }

    public void onClick(View view){
        if(view == startIChef){
            Intent changeToIChef = new Intent(this,IChef.class);
            startActivity(changeToIChef);
        }
    }


}
