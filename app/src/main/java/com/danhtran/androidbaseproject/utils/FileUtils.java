package com.danhtran.androidbaseproject.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;

import com.danhtran.androidbaseproject.extras.listener.ResultListener;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.echodev.resizer.Resizer;

/**
 * Created by SilverWolf on 1/4/2019.
 */
public class FileUtils {
    private static final int IMAGE_QUALITY = 80;
    private static final int IMAGE_SIZE = 1080;

    /**
     * Resize image file.
     * The extension will follow by the source image
     * Filename should be provided without extension
     *
     * @param imageFile context
     * @param generalId generated UUID
     * @param imageFile image file
     */
    @SuppressLint("CheckResult")
    public static void convertImageToSmallSize(Context context, String generalId, File imageFile, final ResultListener<File> listener) {
        String imageNameWithoutExt = generalId + "_small";

        new Resizer(context)
                .setTargetLength(IMAGE_SIZE)
                .setQuality(IMAGE_QUALITY)
                .setOutputFormat(Bitmap.CompressFormat.JPEG)
                .setOutputFilename(imageNameWithoutExt)
                .setOutputDirPath(context.getCacheDir().toString())
                .setSourceImage(imageFile)
                .getResizedFileAsFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) throws Exception {
                        listener.onSuccess(file);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
}
