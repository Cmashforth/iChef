package com.hci.team.ichef;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**
 * Created by Chris on 25/11/2017.
 */

public class Recipe extends AppCompatActivity {

    private static final String TAG = "Recipe";

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private Button startIChef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Log.d(TAG, "onCreate: Starting.");


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        startIChef = (Button) findViewById(R.id.btnTest);


    }


    private void setupViewPager(ViewPager viewPager){

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Ingredients");
        adapter.addFragment(new Tab2Fragment(), "Preparation");
        viewPager.setAdapter(adapter);


    }

    public void onClick(View view){
        if(view == startIChef){
            Intent intent = new Intent(Recipe.this,IChef.class);
            startActivity(intent);
        }
    }


}
