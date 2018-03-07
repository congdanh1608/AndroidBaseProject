package com.congdanh.androidbaseproject.view.fragment.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.appmodel.MapMaker;
import com.congdanh.androidbaseproject.utils.DeprecatedUtil;
import com.congdanh.androidbaseproject.utils.SizeUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import vn.danhtran.customuniversalimageloader.FactoryImageLoader;

/**
 * Created by danhtran on 30/09/2016.
 */
public class AddMarkerAsyncTask<T extends MapMaker> extends AsyncTask<Void, T, Void> {
    private List<T> points;
    private Context context;
    private GoogleMap googleMap;
    private List<Marker> markers;
    private MapListener mapListener;

    public AddMarkerAsyncTask(GoogleMap googleMap, List<T> points, Context context, MapListener mapListener) {
        this.googleMap = googleMap;
        this.points = points;
        this.context = context;
        this.mapListener = mapListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        markers = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i) != null) {
                T point = points.get(i);
                if (point != null) {
                    Bitmap bitmap = getMarkerBitmapFromView(point);
                    if (bitmap != null) {
                        point.setBitmap(bitmap);
                        publishProgress(point);
                    }
                }
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(T... values) {
        super.onProgressUpdate(values);
        if (!isCancelled())
            createMarker(googleMap, values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mapListener.onReturnMarker(markers);
    }

    private void createMarker(GoogleMap googleMap, T post) {
        if (post.getLatLng() != null) {
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(post.getLatLng().latitude, post.getLatLng().longitude))
                    .anchor(0.5f, 0.5f)
                    .title(post.getTitle())
                    .snippet(post.getDescription())
                    .icon(BitmapDescriptorFactory.fromBitmap(post.getBitmap())));
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//            marker.showInfoWindow();
            markers.add(marker);
        }
    }

    public Bitmap getMarkerBitmapFromView(T point) {
        View customMarkerView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.map_job_avatar, null);
        ImageView markerImageView = customMarkerView.findViewById(R.id.profile_image);
        ImageView avatarBG = customMarkerView.findViewById(R.id.imvAvatar);
        avatarBG.setColorFilter(DeprecatedUtil.getResourceColor(point.getColor(), context));
        int width = SizeUtils.dpToPx(35);      //size in xml
        try {
            //get bitmap
            Bitmap bitmap = null;
            if (point.getUrl() != null) {
                bitmap = FactoryImageLoader.getInstance().getBitmap(point.getUrl());
            } else {
                bitmap = FactoryImageLoader.getInstance().getBitmap(R.mipmap.default_avatar);
            }
            if (bitmap != null)
                markerImageView.setImageBitmap(bitmap);

            //set to view
            customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
            customMarkerView.buildDrawingCache();
            Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Matrix scaleMatrix = new Matrix();

            Canvas canvas = new Canvas(returnedBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
            Drawable drawable = customMarkerView.getBackground();
            if (drawable != null)
                drawable.draw(canvas);
            customMarkerView.draw(canvas);
            return returnedBitmap;
        } catch (Exception ex) {

        }
        return null;
    }

    public interface MapListener {
        void onReturnMarker(List<Marker> markeres);
    }
}
