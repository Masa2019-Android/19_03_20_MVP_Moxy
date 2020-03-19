package com.telran.a16_03_20.presentation.auth.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.telran.a16_03_20.R;
import com.telran.a16_03_20.presentation.auth.presenter.AuthPresenter;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthFragment extends MvpAppCompatFragment implements AuthView, View.OnClickListener {
    EditText inputEmail, inputPassword;
    ProgressBar myProgress;
    Button loginBtn, regBtn;
    AlertDialog errorDialog;

    @InjectPresenter
    AuthPresenter presenter;

    public AuthFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        inputEmail = view.findViewById(R.id.inputEmail);
        inputPassword = view.findViewById(R.id.inputPassword);
        loginBtn = view.findViewById(R.id.loginBtn);
        regBtn = view.findViewById(R.id.regBtn);
        myProgress = view.findViewById(R.id.myProgress);

        loginBtn.setOnClickListener(this);
        regBtn.setOnClickListener(this);
//        presenter.onAttach(this);
        return view;
    }

    @Override
    public void onDestroyView() {
//        presenter.onDetachView();
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        myProgress.setVisibility(View.VISIBLE);
        regBtn.setEnabled(false);
        loginBtn.setEnabled(false);
        inputPassword.setEnabled(false);
        inputEmail.setEnabled(false);
    }

    @Override
    public void hideProgress() {
        myProgress.setVisibility(View.INVISIBLE);
        regBtn.setEnabled(true);
        loginBtn.setEnabled(true);
        inputPassword.setEnabled(true);
        inputEmail.setEnabled(true);
    }

    @Override
    public void showError(String error) {
        errorDialog = new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                .setTitle("Error")
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton("Ok", (dialog, which) -> presenter.onOkDialogClicked())
                .create();
        errorDialog.show();
    }

    @Override
    public void showNextView() {
        //Todo implements navigation to next view
        Toast.makeText(getContext(), "Show Next Fragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideErrorDialog() {
        if(errorDialog != null && errorDialog.isShowing()){
            errorDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginBtn){
            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();
            presenter.onLogin(email,password);
        }else if(v.getId() == R.id.regBtn){
            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();
            presenter.onRegistration(email,password);
        }
    }
}
