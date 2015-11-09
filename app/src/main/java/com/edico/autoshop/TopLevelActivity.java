package com.edico.autoshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class TopLevelActivity extends AppCompatActivity {

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

        ListView lstView = (ListView) findViewById(R.id.list_autobrands);
        lstView.setOnItemClickListener(itemClickListener);
    }
}
