package com.congdanh.androidbaseproject.view.activity.demo.items;

/**
 * Created by congd on 2/25/2018.
 */

public class DemoItemVH {
    private DemoItemData demoItemData;

    public DemoItemVH(DemoItemData demoItemData) {
        this.demoItemData = demoItemData;
    }

    public DemoItemData getDemoItemData() {
        return demoItemData;
    }

    public void setDemoItemData(DemoItemData demoItemData) {
        this.demoItemData = demoItemData;
    }
}
