package com.glide.customglide;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.glide.customglide.blur.BlurBuilder;
import com.glide.customglide.rounder.RoundedCornersTransformation;

import java.io.File;
import java.util.concurrent.ExecutionException;

import vn.danhtran.customglide.R;

/**
 * Created by Envisage Software Pty Ltd. on 17/02/2017.
 */

public class GlideHelper {

    private static GlideHelper instance;
    private RequestOptions requestOptions;
    private RequestOptions requestOptionsCircle;

    public static synchronized GlideHelper getInstance() {
        if (instance == null) {
            synchronized (GlideHelper.class) {
                if (instance == null) {
                    instance = new GlideHelper();
                }
            }
        }

        return instance;
    }

    GlideHelper() {
        requestOptions = new RequestOptions()
                .placeholder(R.drawable.place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.place_holder)
                .timeout(Constants.TIME_OUT);

        requestOptionsCircle = new RequestOptions()
                .placeholder(R.drawable.place_holder_circle)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .optionalCircleCrop()
                .error(R.drawable.place_holder_circle)
                .timeout(Constants.TIME_OUT);
    }

    /*@Override
    public void applyOptions(Context context, GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, Constants.MAX_DISK_CACHE_SIZE)
        );
    }*/

    public void displayImage(int drawable, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(drawable)
                .apply(requestOptions)
                .into(imageView);
    }

    public void displayImage(File file, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(file)
                .apply(requestOptions)
                .thumbnail(0.5f)
                .into(imageView);
    }

    public void displayImage(Uri uri, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(uri)
                .apply(requestOptions)
                .thumbnail(0.5f)
                .into(imageView);
    }

    public void displayImage(String url, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context).load(url)
                .thumbnail(0.5f)
                .apply(requestOptions
                        .placeholder(createCircularProgressDrawable(context)))
                .into(imageView);
    }

    public void displayImage(Bitmap bitmap, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context).load(bitmap)
                .thumbnail(0.5f)
                .apply(requestOptions)
                .into(imageView);
    }

    public void displayImage(String url, ImageView imageView, int width, int height) {
        Context context = imageView.getContext();


        Glide.with(context)
                .load(url)
                .apply(requestOptions
                        .placeholder(createCircularProgressDrawable(context))
                        .override(width, height))
                .into(imageView);
    }

    //optimize load gif
    public void displayImageGif(String url, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .asGif()
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }


    //Display video in local only
    public void displayImageFromVideoLocal(String path, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(Uri.fromFile(new File(path)))
                .into(imageView);
    }

    public void displayImageCircle(String url, final ImageView imageView, int width, int height) {
        final Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .apply(requestOptionsCircle
                        .override(width, height))
                .into(imageView);
    }

    public void displayImageCircle(String url, final ImageView imageView) {
        final Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .apply(requestOptionsCircle)
                .into(imageView);
    }

    public void displayImageRounder(String url, final ImageView imageView, int raidus) {
        final Context context = imageView.getContext();
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(requestOptions
                        .transform(new RoundedCornersTransformation(context, raidus, 0)))
                .into(imageView);

    }

    public void displayImageBlur(String url, final ImageView imageView, int width, int height,
                                 final int radius, final float scale, final float alpha) {
        final Context context = imageView.getContext();
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(requestOptions
                        .override(width, height))
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        BlurBuilder blurBuilder = new BlurBuilder(context);
                        Bitmap blurred = blurBuilder.blurRenderScript(resource, radius, scale); //radius to blur 0 - 25
                        imageView.setImageBitmap(blurred);
                        imageView.setAlpha(alpha);
                    }
                });

    }

    public void loadBitmap(String url, Context context, final GlideListener listener) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(requestOptions)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        listener.failLoadBitmap(e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        listener.loadBitmap(resource);
                        return false;
                    }
                }).submit();

    }

    public Bitmap loadBitmap(String url, Context context) {
        FutureTarget<Bitmap> futureBitmap = Glide.with(context)
                .asBitmap()
                .load(url)
                .submit();
        try {
            return futureBitmap.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CircularProgressDrawable createCircularProgressDrawable(Context context) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(3f);
        circularProgressDrawable.setCenterRadius(12f);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }

    public interface GlideListener {
        void loadBitmap(Bitmap bitmap);

        void failLoadBitmap(GlideException ex);
    }
}
