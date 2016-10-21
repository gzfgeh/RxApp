package com.gzfgeh.nbapp.Present;

public interface Presenter<T> {
    void attachView(T t);

    void detachView();
}