package com.openclassrooms.entrevoisins.utils;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import android.util.Log;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

/**
 * Created by Vegeto52-PC on 12/01/2022.
 */
public class ProfilView implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific neighbour";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.activity_profile_view);
        Log.d("CHECK", "perform: button == null " + (button == null));
        button.performClick();
        Log.d("CHECK", "perform: done");
    }
}
