package com.edico.autoshop;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

/**
 * Created by SennaTOR on 09.11.2015.
 */
class AutoshopDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "autoshop";
    private static final int DB_VERSION = 2;

    AutoshopDatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion, newVersion);
    }

    private static void insertCar(SQLiteDatabase db, String name, String description,
                                  int resourceID)
    {
        ContentValues carValues = new ContentValues();
        carValues.put("NAME", name);
        carValues.put("DESCRIPTION", description);
        carValues.put("IMAGE_RESOURCE_ID", resourceID);
        db.insert("CAR", null, carValues);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion < 1)
        {
            db.execSQL("CREATE TABLE CAR (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NAME TEXT, " +
                    "DESCRIPTION TEXT, " +
                    "IMAGE_RESOURCE_ID INTEGER);");
            insertCar(db, "BMW 330i",
                    "На протяжении 40 лет BMW 3 серии представляет собой идеальное воплощение спортивного седана.",
                    R.drawable.bmw330);
            insertCar(db, "BMW M550d",
                    "Инженеры BMW M воплотили свой уникальный опыт в модели BMW M550d xDrive с эксклюзивными, точно настраиваемыми системами трансмиссии, подвески и рулевого управления.",
                    R.drawable.bmwm550d);
            insertCar(db, "BMW 750",
                    "BMW 7 is the best car in the world.",
                    R.drawable.bmw750);
        }

        if (oldVersion < 2)
        {
            db.execSQL("ALTER TABLE CAR ADD COLUMN FAVORITE NUMERIC");
        }
    }
}
