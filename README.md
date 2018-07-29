# AndroidBaseProject
Android Base Project

Base project was built implements the Dagger2 and Data Binding with MVP pattern (custom between MVP and MVVM).
It has several managers classes that encapsulate features that are usually used in a common project, most using inversion control to decouple the implementation.

This project has the most common setttings applied. The main idea is to start any new Android project by cloning this one.
It will help us build an new project faster and cheaper. It also has some examples how to use some library, dependencies... 
which are the most common libraries in a Android project.

Developed by the [Danh Cong Tran](https://github.com/congdanh1608).

### Included Libraries
This is some main libraries:

- [Data binding](https://developer.android.com/topic/libraries/data-binding/index.html)
Data Binding Library to write declarative layouts and minimize the glue code necessary to bind your application logic and layouts.

- [Dagger2](https://google.github.io/dagger/)
Compile-time dependency injection framework.
 
- [Retrofit](http://square.github.io/retrofit/)
Networking library that allows developers to easily make request to a server through Annotations.

- [RxJava](https://github.com/ReactiveX/RxJava)
A library for composing asynchronous and event-based programs by using observable sequences.

- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
This module adds the minimum classes to RxJava that make writing reactive components in Android applications easy and hassle-free.

- [Room](https://developer.android.com/topic/libraries/architecture/room.html)
Database library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

- [Glide](https://github.com/bumptech/glide)
Fast and efficient open source media management and image loading framework.

- [Social login](https://github.com/congdanh1608/AndroidBaseProject/tree/master/social_login)
Authentication with google and facebook faster and easier.

### Package Structure
```
androidbaseproject/app/src/main/java/com/congdanh/androidbaseproject/
└───appmodule
│   │   ...
└───database
│   └─── dao
│   │    │   ...
│   └─── db
│   │    │   ...
│   └─── entity
│   │    │   ...
│   └─── respository
│   │    │   ...
└───di
│   └─── component
│   │    │   ...
│   └─── module
│   │    │   ...
│   └─── scope
│   │    │   ...
└───enums
│   │   ...
└───serviceAPI
│   └─── apiconfig
│   │    │   ...
│   └─── apiservice
│   │    │   ...
│   └─── json
│   │    │   ...
│   └─── model
│   │    │   ...
└───sharepreferences
│   │   ...
└───utils
│   │   ...
└───view
    └─── activity
    │    │   ...
    └─── baserecyclerview
    │    │   ...
    └─── customadapter
    │    │   ...
    └─── customview
    │    │   ...
    └─── dialogfragment
    │    │   ...
    └─── fragment
```

### Recommendation

If you are going to develop a big application, I recommend to create one package for each functionality (screen).

### Base Project Architectures

There is a branch with the following architectures:

- MVP + Data binding

- Room + Dagger2 + RxJava/RxAndroid

- Retrofit + Dagger2 + RxJava/RxAndroid

