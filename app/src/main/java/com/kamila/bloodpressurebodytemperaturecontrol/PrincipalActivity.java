package com.kamila.bloodpressurebodytemperaturecontrol;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.kamila.bloodpressurebodytemperaturecontrol.activity.BloodPressureActivity2;
import com.kamila.bloodpressurebodytemperaturecontrol.activity.BodyTemperatureActivity2;
import com.kamila.bloodpressurebodytemperaturecontrol.activity.MainActivity;
import com.kamila.bloodpressurebodytemperaturecontrol.config.ConfigFirebase;
import com.kamila.bloodpressurebodytemperaturecontrol.databinding.ActivityPrincipalBinding;

public class PrincipalActivity extends AppCompatActivity {

    //private MaterialCalendarView calendarView;
    private TextView textGreeting;
    private FirebaseAuth authentication;
    private AppBarConfiguration appBarConfiguration;
        //private ActivityPrincipalBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        //setSupportActionBar(toolbar);


        /*binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);*/

        textGreeting = findViewById(R.id.textGreeting);
        //calendarView = findViewById(R.id.calendarView);
        //configureCalendarView();

        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    //this method display the Menu - LogOut
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //this method will Logout the User when it is selected on Menu, and then the app goes back to the Main Screen
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogOut:
                authentication = ConfigFirebase.getFirebaseAuthentication();
                authentication.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addBodyTemperature(View view) {
        startActivity(new Intent(this, BodyTemperatureActivity2.class));//this line of code starts the next Screen to add Body Temperature


    }

    public void addBloodPressure(View view) {
        startActivity(new Intent(this, BloodPressureActivity2.class));//this line of code starts the next Screen to add Blood Pressure

    }
}







