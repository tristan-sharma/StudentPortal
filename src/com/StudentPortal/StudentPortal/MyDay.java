package com.StudentPortal.StudentPortal;
import static com.codename1.ui.CN.*;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.SwipeBackSupport;
import com.codename1.util.LazyValue;

import java.util.Calendar;
import java.util.Date;

import static com.codename1.ui.layouts.BorderLayout.CENTER;

public class MyDay extends Page {

    private String firstLine;
    private String secondLine;

/*
    public Button getButton() {

        Button myDayButton = new Button(getDisplayName(), FontImage.MATERIAL_SCHEDULE, "SideCommand");
        myDayButton.addActionListener((e) -> {

            Form previous = getCurrentForm();
            Form opened = createDisplayForm();

            opened.getToolbar().setBackCommand(" ", ee -> {

                previous.showBack();

            });

            LazyValue<Form> lazyPrevious = (Object... args) -> previous;
            SwipeBackSupport.bindBack(opened, lazyPrevious);
            opened.show();
        });

        return myDayButton;

    }
   */
    public Button getButton() {

        Button myDayButton = new Button(" " + getDisplayName(), FontImage.MATERIAL_SCHEDULE, "SideCommand");
        myDayButton.addActionListener((e) -> {

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

    return myDayButton;

}

    public String getDisplayName() {

        return "My Day";

    }

    public void getDate() {

        String day;

        String[] monthName = {"JANUARY", "FEBRUARY",
                "MARCH", "APRIL", "MAY", "JUNE", "JULY",
                "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER",
                "DECEMBER"};

        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)];

        Date now = new Date();
        cal.setTime(now);

        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek-1) {

            case 1:
                day = "MONDAY";
                break;
            case 2:
                day = "TUESDAY";
                break;
            case 3:
                day = "WEDNESDAY";
                break;
            case 4:
                day = "THURSDAY";
                break;
            case 5:
                day = "FRIDAY";
                break;
            case 6:
                day = "SATURDAY";
                break;
            case 7:
                day = "SUNDAY";
                break;
            default:
                day = "N/A";
                break;

        }

        this.firstLine = day;
        this.secondLine = dayOfMonth + " " + month;

    }


    /*
    TODO: add Day # to right side of Date box container (based on school schedule data)
     */

    public Form createDisplayForm() {

        Form myDay = new Form(getDisplayName(), new BorderLayout());
        Container box = new Container(BoxLayout.y());
        box.setScrollableY(true);


        //date box: date and School Day #
        Container dateBoxFirstLine = new Container();
        dateBoxFirstLine.setUIID("DateBoxContainer");
        Container dateBoxSecondLine = new Container();
        dateBoxSecondLine.setUIID("DateBoxContainer2");
        getDate();
        Label firstLeftLine = new Label(this.firstLine, "DateBoxContainer");
        Label firstRightLine = new Label("       Day 3", "DayNumber");
        Label secondLeftLine = new Label(this.secondLine, "DateBoxContainer2");
        firstLeftLine.setTextPosition(dateBoxFirstLine.LEFT);
        firstRightLine.setTextPosition(dateBoxFirstLine.RIGHT);
        secondLeftLine.setTextPosition(dateBoxSecondLine.LEFT);
        dateBoxFirstLine.add(firstLeftLine);
        dateBoxFirstLine.add(firstRightLine);
        dateBoxSecondLine.add(secondLeftLine);



        //Up Next container (button). Clicking it directs the user to the schedule page.

        MultiButton upNextButton = new MultiButton();
        upNextButton.setUIID("MultiButton");

        upNextButton.setTextLine1("");




        /*
        Label nextClass = new Label("ICS4U", "NextCourseText");
        nextClass.setTextPosition(upNextButton.RIGHT);
        */
        upNextButton.addActionListener((e) -> {

            Form previous = getCurrentForm();
            Form opened = createDisplayForm();

            opened.getToolbar().setBackCommand(" ", ee -> {

                previous.showBack();

            });

            LazyValue<Form> lazyPrevious = (Object... args) -> previous;
            SwipeBackSupport.bindBack(opened, lazyPrevious);
            opened.show();
        });

        box.add(dateBoxFirstLine);
        box.add(dateBoxSecondLine);
        box.add(upNextButton);

        myDay.add(CENTER, box);

        return myDay;


        /*

        Button myDayButton = new Button(getDisplayName(), FontImage.MATERIAL_ACCESSIBLE, "SideCommand");
        myDayButton.addActionListener((e) -> {

            Form previous = getCurrentForm();
            Form opened = createDisplayForm();

            opened.getToolbar().setBackCommand(" ", ee -> {

                previous.showBack();

            });

            LazyValue<Form> lazyPrevious = (Object... args) -> previous;
            SwipeBackSupport.bindBack(opened, lazyPrevious);
            opened.show();
        });

         */


    }


}