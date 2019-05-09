package com.StudentPortal.StudentPortal;


import com.codename1.ui.*;
import com.codename1.ui.util.Resources;


public abstract class Page {

    public abstract String getDisplayName();
    public abstract Form createDisplayForm();
    public abstract Button getButton();

}
