package com.zeest.emedz.activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zeest.emedz.R;
import com.zeest.emedz.adapters.MedicineAdapter;
import com.zeest.emedz.models.Medicine;
import com.zeest.emedz.utils.MyDividerItemDecoration;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements MedicineAdapter.MedicineAdapterListener {

    public static final String TAG = SearchActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private MedicineAdapter mAdapter;
    private List<Medicine> medicines;
    private SearchView searchView;

    // url to fetch contacts json
    private static final String URL = "https://api.androidhive.info/json/contacts.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Declaring the Toolbar Object
        Toolbar toolbar = findViewById(R.id.tool_bar_search);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Medicines");
        }

        recyclerView = findViewById(R.id.recycler_view);
        medicines = new ArrayList<>();
        mAdapter = new MedicineAdapter(medicines, getApplicationContext(), this);

        // white background notification bar
        whiteNotificationBar(recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        fetchContacts();
    }

    private void fetchContacts() {
        try {
            JsonArrayRequest request = new JsonArrayRequest(URL,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if (response == null) {
                                Toast.makeText(getApplicationContext(), "Couldn't fetch the medicine! Pleas try again.", Toast.LENGTH_LONG).show();
                                return;
                            }

                            List<Medicine> items = new Gson().fromJson(response.toString(), new TypeToken<List<Medicine>>() {
                            }.getType());

                            // adding contacts to contacts list
                            medicines.clear();
                            medicines.addAll(items);

                            // refreshing recycler view
                            mAdapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // error in getting json
                    Log.e(TAG, "Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            MyApplication.getInstance().addToRequestQueue(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        }
        super.onBackPressed();
        return true;
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onMedicineSelected(Medicine medicine) {
        Toast.makeText(getApplicationContext(), "Selected: " + medicine.getName(), Toast.LENGTH_LONG).show();
    }
}
