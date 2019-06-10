package com.danhtran.androidbaseproject.ui.dialog_fragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by DanhTran on 6/10/2019.
 */
public abstract class BaseDialogFragmentPresenter {
    protected Disposable disposable;

    public abstract void initInject();

    public BaseDialogFragmentPresenter() {
        this.disposable = new CompositeDisposable();

        initInject();
    }
}
