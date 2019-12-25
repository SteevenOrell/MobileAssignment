package ca.georgebrown.comp3074.assignment2;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import ca.georgebrown.comp3074.assignment2.dummy.DummyContent;

public class MainActivity extends FragmentActivity {

    private FoodDbHelper fdbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fdbHelper = new FoodDbHelper(this);
        fdbHelper.getReadableDatabase();
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        Cursor c = getAllWords();

        while(c.moveToNext()){
            Log.d("test id : ",c.getString(c.getColumnIndex(FoodContract.FoodEntity.COLUMN_NAME_FOOD_NAME)));
            DummyContent.FoodItem w = new DummyContent.FoodItem(DummyContent.ITEMS.size()+1,c.getString(c.getColumnIndexOrThrow(
                    FoodContract.FoodEntity.COLUMN_NAME_FOOD_NAME)));

            DummyContent.addItem(w);
            DummyContent.ITEM_MAP.put(w.id, w);
        }
        ItemFragment.adapter.notifyDataSetChanged();
        c.close();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                Intent i = new Intent(v.getContext(), AddItemActivity.class);
                startActivityForResult(i, 1);
            }
        });



    }

    private Cursor getAllWords(){

        SQLiteDatabase db = fdbHelper.getReadableDatabase();

        String[] projection = {
                FoodContract.FoodEntity._ID,
                FoodContract.FoodEntity.COLUMN_NAME_FOOD_NAME,

        };
        String selection =   null;
        String[] selectionArgs = null;
        return db.query(
                FoodContract.FoodEntity.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null


        );
    }

    protected void onDestroy(){
        fdbHelper.close();
        super.onDestroy();

    }

}
