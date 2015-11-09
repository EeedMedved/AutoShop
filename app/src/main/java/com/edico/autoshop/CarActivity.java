package com.edico.autoshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CarActivity extends AppCompatActivity {

    public static final String EXTRA_BMWNO = "BMWNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        int bmwNo = (Integer)getIntent().getExtras().get(EXTRA_BMWNO);
        Car car = Car.cars[bmwNo];

        ImageView photo = (ImageView)findViewById(R.id.photo);
//
        photo.setImageResource(car.getImageResourceId());
        photo.setContentDescription(car.getName());
//
//        // Populate the car name
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(car.getName());
//
//        // Populate the car description
        TextView description = (TextView)findViewById(R.id.about_car);
        description.setText(car.getDescription());
    }
}
