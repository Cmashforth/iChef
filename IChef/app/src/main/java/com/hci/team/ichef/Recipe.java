package com.hci.team.ichef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chris on 25/11/2017.
 */

public class Recipe extends AppCompatActivity {

    private Button startIChef;
    private Button ingredientsButton;
    private Button prepButton;
    private TextView ingredientsList;
    private TextView prepList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        startIChef = (Button) findViewById(R.id.ichef);
        ingredientsButton = (Button) findViewById(R.id.ingredients);
        prepButton = (Button) findViewById(R.id.prep);
        ingredientsList = (TextView) findViewById(R.id.ingredientsList);
        prepList = (TextView) findViewById(R.id.prepList);

    }

    public void onClick(View view){
        if(view == startIChef){
            Intent changeToIChef = new Intent(this,IChef.class);
            startActivity(changeToIChef);
        }else if(view == ingredientsButton){
            prepList.setVisibility(View.INVISIBLE);
            ingredientsList.setVisibility(View.VISIBLE);
        }else if(view == prepButton){
            ingredientsList.setVisibility(View.INVISIBLE);
            prepList.setVisibility(View.VISIBLE);
        }
    }


}
