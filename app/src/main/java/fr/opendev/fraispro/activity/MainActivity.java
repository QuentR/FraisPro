package fr.opendev.fraispro.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import fr.opendev.fraispro.R;
import fr.opendev.fraispro.fragment.FraisListFragment;
import fr.opendev.fraispro.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigation bottomNavigation = (BottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int itemId) {
                FragmentTransaction transaction = null;
                switch (itemId) {
                    case R.id.tab_frais:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, new FraisListFragment());
                        break;
                    case R.id.tab_settings:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, new SettingsFragment());
                        break;
                    case R.id.tab_camera:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, new SettingsFragment());
                        break;
                }
                transaction.commit();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivity(new Intent(MainActivity.this, AddFraisActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
