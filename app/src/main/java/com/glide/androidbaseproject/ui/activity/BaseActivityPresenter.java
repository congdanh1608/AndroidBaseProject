package com.glide.androidbaseproject.ui.activity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by DanhTran on 5/31/2019.
 */
public abstract class BaseActivityPresenter {
    private Disposable disposable;
    private Disposable disposable2;
    private Disposable disposable3;

    public abstract void initInject();

    public BaseActivityPresenter() {
        this.disposable = new CompositeDisposable();
        this.disposable2 = new CompositeDisposable();
        this.disposable3 = new CompositeDisposable();

        initInject();
    }
}

