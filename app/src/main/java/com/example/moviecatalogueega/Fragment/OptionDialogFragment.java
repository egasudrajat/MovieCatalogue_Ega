package com.example.moviecatalogueega.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.moviecatalogueega.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionDialogFragment extends DialogFragment implements View.OnClickListener {

    public interface OnOptionDialogListener {
        void OnOptionChosen(String text);
    }

    private OnOptionDialogListener optionDialogListener;

    private RadioGroup rbGrup;
    private RadioButton rbsaf, rbmou, rblpg, rbmoyes;


    public OptionDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rbGrup = view.findViewById(R.id.rGgrup);
        rbsaf = view.findViewById((R.id.rb_sap));
        rbmou = view.findViewById((R.id.rb_mou));
        rblpg = view.findViewById((R.id.rb_lvg));
        rbmoyes = view.findViewById((R.id.rb_moyes));
        Button btchoose = view.findViewById(R.id.btn_choose);
        Button btclose = view.findViewById(R.id.btn_close);

        btclose.setOnClickListener(this);
        btchoose.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();

        if (fragment instanceof DetailCategoryFragment) {
            DetailCategoryFragment detailCategoryFragment = (DetailCategoryFragment) fragment;
            this.optionDialogListener = detailCategoryFragment.optionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.optionDialogListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                getDialog().cancel();
                break;
            case R.id.btn_choose:

                int checkedradiobuttonid = rbGrup.getCheckedRadioButtonId();
                if (checkedradiobuttonid != -1) {
                    String coach = null;
                    switch (checkedradiobuttonid) {
                        case R.id.rb_sap:
                            coach = rbsaf.getText().toString().trim();
                            break;
                        case R.id.rb_mou:
                            coach = rbmou.getText().toString().trim();
                            break;
                        case R.id.rb_lvg:
                            coach = rblpg.getText().toString().trim();
                            break;
                        case R.id.rb_moyes:
                            coach = rbmoyes.getText().toString().trim();
                            break;
                    }
                    if (optionDialogListener != null) {
                        optionDialogListener.OnOptionChosen(coach);
                    }
                }
                getDialog().dismiss();
        }

    }



}
