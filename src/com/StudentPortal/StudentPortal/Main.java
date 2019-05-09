package com.StudentPortal.StudentPortal;

import static com.codename1.ui.CN.*;
import com.codename1.io.Log;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class Main {

    private Form current;
    private Resources theme;

    public void init(Object context) {

        updateNetworkThreadCount(2);
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            //remove this later (shows an error message to the user)
            Dialog.show("Connection Error", "There was a networking error in the connection to "
                    + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }

        MyDay myDayForm = new MyDay();
        Schedule scheduleForm = new Schedule();
        Marks marksForm = new Marks();
        LunchMenu lunchMenuForm = new LunchMenu();
        Info infoForm = new Info();

        Form a = new Form("Portal", new BorderLayout());
        Container box = new Container(BoxLayout.y());
        box.setScrollableY(true);



        box.add(myDayForm.getButton());
        box.add(scheduleForm.getButton());
        box.add(marksForm.getButton());
        box.add(lunchMenuForm.getButton());
        box.add(infoForm.getButton());
        a.add(CENTER, box);
        a.show();

    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }

    }

    public void destroy() {
    }

}