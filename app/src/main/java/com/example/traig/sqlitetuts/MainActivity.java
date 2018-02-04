package com.example.traig.sqlitetuts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase sqLiteDatabase;
    private SqliteDatabaseHelper sqliteDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        favouritItem favouritItem2  =new favouritItem(0,"tai","0");
        favouritItem favouritItem3  =new favouritItem(1,"tai","1");
        favouritItem favouritItem4  =new favouritItem(2,"tai","2");
        final  SqliteDatabaseHelper sqliteDatabaseHelper = new SqliteDatabaseHelper(getApplicationContext());
        sqliteDatabaseHelper.addFavorit(favouritItem2);
        sqliteDatabaseHelper.addFavorit(favouritItem3);
        sqliteDatabaseHelper.addFavorit(favouritItem4);
        Log.d(TAG, favouritItem2.get_Content());

        // the first auto-incremented id value is 1, not 0
        final  favouritItem favouritItem1 = sqliteDatabaseHelper.getData(1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView textView = findViewById(R.id.textView);
              //  favouritItem favouritItem1 = sqliteDatabaseHelper.getData(0);
                Log.d(TAG, favouritItem1.get_Content());
                textView.setText(favouritItem1.get_pos());
            }
        });
    }
}
