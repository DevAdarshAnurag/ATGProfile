package com.nitjsr.adarsh.atgprofile;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView profileName, profileLocation, profileAge, profileStatus, profileActive;
    private LinearLayout interestLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        runGradient();
        initializeViews();
    }

    private void initializeViews() {
        profileName = findViewById(R.id.profile_name);
        profileLocation = findViewById(R.id.text_location);
        profileAge = findViewById(R.id.text_age);
        profileActive = findViewById(R.id.text_online);
        profileStatus = findViewById(R.id.profile_status);
        interestLinearLayout = findViewById(R.id.interest_list);

        String interestList[] = {"Travel", "Music", "Photography", "Sports", "Dance", "Animals", "SciFi Movies"};
        Profile profile = new Profile("Susan Klein", "Australia", "21", "Online",
                "Wants to make new friends", interestList);

        //adding interests dynamically
        int interestCharLimit = 0, parentCharLimit = 30, listLen = profile.getInterestList().length;
        for (String interest : profile.getInterestList()) {
            TextView textView = new TextView(MainActivity.this);
            textView.setBackground(getResources().getDrawable(R.drawable.interests_bg));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            textView.setLayoutParams(params);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setPadding(16, 4, 16, 4);
            String intText = interest;
            textView.setText(intText);
            textView.setTextColor(getResources().getColor(R.color.white));
            interestCharLimit += interest.length();
            if (interestCharLimit < parentCharLimit)
                interestLinearLayout.addView(textView);
            else {
                intText = " +" + listLen+" ";
                textView.setText(intText);
                interestLinearLayout.addView(textView);
                break;
            }
            listLen--;
        }

        //setting profile details
        profileName.setText(profile.getName());
        profileLocation.setText(profile.getLocation());
        profileAge.setText(profile.getAge());
        profileActive.setText(profile.getOnline());
        profileStatus.setText(profile.getStatus());
    }

    private void runGradient() {
        //method is used to give gradient color animation to the bottom of profile screen
        RelativeLayout relativeLayout = findViewById(R.id.gradient_rl);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(7500);
        animationDrawable.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //for hiding screen navigation buttons and show them when required by user; providing immersive display
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }
}
