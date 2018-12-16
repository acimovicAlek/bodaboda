package com.bodaboda.bodaboda.classes;

import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuView;

public class AccountSettingsItem {
    private Drawable iconImage;
    private String settingsTitle;

    public AccountSettingsItem(Drawable iconImage, String settingsTitle) {
        this.iconImage = iconImage;
        this.settingsTitle = settingsTitle;
    }

    public Drawable getIconImage() {
        return iconImage;
    }

    public void setIconImage(Drawable iconImage) {
        this.iconImage = iconImage;
    }

    public String getSettingsTitle() {
        return settingsTitle;
    }

    public void setSettingsTitle(String settingsTitle) {
        this.settingsTitle = settingsTitle;
    }

}
