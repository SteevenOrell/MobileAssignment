package ca.georgebrown.comp3074.assignment2;

import android.provider.BaseColumns;

public final class FoodContract {
    private FoodContract(){}

    public static class FoodEntity implements BaseColumns{

        public static final String TABLE_NAME = "food";
        public static final String COLUMN_NAME_FOOD_NAME = "food_name";


        public static final String SQL_CREATE = "CREATE TABLE "+ TABLE_NAME + " ( "
                + _ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME_FOOD_NAME + " TEXT)";

        public static final String SQL_DROP = " DROP TABLE IF EXISTS" + TABLE_NAME;



    }
}
