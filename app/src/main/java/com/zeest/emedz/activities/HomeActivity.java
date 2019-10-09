package com.zeest.emedz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;

import com.zeest.emedz.R;
import com.zeest.emedz.adapters.PharmaciesAdapter;
import com.zeest.emedz.models.PharmaciesModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private List<PharmaciesModel> pharmaciesModels;
    private RecyclerView rvPharmacies;
    private RelativeLayout rlSearchBOx;

    // Declaring the Toolbar Object
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initialize all views
        initAllViews();

        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        //Set Adapters
        setAdapters();

        //Set All Listeners
        setAllListeners();

    }

    private void setAllListeners() {
        rlSearchBOx.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void setAdapters() {

        /*Set the Pharmacies adapter*/
        PharmaciesAdapter pharmaciesAdapter = new PharmaciesAdapter(pharmaciesModels, this);
        setPharmaciesAdapter(pharmaciesAdapter);

    }

    private void setPharmaciesAdapter(PharmaciesAdapter pharmaciesAdapter) {

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPharmacies.setLayoutManager(horizontalLayoutManager);
        rvPharmacies.setAdapter(pharmaciesAdapter);
    }

    private void initAllViews() {
        rvPharmacies = findViewById(R.id.rvPharmacies);
        rlSearchBOx = findViewById(R.id.rlSearchBOx);
        toolbar = findViewById(R.id.tool_bar);
        pharmaciesModels = fillItems();
    }

    private List<PharmaciesModel> fillItems() {
        List<PharmaciesModel> pharms = new ArrayList<>();
        pharms.add(new PharmaciesModel(1, "D.Watson", ""));
        pharms.add(new PharmaciesModel(2, "Shaheen Chemist", ""));
        pharms.add(new PharmaciesModel(3, "Maa Pharmacy", ""));
        pharms.add(new PharmaciesModel(4, "Najeeb Pharmacy", ""));
        return pharms;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rlSearchBOx) {
            startActivity(new Intent(getApplicationContext(), SearchActivity.class));
        }
    }
}