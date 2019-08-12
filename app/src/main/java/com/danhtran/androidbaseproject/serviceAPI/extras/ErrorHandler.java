package com.danhtran.androidbaseproject.serviceAPI.extras;

import android.text.TextUtils;
import android.view.View;

import com.danhtran.androidbaseproject.serviceAPI.model.Error;
import com.danhtran.androidbaseproject.serviceAPI.model.ResponseModel;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;
import com.danhtran.androidbaseproject.utils.SnackBarUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.HttpException;

/**
 * Created by DanhTran on 5/31/2019.
 */
public class ErrorHandler {
    /**
     * handle error and show by snack bar or toast
     *
     * @param throwable throwable
     * @param activity  BaseAppCompatActivity
     */
    public static void showActivityError(Throwable throwable, BaseAppCompatActivity activity) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            try {
                String errorBody = Objects.requireNonNull(httpException.response().errorBody()).string();
                if (!TextUtils.isEmpty(errorBody)) {
                    Gson gson = new Gson();
                    ResponseModel responseModel = gson.fromJson(errorBody, ResponseModel.class);
                    if (responseModel != null && responseModel.getErrors() != null) {
                        List errors = responseModel.getErrors();
                        Error error = (Error) errors.get(0);
                        switch (error.getErrorCode()) {
                            case 401:   //un authentication
//                                activity.startActivityAsRoot(AuthenActivity.class.getName(), null);
                                break;
                            case 400:
                                showError(activity, (String) responseModel.getData());
                                break;
                            case 500:
                                showError(activity, (String) responseModel.getData());
                                break;
                            default:
                                showError(activity, (String) responseModel.getData());
                                break;
                        }
                    }
                } else {
                    showError(activity, httpException.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JsonParseException jsonParseException) {
                jsonParseException.printStackTrace();
            }
        } else {
            showError(activity, throwable.getMessage());
        }
        Logger.e(throwable, activity.getClass().getSimpleName());
    }

    /**
     * handle error and show by snack bar or toast
     *
     * @param throwable throwable
     * @param activity  BaseAppCompatActivity
     * @param view      Dialog view
     */
    public static void showDialogError(Throwable throwable, BaseAppCompatActivity activity, View view) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            try {
                String errorBody = Objects.requireNonNull(httpException.response().errorBody()).string();
                if (!TextUtils.isEmpty(errorBody)) {
                    Gson gson = new Gson();
                    ResponseModel responseModel = gson.fromJson(errorBody, ResponseModel.class);
                    if (responseModel != null && responseModel.getErrors() != null) {
                        List errors = responseModel.getErrors();
                        Error error = (Error) errors.get(0);
                        switch (error.getErrorCode()) {
                            case 401:   //un authentication
                                //activity.startActivityAsRoot(AuthenActivity.class.getName(), null);
                                break;
                            case 400:
                                showError(view, (String) responseModel.getData());
                                break;
                            case 500:
                                showError(view, (String) responseModel.getData());
                                break;
                            default:
                                showError(view, (String) responseModel.getData());
                                break;
                        }
                    }
                } else {
                    showError(view, httpException.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JsonParseException jsonParseException) {
                jsonParseException.printStackTrace();
            }
        } else {
            showError(view, throwable.getMessage());
        }
        Logger.e(throwable, view.getClass().getSimpleName());
    }

    private static void showError(BaseAppCompatActivity activity, String message) {
        SnackBarUtils.showGeneralError(activity.getRootView(), message);
    }

    private static void showError(View view, String message) {
        SnackBarUtils.showGeneralError(view, message);
    }
}
