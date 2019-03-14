package com.example.observerpatternproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Integer> integerObservable = Observable
                .just(1, 2, 3, 4, 5)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
                    }
                });
        Observable<String> stringObservable = Observable.just("i", "love", "android", "development");

        Observer<Integer> integerObserver = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("Integer Observation", "Observe Completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Integer Observation", "Observe Error");
            }

            @Override
            public void onNext(Integer s) {
                Log.d("Integer Observation", "Observe item: " + s);
            }
        };

        Observer<String> stringObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("String Observation", "Observe Completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("String Observation", "Observe Error");
            }

            @Override
            public void onNext(String s) {
                Log.d("String Observation", "Observe item: " + s);
            }
        };

        Subscription integerSubscriber = integerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integerObserver);


        Subscription stringSubscriber = stringObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stringObserver);
    }
}
