package com.telran.a16_03_20.di;

import android.content.Context;

import com.telran.a16_03_20.business.auth.AuthInteractor;
import com.telran.a16_03_20.business.auth.AuthInteractorImpl;
import com.telran.a16_03_20.data.auth.AuthRepository;
import com.telran.a16_03_20.data.auth.AuthRepositoryImpl;
import com.telran.a16_03_20.data.provider.store.SprefStoreProvider;
import com.telran.a16_03_20.data.provider.store.StoreProvider;

public class DependenceFactory {
    private static DependenceFactory instance;
    private StoreProvider storeProvider;
    private AuthRepository authRepository;
    private AuthInteractor authInteractor;

    public DependenceFactory(Context context){
        storeProvider = new SprefStoreProvider(context);
        authRepository = new AuthRepositoryImpl(ApiProvider.getInstance().getApiRx(),storeProvider);
        authInteractor = new AuthInteractorImpl(authRepository);
        instance = this;
    }

    public AuthInteractor getAuthInteractor(){
        return authInteractor;
    }

    public static DependenceFactory getInstance(){
        return instance;
    }
}
