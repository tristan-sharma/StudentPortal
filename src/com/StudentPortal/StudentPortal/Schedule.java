package com.StudentPortal.StudentPortal;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.SwipeBackSupport;
import com.codename1.util.LazyValue;

import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.layouts.BorderLayout.CENTER;

public class Schedule extends Page {

    public Button getButton() {

        Button scheduleButton = new Button(" " + getDisplayName(), FontImage.MATERIAL_VIEW_WEEK, "SideCommand");
        scheduleButton.addActionListener((e) -> {

            Form previous = getCurrentForm();
            Form opened = createDisplayForm();
            Command back = new Command("Back") {

                public void actionPerformed(ActionEvent evt) {
                    previous.showBack();
                }
            };

            opened.getToolbar().setBackCommand(back);

            LazyValue<Form> lazyPrevious = (Object... args) -> previous;
            SwipeBackSupport.bindBack(opened, lazyPrevious);
            Toolbar myToolbar = opened.getToolbar();
            Button buttonToolbar = myToolbar.findCommandComponent(back);
            FontImage.setMaterialIcon(buttonToolbar, FontImage.MATERIAL_KEYBOARD_BACKSPACE);

            opened.show();
        });

        return scheduleButton;
    }


    public String getDisplayName() {

        return "Schedule";

    }

    public Form createDisplayForm() {

        Form schedule = new Form(getDisplayName(), new BorderLayout());
        Container box = new Container(BoxLayout.y());
        box.setScrollableY(true);

        schedule.add(CENTER, box);

        return schedule;



    }
}
