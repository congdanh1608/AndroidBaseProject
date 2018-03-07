package com.congdanh.androidbaseproject.view.dialogfragment.mappick;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.databinding.MapPickDialogFragmentBinding;
import com.congdanh.androidbaseproject.view.dialogfragment.BaseDialogFragment;

/**
 * Created by congdanh on 3/6/2018.
 */

public class MapPickDialogFragment extends BaseDialogFragment implements MapPickListener {
    private static final String KEY_TYPE = "KEY_TYPE";
    private MapPickDialogFragmentBinding dialogFragmentBinding;
    private MapPickPresenter mapPickPresenter;
    private int type;

    public static MapPickDialogFragment instance(int type) {
        MapPickDialogFragment mapPickDialogFragment = new MapPickDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TYPE, type);
        mapPickDialogFragment.setArguments(bundle);
        return mapPickDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        type = getArguments().getInt(KEY_TYPE);
    }

    @Override
    public int setLayout() {
        return R.layout.map_pick_dialog_fragment;
    }

    @Override
    public View setProgressLayout() {
        return dialogFragmentBinding.progressLayout.progressBar;
    }

    @Override
    public TYPE setTypeScreen() {
        return TYPE.MATCH_PARENT;
    }

    @Override
    public void initUI() {
        dialogFragmentBinding = (MapPickDialogFragmentBinding) binding;
        mapPickPresenter = new MapPickPresenter(this, dialogFragmentBinding.mapView, type);
        dialogFragmentBinding.setPresenter(mapPickPresenter);
        dialogFragmentBinding.executePendingBindings();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onConfigurationChanged() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dialogFragmentBinding.mapView.onCreate(savedInstanceState);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        dialogFragmentBinding.mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        dialogFragmentBinding.mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialogFragmentBinding.mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        dialogFragmentBinding.mapView.onLowMemory();
    }

    @Override
    public void updateTextView(String address) {
        dialogFragmentBinding.tvDestination.setText(address);
    }
}
