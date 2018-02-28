package com.congdanh.androidbaseproject.view.customadpater;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by congdanh on 18/05/2016.
 */
public class ViewPageCustomTabAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private Context context;
    private boolean isShowTitle = true;

    public ViewPageCustomTabAdapter(FragmentManager manager, Context context) {
        super(manager);
        mFragmentList.clear();
        this.context = context;
    }

    public void setListFragment(ArrayList<Fragment> arrayList) {
        this.mFragmentList.clear();
        this.mFragmentList.addAll(arrayList);
    }

    public void setListTitle(ArrayList<String> arrayString) {
        this.mFragmentTitleList.clear();
        this.mFragmentTitleList.addAll(arrayString);
    }

    public void setShowTitle(boolean showTitle) {
        isShowTitle = showTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (isShowTitle)
            return mFragmentTitleList.get(position);
        else return null;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

//    public View getTabView(int position) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_emoji_tab, null);
//        ImageView img = (ImageView) view.findViewById(R.id.tvEmojiItem);
//        FactoryImageLoader.getInstance().displayImage(mFragmentTitleList.get(position), img);
//        view.requestFocus();
//        return view;
//    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position >= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commitNow();
        }
    }

    public List<Fragment> getmFragmentList() {
        return mFragmentList;
    }
}
