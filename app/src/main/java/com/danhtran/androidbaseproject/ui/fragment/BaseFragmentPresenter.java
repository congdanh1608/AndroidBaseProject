package com.danhtran.androidbaseproject.ui.fragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by DanhTran on 5/31/2019.
 */
public abstract class BaseFragmentPresenter {
    protected Disposable disposable;

    public abstract void initInject();

    public BaseFragmentPresenter() {
        this.disposable = new CompositeDisposable();

        initInject();
    }
}
