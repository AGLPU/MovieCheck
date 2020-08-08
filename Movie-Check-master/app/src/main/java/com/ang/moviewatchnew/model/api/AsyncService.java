package com.ang.moviewatchnew.model.api;

import com.ang.moviewatchnew.model.api.asynctask.ApiResultListener;

public interface AsyncService {

    ApiResultListener getApiResultListener();

    void setApiResultListener(ApiResultListener listener);

    void cancelAllServices();
}
