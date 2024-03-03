package com.example.imagetotext;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    private TabLayout tabIndicator;
    private Button btnNext;
    private int position = 0;
    private Button btnGetStarted;
    private Animation btnAnim;
    private TextView tvSkip;
    private ConstraintLayout layout;

    final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Integer[] colors = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_intro);


        btnNext = findViewById(R.id.btnNextIntro);
        btnGetStarted = findViewById(R.id.btnGetStartedIntro);
        tabIndicator = findViewById(R.id.tiIntro);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip = findViewById(R.id.tvSkipIntro);
        layout = findViewById(R.id.conLayIntro);




        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Welcome to ImageText!","Scan images for text effortlessly.",R.drawable.intro1));
        mList.add(new ScreenItem("Scan Anytime, Anywhere","Capture text from images on the go.",R.drawable.intro2));
        mList.add(new ScreenItem("Instant Text Recognition","Get accurate text from your images instantly.",R.drawable.intro3));
        mList.add(new ScreenItem("Explore the Possibilities","Unlock the power of image-to-text conversion.",R.drawable.intro4));

        screenPager = findViewById(R.id.vpScreenIntro);
        final IntroViewPagerAdapter introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        colors = new Integer[]{
                getResources().getColor(R.color.colorIntro1),
                getResources().getColor(R.color.colorIntro2),
                getResources().getColor(R.color.colorIntro3),
                getResources().getColor(R.color.colorIntro4)
        };
        screenPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (introViewPagerAdapter.getCount() - 1) && position < (colors.length - 1)) {
                    layout.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                } else {
                    layout.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabIndicator.setupWithViewPager(screenPager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == mList.size() - 1) {
                    loadLastScreen();
                }
            }
        });


        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size() - 1) {

                    loadLastScreen();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLORFromIntro = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(openLORFromIntro);
                savePrefsData();
                finish();
            }
        });


        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });


    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        return pref.getBoolean("isIntroOpened", false);
    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }


    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnim);
    }
}
