package com.hci.team.ichef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Category extends AppCompatActivity {

    private Button drinksButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        drinksButton = (Button) findViewById(R.id.drinks);
    }


    public void onClick(View view){
        if(view == drinksButton){
            Intent changeToDrinks = new Intent(this,RecipeSelection.class);
            startActivity(changeToDrinks);
        }
    }


}
