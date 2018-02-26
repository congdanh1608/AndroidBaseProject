package com.congdanh.androidbaseproject.view.activity.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.Bindable;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.congdanh.androidbaseproject.BR;
import com.congdanh.androidbaseproject.database.entity.Address;
import com.congdanh.androidbaseproject.database.entity.User;
import com.congdanh.androidbaseproject.database.repository.UserAddrAddrRepository;
import com.congdanh.androidbaseproject.view.activity.demo.items.DemoItemData;
import com.congdanh.androidbaseproject.view.baserecyclerview.BaseRecyclerListener;
import com.congdanh.androidbaseproject.view.baserecyclerview.BaseRecyclerViewHandler;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by congd on 2/25/2018.
 */

public class DemoActivityPresenter extends BaseRecyclerViewHandler<DemoItemData> implements BaseRecyclerListener {
    private DemoActivity demoActivity;
    private DemoActivityListener demoActivityListener;
    private UserAddrAddrRepository userAddrRepository;
    private CompositeDisposable compositeDisposable;
    private Context context;

    private String result;
    private String addressId;

    public DemoActivityPresenter(DemoActivity demoActivity, SwipeRefreshLayout refreshLayout,
                                 DemoActivityListener demoActivityListener,
                                 UserAddrAddrRepository userAddrRepository, CompositeDisposable compositeDisposable) {
        super(refreshLayout);
        this.demoActivity = demoActivity;
        this.demoActivityListener = demoActivityListener;
        this.userAddrRepository = userAddrRepository;
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
        context = demoActivity.getBaseContext();
        adapter = new DemoAdapter(context, items, this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void loadData() {
        super.loadData();
        getUsers();
    }

    @Override
    public void onLoadMore(int position) {
        super.onLoadMore(position);
    }

    private void createDatas(List<User> users) {
        //get address for each user
        //create list demo item data for adapter
        List<DemoItemData> items = new ArrayList<>();
        for (User user : users) {
            items.add(new DemoItemData(user));
        }
        addItems(items);
    }

    private void createData(final User user) {
        List<User> items = new ArrayList<User>() {
            {
                add(user);
            }
        };
        createDatas(items);
    }

    public View.OnClickListener onClickButtonListener(final int index) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 0:
                        onRefresh();
//                        getAUser("3");
                        break;
                    case 1:
                        insertUser();
                        break;
                    case 2:
                        deleteAllUsers();
                        break;
                    case 3:
                        insertAddress();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public TextWatcher textWatcherAddressId() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addressId = s.toString();
            }
        };
    }

    //Example for using RxJava with Single to get List Users
    private void getUsers() {
        compositeDisposable.add(userAddrRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .map(new Function<List<User>, List<User>>() {
                    @Override
                    public List<User> apply(List<User> users) throws Exception {
                        for (User user : users) {
                            getAddressForUser(user);
                        }
                        return users;
                    }
                })
                //after observeOn will run on mainThread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        createDatas(users);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })
        );
    }

    //Example for using the AsyncTask in get List Users
    @SuppressLint("StaticFieldLeak")
    public void getUsers_() {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {
                //runs on background thread
                return userAddrRepository.getUsers_();
            }

            @Override
            protected void onPostExecute(List<User> users) {
                //runs on main thread
                createDatas(users);
            }
        }.execute();
    }

    private void insertUser() {
        //Example for using RxJava if you want to return a object
        /*Single.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return userAddrRepository.insertUser_(createUser());
            }
        });*/

        //if none require value return.
        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                userAddrRepository.insertUser(createUser());
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

    //example to get a user with Maybe
    private void getAUser(final String id) {
        compositeDisposable.add(userAddrRepository.getUserById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
//                        createData(user);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("Error", throwable.getMessage());
                            }
                        })
        );
    }

    private void deleteAllUsers() {
        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                userAddrRepository.deleteAllUsers();
            }
        });
    }

    private void insertAddress() {
        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                userAddrRepository.insertAddress(createAddress());
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

    private void getAddressForUser(final User user) {
        compositeDisposable.add(userAddrRepository.getAddressForUser(user.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Address>>() {
                    @Override
                    public void accept(List<Address> addresses) throws Exception {
                        if (!addresses.isEmpty()) {
                            user.setAddress(addresses.get(0));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })
        );
    }

    private User createUser() {
        final User user = new User();
        user.setFullName("Tran Cong Danh");
        user.setPhone("0965 956 6785");
        user.setUsername("soibat");
        return user;
    }

    private Address createAddress() {
        final Address address = new Address();
        address.setUserId(Integer.valueOf(addressId));
        address.setStreet("Salvador");
        address.setHouseNumber("17C");
        address.setCity("Quezon city");
        address.setCountry("Philippines");
        return address;
    }

}
