package com.bodaboda.bodaboda.classes;

import android.graphics.drawable.Drawable;
import android.widget.Button;

public class MenuItemChild {
    private Button settingsButton;
    private Button loqoutButton;

    public MenuItemChild(Button settingsButton, Button logoutButton) {
        this.settingsButton = settingsButton;
        this.loqoutButton = loqoutButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(Button settingsButton) {
        this.settingsButton = settingsButton;
    }

    public Button getLoqoutButton() {
        return loqoutButton;
    }

    public void setLoqoutButton(Button loqoutButton) {
        this.loqoutButton = loqoutButton;
    }
}
