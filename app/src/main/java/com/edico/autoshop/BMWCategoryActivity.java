package com.edico.autoshop;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BMWCategoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listBMW = getListView();
        ArrayAdapter<Car> listAdapter = new ArrayAdapter<Car>(
                this,
                android.R.layout.simple_list_item_1,
                Car.cars
        );
        listBMW.setAdapter(listAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(BMWCategoryActivity.this, CarActivity.class);
        intent.putExtra(CarActivity.EXTRA_BMWNO, (int) id);
        startActivity(intent);
    }
}
