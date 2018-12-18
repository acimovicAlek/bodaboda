package com.bodaboda.bodaboda.classes;

import android.graphics.drawable.Drawable;

public class MenuItem {
    private Drawable menuIcon;

    public MenuItem(Drawable menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Drawable getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(Drawable menuIcon) {
        this.menuIcon = menuIcon;
    }
}
