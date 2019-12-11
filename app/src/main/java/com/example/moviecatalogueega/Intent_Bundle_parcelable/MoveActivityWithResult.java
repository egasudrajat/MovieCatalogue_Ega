package com.example.moviecatalogueega.Intent_Bundle_parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.moviecatalogueega.R;

public class MoveActivityWithResult extends AppCompatActivity implements View.OnClickListener{
    private RadioGroup rbgrup;
    private Button btchoose;

    public static String  EXTRA_SELECTED_VALUE = "result";
    public static int RESULT_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_result);
        rbgrup = findViewById(R.id.Radiogp);
        btchoose = findViewById(R.id.buttonchoose);

        btchoose.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonchoose:
                int value = 0;
                String string = "";
                if(rbgrup.getCheckedRadioButtonId() !=0){
                    switch (rbgrup.getCheckedRadioButtonId()){
                        case R.id.rb100:
                            value = 100;
                            string = "seratus";
                            break;
                        case R.id.rb200:
                            value = 200;
                            string = "dua ratus";
                            break;
                        case R.id.rb300:
                            value = 300;
                            string = "Tiga ratus";
                            break;
                        case R.id.rb400:
                            value = 400;
                            RESULT_CODE = 20;
                            break;

                    }
                }
                Intent intent = new Intent();
                intent.putExtra(EXTRA_SELECTED_VALUE, value);
                setResult(RESULT_CODE, intent);
                finish();

                break;
        }


    }
}
