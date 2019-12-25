package ca.georgebrown.comp3074.assignment2;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

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

public class DetailActivity extends FragmentActivity {

    Button del;

    private FoodDbHelper fdbHelper = null;
    TextView foodName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        foodName = findViewById(R.id.textVItem);
        String item = getIntent().getExtras().getString(ItemFragment.KEY);
        foodName.setText(item);
        fdbHelper = new FoodDbHelper(this);
        fdbHelper.getReadableDatabase();
        del = findViewById(R.id.btnDelete);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = foodName.getText().toString();
                long id = deleteFood(fname);
                Toast.makeText(v.getContext(),"Food with removed successfully", Toast.LENGTH_LONG).show();
                DummyContent.removeItem(fname);  //or you can use cursor to get the id of the object to be deleted
                ItemFragment.adapter.notifyDataSetChanged();

                Intent i = new Intent();
                ((Activity)v.getContext()).setResult(Activity.RESULT_OK, i);
                finish();

            }
        });

    }

    private long deleteFood(String foodname){

        SQLiteDatabase db = fdbHelper.getWritableDatabase();

        String whereClause = " food_name=? ";
        String[]whereArgs = new String[]{foodname};
        return db.delete(FoodContract.FoodEntity.TABLE_NAME,whereClause,whereArgs);
    }

    }

