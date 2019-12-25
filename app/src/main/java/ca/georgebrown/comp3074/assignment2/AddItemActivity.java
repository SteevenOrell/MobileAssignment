package ca.georgebrown.comp3074.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.georgebrown.comp3074.assignment2.dummy.DummyContent;

public class AddItemActivity extends AppCompatActivity {

    private FoodDbHelper fdbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        fdbHelper = new FoodDbHelper(this);
        fdbHelper.getReadableDatabase();

        Button button = findViewById(R.id.btnSave);
        final EditText txtItem = findViewById(R.id.editTextItem);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String it = txtItem.getText().toString();

                if(it.length()>0){

                    long id = addFood(it);
                    Toast.makeText(v.getContext(),"Food added successfully", Toast.LENGTH_LONG).show();


                }
                DummyContent.FoodItem item = new DummyContent.FoodItem((DummyContent.ITEMS.size()+1), it);
                DummyContent.ITEMS.add(item);
                DummyContent.ITEM_MAP.put(item.id, item);

                ItemFragment.adapter.notifyDataSetChanged();
                Intent i = new Intent();
                ((Activity)v.getContext()).setResult(Activity.RESULT_OK, i);
                finish();
            }
        });


    }

    private long addFood(String foodname){

        SQLiteDatabase db = fdbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FoodContract.FoodEntity.COLUMN_NAME_FOOD_NAME,foodname);

        return db.insert(FoodContract.FoodEntity.TABLE_NAME,null,cv);
    }


    protected void onDestroy(){
        fdbHelper.close();
        super.onDestroy();

    }
}
