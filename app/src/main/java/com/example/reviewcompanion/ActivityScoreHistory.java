package com.example.reviewcompanion;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityScoreHistory extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_history);

        ListView score_history;

//        score_history = (ListView) findViewById(R.id.score_history);
//        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), fruitList, fruitImages);
//        listView.setAdapter(customBaseAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i("CUSTOM_LIST_VIEW", "Item is clicked");
//            }
//        });
    }
}