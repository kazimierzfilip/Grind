package com.wordpress.javawrok.grind;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private static int sCount;
    private static int sRoutine;
    private static final String POSITION = "pos";
    private static final String ROUTINE = "rut";
    private Vibrator mVibrator;

    private ArrayList<String> mStringArrayList;
    private TextView mTextViewTask;
    private Button mButtonNext;
    private Iterator<String> mStringIterator;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        mStringArrayList = new ArrayList<>();
        mStringArrayList.add("Welcome");

        if(savedInstanceState!=null) {
            int routine = savedInstanceState.getInt(ROUTINE);
            if(routine == 1){
                mStringArrayList = new ArrayList<>(
                        Arrays.asList(getResources().getStringArray(R.array.list1)));
                sRoutine=1;
            } else if(routine == 2) {
                mStringArrayList = new ArrayList<>(
                        Arrays.asList(getResources().getStringArray(R.array.list2)));
                sRoutine = 2;
            }
        }

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        mStringIterator = mStringArrayList.iterator();

        mTextViewTask = findViewById(R.id.text_task);

        if(savedInstanceState!=null){
            int pos = savedInstanceState.getInt(POSITION);
            for(int i=0; i<pos; i++){
                mStringIterator.next();
            }
        }
        displayItem();
        mButtonNext = findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayItem();
            }
        });
    }

    void vibration(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mVibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            //deprecated in API 26
            mVibrator.vibrate(500);
        }
    }

    void displayItem(){
        if(mStringIterator.hasNext()){
            vibration();
            String s = mStringIterator.next();
            if(!s.endsWith(".") && !s.endsWith("?") && !s.endsWith("!"))
                s=s+".";
            mTextViewTask.setText(s);
            sCount += 1;
        }
        else{
            sCount = 0;
            mTextViewTask.setText("done.");
        }
    }

    public void showRoutine1(MenuItem item){
        mStringArrayList = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.list1)));
        sRoutine = 1;
        sCount = 0;
        mStringIterator = mStringArrayList.iterator();
        displayItem();
    }

    public void showRoutine2(MenuItem item){
        mStringArrayList = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.list2)));
        sRoutine=2;
        sCount = 0;
        mStringIterator = mStringArrayList.iterator();
        displayItem();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, sCount);
        outState.putInt(ROUTINE, sRoutine);
    }

}
