### How to room with Rxjava:
First of all, inject the Room Repository
    @Inject
    UserAddrAddrRepository userAddrRepository;

    MyApplication.instance().getAppComponent().plusRoomComponent(new RoomModule()).inject(this);

# Insert database
- Example for using RxJava if you want to return a object:
        Single.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return userAddrRepository.insertUser_(createUser());
            }
        });

- if none require value return:
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

# Fetch database
- Example to get a user with Maybe
        compositeDisposable.add(userAddrRepository.getUserById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })
        );

# Delete database
- Delete without value return:
    Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                userAddrRepository.deleteAllUsers();
            }
        });
    }