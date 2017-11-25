package com.hci.team.ichef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Chris on 25/11/2017.
 */

public class RecipeSelection extends AppCompatActivity {

    private Button selectionOne;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeselection);

        selectionOne = (Button) findViewById(R.id.selectionOne);
    }


    public void onClick(View view){
        if(view == selectionOne){
            Intent changeToRecipe = new Intent(this,Recipe.class);
            startActivity(changeToRecipe);
        }
    }
}
