package com.hci.team.ichef;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Category extends AppCompatActivity {

    ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mImageButton = (ImageButton) findViewById(R.id.saladsBtn);

    }

    public void onClick(View view) {
        if(view == mImageButton){
            Intent intent = new Intent(Category.this, RecipeSelection.class);
            startActivity(intent);
        }

    }
}
