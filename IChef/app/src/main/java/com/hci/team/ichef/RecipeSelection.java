package com.hci.team.ichef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


/**
 * Created by Chris on 25/11/2017.
 */

public class RecipeSelection extends AppCompatActivity {

    ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeselection);

        mImageButton = (ImageButton) findViewById(R.id.saladOptionBtn2);
    }

    public void onClick(View view){
        if(view == mImageButton){
            Intent intent = new Intent(RecipeSelection.this, Recipe.class);
            startActivity(intent);
        }
    }
}
