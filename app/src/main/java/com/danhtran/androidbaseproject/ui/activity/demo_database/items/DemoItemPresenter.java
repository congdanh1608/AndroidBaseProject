package com.danhtran.androidbaseproject.ui.activity.demo_database.items;

import com.danhtran.androidbaseproject.database.entity.Address;

/**
 * Created by danhtran on 2/25/2018.
 */

public class DemoItemPresenter {
    private DemoItemData demoItemData;

    public DemoItemPresenter(DemoItemData demoItemData) {
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
