package com.danhtran.androidbaseproject.serviceAPI.extras;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.danhtran.androidbaseproject.MyApplication;
import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.serviceAPI.model.Error;
import com.danhtran.androidbaseproject.serviceAPI.model.ResponseModel;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;
import com.danhtran.androidbaseproject.utils.SnackBarUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import retrofit2.HttpException;
import retrofit2.Response;

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
    public static void showError(final Throwable throwable, final Context context) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            try {
                String errorBody = null;
                if (httpException.response() != null) {
                    Response<?> response = httpException.response();
                    if (response != null && response.errorBody() != null) {
                        errorBody = httpException.response().errorBody().string();
                    }
                }
                if (!TextUtils.isEmpty(errorBody) && JsonParser.isJsonValid(errorBody)) {
                    ResponseModel responseModel = new Gson().fromJson(errorBody, ResponseModel.class);
                    if (responseModel != null && responseModel.getErrors() != null) {
                        List<Error> errors = responseModel.getErrors();
                        Error error = errors.get(0);
                        showNotifyByErrorCode(context, error.getErrorMessage(), error.getErrorCode());
                    } else {
                        showNotify(context, httpException.getMessage());
                    }
                } else if (httpException.response() != null) {
                    showNotifyByErrorCode(context, httpException.getMessage(), httpException.code());
                } else {
                    showUnKnowError(context, httpException.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (throwable instanceof SocketTimeoutException || throwable instanceof UnknownHostException) {
            showNotify(context, R.string.notify_network_error);
        } else {
            showUnKnowErrorWithoutTracking(context);
        }
        Logger.e(throwable, context.getClass().getSimpleName());
    }

    private static void showNotifyByErrorCode(Context context, String message, int errorCode) {
        switch (errorCode) {
            case 401:   //un authentication
                //clear session
//                MyApplication.instance().clearLoginSession();
                //start authentication screen
                                /*Bundle bundle = new Bundle();
                                bundle.putString(SecondaryActivity.KEY_FRAGMENT_TAG, SignInFragment.class.getName());
                                if (context instanceof BaseAppCompatActivity) {
                                    BaseAppCompatActivity activity = (BaseAppCompatActivity) context;
                                    activity.startActivity(SecondaryActivity.class.getName(), bundle);
                                }*/
                break;
            case 400:
            case 500:
                showNotify(context, message);
                break;
            case 404:
                showNotify(context, R.string.notify_could_not_resolve_host);
                break;
            default:
                showUnKnowError(context, message);
                break;
        }
    }

    private static void showNotify(BaseAppCompatActivity activity, String message) {
        SnackBarUtils.showGeneralNotify(activity, message);
    }

    private static void showNotify(@NotNull Context context, String message) {
        if (context instanceof BaseAppCompatActivity) {
            BaseAppCompatActivity activity = (BaseAppCompatActivity) context;
            showNotify(activity, message);
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    private static void showNotify(Context context, int idMessage) {
        if (context != null) {
            showNotify(context, context.getString(idMessage));
        } else {
            Toast.makeText(MyApplication.instance(), MyApplication.instance().getString(idMessage), Toast.LENGTH_LONG).show();
        }
    }

    public static void showApiException(Exception exception, Context context, String notifyMessage) {
        if (exception instanceof ApiException) {
            ApiException apiException = (ApiException) exception;
            switch (apiException.getStatusCode()) {
                case CommonStatusCodes.NETWORK_ERROR:
                    showNotify(context, notifyMessage);
                    break;
            }
        }
    }

    public interface ErrorHandlerListener {
        void success();

        void failure();
    }

    private static void showUnKnowError(Context context, String trackingMsg) {
        showNotify(context, R.string.notify_unknown_error);
    }

    private static void showUnKnowErrorWithoutTracking(Context context) {
        showNotify(context, R.string.notify_unknown_error);
    }
}
