package vn.danhtran.customglide;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

import vn.danhtran.customglide.blur.BlurBuilder;
import vn.danhtran.customglide.rounder.RoundedBitmapDrawableFormat;
import vn.danhtran.customglide.rounder.RoundedCornersTransformation;

/**
 * Created by congdanh on 17/02/2017.
 */

public class GlideHelper {

    private static GlideHelper instance;
    private RequestOptions requestOptions;

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
                .error(R.drawable.place_holder);
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
                .apply(requestOptions)
                .into(imageView);
    }

    public void displayImage(String url, ImageView imageView, int width, int height) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .apply(requestOptions
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
                .asBitmap()
                .load(url)
                .apply(requestOptions
                        .override(width, height))
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }

    public void displayImageCircle(String url, final ImageView imageView, int width, int height, final int borderSize) {
        final Context context = imageView.getContext();
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(requestOptions
                        .override(width, height))
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawableFormat format =
                                new RoundedBitmapDrawableFormat(context.getResources(), borderSize);
                        imageView.setImageDrawable(format.createRoundedBitmapDrawableWithBorder(resource));
                    }
                });

    }

    public void displayImageCircle(String url, final ImageView imageView) {
        final Context context = imageView.getContext();
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(requestOptions)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

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
                .apply(requestOptions)
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
}
