package com.example.fluenceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.View;
import com.example.fluenceapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationFragment extends Fragment {

    public BottomNavigationFragment() {
        super(R.layout.fragment_bottom_navigation);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                Log.d("Navigation", "Navigating to HomeActivity");
                startActivity(new Intent(getContext(), HomeActivity.class));
                return true;
            } else if (itemId == R.id.nav_explore) {
                Log.d("Navigation", "Navigating to ExploreActivity");
                startActivity(new Intent(getContext(), ExploreActivity.class));
                return true;
            }
            return false;
        });

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        }
    }
}