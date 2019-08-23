package com.danhtran.androidbaseproject.ui.fragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by DanhTran on 5/31/2019.
 */
public abstract class BaseFragmentPresenter {
    private Disposable disposable;
    private Disposable disposable2;
    private Disposable disposable3;

    public abstract void initInject();

    public BaseFragmentPresenter() {
        this.disposable = new CompositeDisposable();
        this.disposable2 = new CompositeDisposable();
        this.disposable3 = new CompositeDisposable();

        initInject();
    }
}
