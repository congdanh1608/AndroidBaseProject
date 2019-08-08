package com.danhtran.androidbaseproject.serviceAPI.extras;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

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
     * @param context   prefer BaseActivity than Context
     */
    public static void showError(Throwable throwable, Context context) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            try {
                String errorBody = Objects.requireNonNull(httpException.response().errorBody()).string();
                if (!TextUtils.isEmpty(errorBody)) {
                    int errorCode = httpException.response().code();
                    Gson gson = new Gson();
                    ResponseModel responseModel = gson.fromJson(errorBody, ResponseModel.class);
                    if (responseModel != null) {
                        List<Error> errors = responseModel.getErrors();
                        Error error = errors.get(0);
                        switch (errorCode) {
                            case 401:   //un authentication
                               /* Bundle bundle = new Bundle();
                                bundle.putString(SecondaryActivity.KEY_FRAGMENT_TAG, SignInFragment.class.getName());
                                if (context instanceof BaseAppCompatActivity) {
                                    BaseAppCompatActivity activity = (BaseAppCompatActivity) context;
                                    activity.startActivityAsRoot(SecondaryActivity.class.getName(), bundle);
                                }*/
                                break;
                            case 400:
                                showError(context, error.getErrorMessage());
                                break;
                            case 500:
                                showError(context, error.getErrorMessage());
                                break;
                            default:
                                showError(context, error.getErrorMessage());
                                break;
                        }
                    }
                } else {
                    showError(context, httpException.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JsonParseException jsonParseException) {
                jsonParseException.printStackTrace();
            }
        } else {
            showError(context, throwable.getMessage());
        }
        Logger.e(throwable, context.getClass().getSimpleName());
    }

    private static void showError(BaseAppCompatActivity activity, String message) {
        SnackBarUtils.showGeneralError(activity, message);
    }

    private static void showError(Context context, String message) {
        if (context instanceof BaseAppCompatActivity) {
            BaseAppCompatActivity activity = (BaseAppCompatActivity) context;
            showError(activity, message);
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
