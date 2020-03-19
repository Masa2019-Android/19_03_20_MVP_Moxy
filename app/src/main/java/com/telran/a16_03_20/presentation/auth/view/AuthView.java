package com.telran.a16_03_20.presentation.auth.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AuthView extends MvpView {
    void showProgress();
    void hideProgress();
    @StateStrategyType(SingleStateStrategy.class)
    void showError(String error);
    @StateStrategyType(SingleStateStrategy.class)
    void showNextView();
    @StateStrategyType(SingleStateStrategy.class)
    void hideErrorDialog();
}
