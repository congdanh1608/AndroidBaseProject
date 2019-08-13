package com.danhtran.androidbaseproject.ui.activity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by DanhTran on 5/31/2019.
 */
public abstract class BaseActivityPresenter {
    private Disposable disposable;

    public abstract void initInject();

    public BaseActivityPresenter() {
        this.disposable = new CompositeDisposable();

        initInject();
    }
}

