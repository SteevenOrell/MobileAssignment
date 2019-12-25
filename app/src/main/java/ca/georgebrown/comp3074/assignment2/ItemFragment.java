package ca.georgebrown.comp3074.assignment2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import ca.georgebrown.comp3074.assignment2.dummy.DummyContent;


public class ItemFragment extends Fragment {

    public ItemFragment() {
        // Required empty public constructor
    }

    public static final String KEY = "KEY";
  ListView listV;
  static ArrayAdapter<DummyContent.FoodItem> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_item_1, container, false);

        listV =  v.findViewById(R.id.listV);
        adapter = new ArrayAdapter<DummyContent.FoodItem>(getActivity(),android.R.layout.simple_list_item_1,DummyContent.ITEMS);

        listV.setAdapter(adapter);
        onResume();
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(),DetailActivity.class);

                String itemOnList = ((DummyContent.FoodItem)parent.getItemAtPosition(position)).content;

                i.putExtra(KEY,itemOnList);
                startActivity(i);
            }
        });

        return v; }


}
