package com.telran.a16_03_20.data.provider.store;

public interface StoreProvider {
    boolean saveToken(String token);
    boolean clearToken();
    String getToken();
}
