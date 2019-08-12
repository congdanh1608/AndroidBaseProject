package com.danhtran.androidbaseproject.utils;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.ContextThemeWrapper;

import com.danhtran.androidbaseproject.R;

/**
 * Created by DanhTran on 6/12/2019.
 */
public class DialogUtils {
    public static AlertDialog.Builder showDialog(Context context, int title, int message, int positiveText, int negativeText, final DialogListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom));
        alertDialog.setCancelable(false);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                listener.positiveClick();
            }
        });
        alertDialog.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                listener.negativeClick();
            }
        });
        alertDialog.show();
        return alertDialog;
    }

    public static AlertDialog.Builder showDialog(Context context, String title, String message, int positiveText, int negativeText, final DialogListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom));
        alertDialog.setCancelable(false);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                listener.positiveClick();
            }
        });
        alertDialog.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                listener.negativeClick();
            }
        });
        alertDialog.show();
        return alertDialog;
    }

    public static AlertDialog.Builder showDialog(Context context, String title, String message, String positiveText) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom));
        alertDialog.setCancelable(false);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
        return alertDialog;
    }

    public interface DialogListener {
        void positiveClick();

        void negativeClick();
    }
}
