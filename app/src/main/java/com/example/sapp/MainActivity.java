package com.example.sapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
    private final int ID_HOME=1;
    private final int ID_NOTIFICATION=2;
    private final int ID_CAMERA=3;
    private final int ID_MENU=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.notifications));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_CAMERA, R.drawable.photo_camera));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_MENU, R.drawable.menu));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                // on click
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //pass
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment;
                switch (item.getId()){
                    case ID_HOME:
                        fragment = new CameraFragment();
                        break;
                    case ID_MENU:
                        fragment = new Fragment();
                        break;
                    case ID_CAMERA:
                        fragment = new CameraFragment();
                        break;
                    case ID_NOTIFICATION:
                        fragment = new Fragment();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getId());
                }
                loadFragment(fragment);
            }
        });

        bottomNavigation.show(ID_HOME, true);
        bottomNavigation.setCount(ID_NOTIFICATION, "2");

    }

    public void loadFragment(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }
}