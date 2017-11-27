package com.hci.team.ichef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Category extends AppCompatActivity {

    private Button saladButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        saladButton = (Button) findViewById(R.id.salads);
    }


    public void onClick(View view){
        if(view == saladButton){
            Intent changeToDrinks = new Intent(this,RecipeSelection.class);
            startActivity(changeToDrinks);
        }
    }


}
