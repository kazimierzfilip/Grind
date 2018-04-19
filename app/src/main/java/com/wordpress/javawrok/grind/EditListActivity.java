package com.wordpress.javawrok.grind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EditListActivity extends AppCompatActivity {

    private static final String PREFERENCES_TAG = "to_do_list";
    Set<String> list;
    EditText insertedTasks;
    Button saveButton;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);


        insertedTasks = findViewById(R.id.insert_tasks_et);
        saveButton = findViewById(R.id.save_button);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        list = new HashSet<String>();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                String txt = insertedTasks.getText().toString();
                list.add(txt);
                editor.putStringSet(PREFERENCES_TAG, list);
                editor.apply();
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
