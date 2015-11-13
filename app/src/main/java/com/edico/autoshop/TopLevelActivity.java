package com.edico.autoshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;
import android.widget.CursorAdapter;
import android.widget.Toast;

public class TopLevelActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor favoritesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id)
            {
                if (position == 0)
                {
                    Intent intent = new Intent(TopLevelActivity.this, BMWCategoryActivity.class);
                    startActivity(intent);
//                    TextView selectedView = (TextView) itemView;
//                    String msg = selectedView.getText().toString();
//
//                    TextView txtViewMsg = (TextView) findViewById(R.id.txtViewMsg);
//                    txtViewMsg.setText(msg);
                }
            }
        };

        // Add the listener to the Options ListView
        ListView lstView = (ListView) findViewById(R.id.list_autobrands);
        lstView.setOnItemClickListener(itemClickListener);

        // Poputlate the list_favorites ListView from a cursor
        ListView listFavorites = (ListView)findViewById(R.id.list_favorites);

        try {
            SQLiteOpenHelper autoshopDatabaseHelper = new AutoshopDatabaseHelper(this);
            db = autoshopDatabaseHelper.getReadableDatabase();
            favoritesCursor = db.query("CAR",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(
                                    TopLevelActivity.this,
                                    android.R.layout.simple_list_item_1,
                                    favoritesCursor,
                                    new String[] {"NAME"},
                                    new int[] {android.R.id.text1},
                                    0);

            listFavorites.setAdapter(favoriteAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        // Navigate to DrinkActivity if a drink is clicked
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, CarActivity.class);
                intent.putExtra(CarActivity.EXTRA_BMWNO, (int)id);
                startActivity(intent);
            }
        });


    }
    // Close the cursor and database in the onDestroy() method
    @Override
    public void onDestroy() {
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        try {
            AutoshopDatabaseHelper autoshopDatabaseHelper = new AutoshopDatabaseHelper(this);
            db = autoshopDatabaseHelper.getReadableDatabase();
            Cursor newCursor =
                    db.query("CAR",
                            new String[] {"_id", "NAME"},
                            "FAVORITE = 1",
                            null, null, null, null);
            ListView listFavorites = (ListView)findViewById(R.id.list_favorites);
            CursorAdapter adapter = (CursorAdapter) listFavorites.getAdapter();
            adapter.changeCursor(newCursor);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, R.string.db_error_msg, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
