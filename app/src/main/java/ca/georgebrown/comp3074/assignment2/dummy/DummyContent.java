package ca.georgebrown.comp3074.assignment2.dummy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */

    public static List<FoodItem> ITEMS = new ArrayList<FoodItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, FoodItem> ITEM_MAP = new HashMap<Integer, FoodItem>();

//
//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
//    }

    public static void addItem(FoodItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
   }

    public static void removeItem(String fitem){

        int pos = search(fitem);
        if(pos !=-1){
            FoodItem el = ITEM_MAP.get(pos);
            ITEMS.remove(el);
            ITEM_MAP.remove(pos);
        }
    }

    public static int search(String foodname) {
        for (int x = 0; x < ITEMS.size(); x++) {
            if (ITEMS.get(x).content.equals(foodname)) {

                return x+1;
            }


        }
          return  -1;

    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class FoodItem implements Serializable {
        public final int id;
        public final String content;


        public FoodItem(int id, String content) {
            this.id = id;
            this.content = content;

        }

        @Override
        public String toString() {
            return content;
        }
    }
}
