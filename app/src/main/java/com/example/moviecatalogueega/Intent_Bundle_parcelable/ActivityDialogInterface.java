package com.example.moviecatalogueega.Intent_Bundle_parcelable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.moviecatalogueega.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityDialogInterface extends AppCompatActivity {
    private AppCompatTextView hsil1, hsil2, hsil3;
    private AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_interface);
        hsil1 = findViewById(R.id.hsl1);
        hsil2 = findViewById(R.id.hsl2);
        hsil3 = findViewById(R.id.hsl3);
        AppCompatButton btdlg = findViewById(R.id.btdlg);
        btdlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForm();
            }
        });
    }

    private void DialogForm() {
        dialog = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.form_dialog_interface, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Form Biodata");

        final AppCompatSpinner spinner = dialogView.findViewById(R.id.spinner);
        final TextInputEditText inputEditText = dialogView.findViewById(R.id.et);
        final RadioGroup radioGroup = dialogView.findViewById(R.id.rG);
        int selected = radioGroup.getCheckedRadioButtonId();
        final RadioButton radioButton = dialogView.findViewById(selected);

        AppCompatButton button = dialogView.findViewById(R.id.btsubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int selected = radioGroup.getCheckedRadioButtonId();
                String string = null;
                switch (selected) {
                    case R.id.rdlaki:
                        string = "Laki";
                        break;
                    case R.id.rdperem:
                        string = "Perempuan";
                        break;
                }
                hsil1.setText(spinner.getSelectedItem().toString());
                hsil2.setText(inputEditText.getText().toString());
                hsil3.setText(string);
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
