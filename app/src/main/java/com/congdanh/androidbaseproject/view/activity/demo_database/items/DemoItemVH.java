package com.congdanh.androidbaseproject.view.activity.demo_database.items;

import com.congdanh.androidbaseproject.database.entity.Address;

/**
 * Created by congdanh on 2/25/2018.
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

    public String getAddress() {
        if (demoItemData.getUser() != null && demoItemData.getUser().getAddress() != null) {
            Address address = demoItemData.getUser().getAddress();
            return address.getHouseNumber() + ", " + address.getStreet() + ", " + address.getCity() + ", " + address.getCountry();
        }
        return "";
    }
}
