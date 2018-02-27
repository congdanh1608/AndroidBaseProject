package vn.danhtran.customglide;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

import vn.danhtran.customglide.customsize.CustomImageSizeModel;
import vn.danhtran.customglide.customsize.CustomImageSizeModelFutureStudio;
import vn.danhtran.customglide.customsize.CustomImageSizeUrlLoader;
import vn.danhtran.customglide.rounder.RoundedBitmapDrawableFormat;
import vn.danhtran.customglide.rounder.RoundedCornersTransformation;

/**
 * Created by congdanh on 17/02/2017.
 */
public class GlideImageLoader implements GlideModule {

    private static GlideImageLoader instance;

    public static synchronized GlideImageLoader getInstance() {
        if (instance == null) {
            synchronized (GlideImageLoader.class) {
                if (instance == null) {
                    instance = new GlideImageLoader();
                }
            }
        }

        return instance;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, Constants.MAX_DISK_CACHE_SIZE)
        );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        //use for https
//        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

    public void displayImage(int drawable, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(drawable)
                .placeholder(drawable)
                .crossFade()
                .into(imageView);
    }

    public void displayImage(File file, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(file)
                .placeholder(R.drawable.place_holder)
                .crossFade()
                .thumbnail(0.5f)
                .into(imageView);
    }

    public void displayImage(Uri uri, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(uri)
                .placeholder(R.drawable.place_holder)
                .crossFade()
                .thumbnail(0.5f)
                .into(imageView);
    }

    public void displayImage(String url, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context).load(url)
                .thumbnail(0.5f)
                .placeholder(R.drawable.place_holder)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public void displayImage(String url, ImageView imageView, int width, int height) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .override(width, height)
                .into(imageView);
    }

    //auto get size from server -- ?w=?&h=?
    public void displayImageAutoCustomSize(String baseImageUrl, ImageView imageView) {
        Context context = imageView.getContext();
        CustomImageSizeModel customImageRequest = new CustomImageSizeModelFutureStudio(baseImageUrl);
        Glide.with(context)
                .using(new CustomImageSizeUrlLoader(context))
                .load(customImageRequest)
                .placeholder(R.drawable.place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(imageView);
    }

    //optimize load gif
    public void displayImageGif(String url, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .asGif()
                .error(R.drawable.place_holder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
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
                .asBitmap()
                .override(width, height)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
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
                .load(url)
                .asBitmap()
                .override(width, height)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
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
                .load(url)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
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
                .load(url)
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCornersTransformation(context, raidus, 0))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.place_holder)
                .into(imageView);

    }
}
