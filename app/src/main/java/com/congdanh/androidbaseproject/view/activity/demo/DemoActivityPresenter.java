package com.congdanh.androidbaseproject.view.activity.demo;

import android.annotation.SuppressLint;
import android.databinding.Bindable;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.congdanh.androidbaseproject.BR;
import com.congdanh.androidbaseproject.MyApplication;
import com.congdanh.androidbaseproject.database.entity.User;
import com.congdanh.androidbaseproject.database.repository.UserRepository;
import com.congdanh.androidbaseproject.view.activity.demo.items.DemoItemData;
import com.congdanh.androidbaseproject.view.baserecyclerview.BaseRecyclerListener;
import com.congdanh.androidbaseproject.view.baserecyclerview.BaseRecyclerViewHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by congd on 2/25/2018.
 */

public class DemoActivityPresenter extends BaseRecyclerViewHandler<DemoItemData> implements BaseRecyclerListener {
    private DemoListener demoListener;
    private UserRepository userRepository;
    private CompositeDisposable compositeDisposable;

    private String result;

    public DemoActivityPresenter(SwipeRefreshLayout refreshLayout, DemoListener demoListener, UserRepository userRepository,
                                 CompositeDisposable compositeDisposable) {
        super(refreshLayout);
        this.demoListener = demoListener;
        this.userRepository = userRepository;
        this.compositeDisposable = compositeDisposable;

        init();
    }

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
    }

    private void init() {
        adapter = new DemoAdapter(MyApplication.Instance(), items, this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void loadData() {
        super.loadData();
        getUsers();
//        getUsers_();
    }

    @Override
    public void onLoadMore(int position) {
        super.onLoadMore(position);
    }

    private void createDatas(List<User> users) {
        List<DemoItemData> items = new ArrayList<>();
        for (User user : users) {
            items.add(new DemoItemData(user));
        }
        addItems(items);
    }

    public View.OnClickListener onClickButtonListener(final int index) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 0:
                        onRefresh();
                        break;
                    case 1:
//                        insertUser();
                        insertUser_();
                        break;
                    case 2:
                        deleteAllUsers();
                        break;
                }
            }
        };
    }

    private void getUsers() {
        compositeDisposable.add(userRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> userEntities) throws Exception {
                        createDatas(userEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })
        );
    }

    @SuppressLint("StaticFieldLeak")
    public void getUsers_() {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {
                //runs on background thread
                return userRepository.getUsers_();
            }

            @Override
            protected void onPostExecute(List<User> users) {
                //runs on main thread
                createDatas(users);
            }
        }.execute();
    }

    private void insertUser() {
        userRepository.insertUser(createUser());
    }

    private void insertUser_() {
        //example rxjava to return value
        /*Single.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return userRepository.insertUser_(createUser());
            }
        });*/

        //example rxjava with void
        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                userRepository.insertUser_(createUser());
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private User createUser() {
        final User user = new User();
        user.setAddress("17C Salvador, Varsity Hills, Loyola Heights");
        user.setFullName("Tran Cong Danh");
        user.setPhone("0965 956 6785");
        user.setUsername("soibat");
        return user;
    }

    private void deleteAllUsers() {
        userRepository.deleteAllUsers();
    }
}
