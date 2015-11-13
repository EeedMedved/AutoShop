package com.edico.autoshop;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;

import org.w3c.dom.Text;


public class CarActivity extends AppCompatActivity {

    public static final String EXTRA_BMWNO = "BMWNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        int bmwNo = (Integer)getIntent().getExtras().get(EXTRA_BMWNO);
        // Car car = Car.cars[bmwNo];

        new ShowCarTask().execute(bmwNo);
//        try {
//            SQLiteOpenHelper autoshopDatabaseHelper = new AutoshopDatabaseHelper(this);
//            SQLiteDatabase db = autoshopDatabaseHelper.getReadableDatabase();
//
//            Cursor cursor = db.query("CAR", new String[]{"NAME", "DESCRIPTION",
//                    "IMAGE_RESOURCE_ID", "FAVORITE"},
//                    "_id = ?", new String[]{Integer.toString(bmwNo)}, null, null, null);
//
//            if (cursor.moveToFirst()) {
//                String carName = cursor.getString(0);
//                String carDescription = cursor.getString(1);
//                int imgResourceId = cursor.getInt(2);
//                boolean isFavorite = (cursor.getInt(3) == 1);
//
//
//                ImageView photo = (ImageView)findViewById(R.id.photo);
//
//                photo.setImageResource(imgResourceId);
//                photo.setContentDescription(carName);
//
//                // Populate the car name
//                TextView name = (TextView)findViewById(R.id.name);
//                name.setText(carName);
//
//                // Populate the car description
//                TextView description = (TextView)findViewById(R.id.about_car);
//                description.setText(carDescription);
//
//                // Populate the favorite checkbox
//                CheckBox favorite = (CheckBox)findViewById(R.id.favorite);
//                favorite.setChecked(isFavorite);
//            }
//            else
//            {
//                Toast toast = Toast.makeText(this, R.string.db_empty_msg, Toast.LENGTH_LONG);
//                toast.show();
//            }
//
//            cursor.close();
//            db.close();
//        }
//        catch (SQLiteException e) {
//            Log.v("DB_ERR", e.getMessage());
//            Toast toast = Toast.makeText(this, R.string.db_error_msg, Toast.LENGTH_SHORT);
//            toast.show();
//        }
    }

    public void onFavoriteClicked(View view) {
        int carNo = (int)getIntent().getExtras().get(EXTRA_BMWNO);
        new UpdateCarTask().execute(carNo);
    }

    // Inner class to update the car.
    private class UpdateCarTask extends AsyncTask<Integer, Void, Boolean> {

        ContentValues carValues;

        @Override
        protected void onPreExecute() {
            CheckBox favorite = (CheckBox)findViewById(R.id.favorite);
            carValues = new ContentValues();
            carValues.put("FAVORITE", favorite.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... cars) {
            int carNo = cars[0];
            try {
                SQLiteOpenHelper autoshopDatabaseHelper = new AutoshopDatabaseHelper(CarActivity.this);
                SQLiteDatabase db = autoshopDatabaseHelper.getWritableDatabase();
                db.update("CAR", carValues, "_id = ?", new String[]{Integer.toString(carNo)});
                db.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success)
            {
                Toast toast = Toast.makeText(CarActivity.this,
                                                R.string.db_error_msg, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private class ShowCarTask extends AsyncTask<Integer, Void, Car> {

        Car bmw;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Car doInBackground(Integer... cars) {
            int carNo = cars[0];

            try {
                SQLiteOpenHelper autoshopDatabaseHelper = new AutoshopDatabaseHelper(CarActivity.this);
                SQLiteDatabase db = autoshopDatabaseHelper.getReadableDatabase();
                Cursor cursor = db.query("CAR",
                                        new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                                        "_id = ?",
                                        new String[] {Integer.toString(carNo)},
                                        null, null, null
                                        );
                if (cursor.moveToFirst())
                {
                    bmw = new Car();
                    bmw.setName(cursor.getString(0));
                    bmw.setDescription(cursor.getString(1));
                    bmw.setImageResourceId(cursor.getInt(2));
                    bmw.setFavorite(cursor.getInt(3) == 1);
                    cursor.close();
                    db.close();
                    return bmw;
                }
                else
                {
                    return null;
                }
            } catch (SQLiteException e)
            {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Car bmw) {
            if (bmw != null)
            {
                TextView txtName = (TextView)findViewById(R.id.name);
                txtName.setText(bmw.getName());

                TextView txtDescription = (TextView)findViewById(R.id.about_car);
                txtDescription.setText(bmw.getDescription());

                ImageView carImage = (ImageView)findViewById(R.id.photo);
                carImage.setImageResource(bmw.getImageResourceId());
                carImage.setContentDescription(bmw.getName());

                CheckBox chkBoxFavorite = (CheckBox)findViewById(R.id.favorite);
                chkBoxFavorite.setChecked(bmw.getFavorite());

                Toast toast = Toast.makeText(CarActivity.this, bmw.getName(), Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                Toast toast = Toast.makeText(CarActivity.this, R.string.db_error_msg, Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }
}
