package vn.danhtran.customuniversalimageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

/**
 * Created by tamhuynh on 11/18/15.
 */
public class FactoryImageLoader {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    public static String TAG = "FactoryImageLoader";
    private double ratio = 1f;

    private static FactoryImageLoader instance;

    public static synchronized FactoryImageLoader getInstance() {
        if (instance == null) {
            synchronized (FactoryImageLoader.class) {
                if (instance == null) {
                    instance = new FactoryImageLoader();
                }
            }
        }

        return instance;
    }

    public static ImageLoader factoryImageLoader() {
        return ImageLoader.getInstance();
    }

    public static ImageLoader factoryImageLoader(ImageLoaderConfiguration config) {
        ImageLoader loader = ImageLoader.getInstance();
        loader.init(config);
        return loader;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public void initImageLoaderNoBackgroundUniversal(Context context) {
        if (context != null) {
            DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .showImageForEmptyUri(null)
                    .showImageOnLoading(null)
                    .showImageOnFail(null)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .displayer(new FadeInBitmapDisplayer(0))
                    .preProcessor(new BitmapProcessor() {
                        @Override
                        public Bitmap process(Bitmap bmp) {
                            return Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() / ratio),
                                    (int) (bmp.getHeight() / ratio), false);
                        }
                    })
                    .build();

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                    context.getApplicationContext())
                    .defaultDisplayImageOptions(defaultOptions)
                    .memoryCache(new WeakMemoryCache())
                    .diskCacheSize(Constants.MAX_DISK_CACHE_SIZE)
                    .memoryCacheSize(Constants.MAX_MEMORY_CACHE_SIZE)
                    .threadPoolSize(CPU_COUNT).build();
            ImageLoader.getInstance().init(config);
        }
    }

    public void displayImage(int drawable, ImageView imageView) {
        Context context = imageView.getContext();
        ImageLoader.getInstance().displayImage(Utils.drawableToUri(context, drawable), imageView);
    }

    public void displayImage(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView);
    }

    public Bitmap getBitmap(String url) {
        return ImageLoader.getInstance().loadImageSync(url);        //url have been token
    }

    public Bitmap getBitmap(int drawable, Context context) {
        return ImageLoader.getInstance().loadImageSync(Utils.drawableToUri(context, drawable));
    }

    public void getBitmap(String url, final LoadCompleteListener listener) {
        ImageLoader.getInstance().loadImage(url, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                listener.onLoadComplete(bitmap);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }

    public interface LoadCompleteListener {
        void onLoadComplete(Bitmap bitmap);
    }
}
