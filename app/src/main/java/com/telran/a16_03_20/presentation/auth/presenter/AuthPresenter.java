package com.telran.a16_03_20.presentation.auth.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.telran.a16_03_20.business.auth.AuthInteractor;
import com.telran.a16_03_20.di.DependenceFactory;
import com.telran.a16_03_20.presentation.auth.view.AuthView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {
    private static final String TAG = "AuthPresenter";
    AuthInteractor interactor;
    Disposable disposable;

    public AuthPresenter() {
        this.interactor = DependenceFactory.getInstance().getAuthInteractor();
    }


    public void onOkDialogClicked(){
        getViewState().hideErrorDialog();
    }

    public void onLogin(String email, String password){
        getViewState().showProgress();

        disposable = interactor.onLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::authSuccess,throwable -> {
                    Log.e(TAG, "onLogin: ", throwable );
                    onAuthError(throwable.getMessage());
                });
    }

    public void onRegistration(String email, String password){
        getViewState().showProgress();
        disposable = interactor.onRegistration(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::authSuccess,throwable -> {
                    Log.e(TAG, "onRegistration: ", throwable );
                    onAuthError(throwable.getMessage());
                });
    }

    private void authSuccess(){
        getViewState().hideProgress();
        getViewState().showNextView();
    }

    private void onAuthError(String error){
        getViewState().hideProgress();
        getViewState().showError(error);
    }
}
