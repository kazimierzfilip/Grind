package com.wordpress.javawrok.grind;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_TAG = "to_do_list";
    private static final int REQUEST_TASKS = 100;

//    private Set<String> list;
    private ArrayList<String> list;
    private TextView viewTask;
    private Button nextButton;
    private Iterator<String> listIterator;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.list)));

//        list = sharedPref.getStringSet(PREFERENCES_TAG, null);
//        if(list==null){
//            Intent getListIntent = new Intent(this, EditListActivity.class);
//            startActivityForResult(getListIntent,REQUEST_TASKS);
//        } else {
            listIterator = list.iterator();

            viewTask = findViewById(R.id.task_tv);
        if(listIterator.hasNext())
            viewTask.setText(listIterator.next());
//        }
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listIterator.hasNext())
                    viewTask.setText(listIterator.next()+".");
                else
                    viewTask.setText("done.");
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_TASKS) {
//            if (resultCode == RESULT_OK) {
//                list = sharedPref.getStringSet(PREFERENCES_TAG, null);
//                listIterator = list.iterator();
//
//                viewTask = findViewById(R.id.task_tv);
//
//                viewTask.setText(listIterator.next());
//            }
//        }
//    }
}
