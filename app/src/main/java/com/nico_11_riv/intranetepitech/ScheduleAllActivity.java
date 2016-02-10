package com.nico_11_riv.intranetepitech;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nico_11_riv.intranetepitech.API.APIErrorHandler;
import com.nico_11_riv.intranetepitech.API.intrapi;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.Infos.CircleTransform;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.Infos.Guserinfos;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.Planning.Pplanning;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;
import com.nico_11_riv.intranetepitech.Database.Planning;
import com.nico_11_riv.intranetepitech.Database.User;
import com.nico_11_riv.intranetepitech.UI.Adapters.ScheduleAdpater;
import com.nico_11_riv.intranetepitech.UI.Contents.Schedule_content;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

@EActivity(R.layout.activity_schedule)
public class ScheduleAllActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static int week = 0;

    @RestService
    intrapi api;

    @Bean
    APIErrorHandler ErrorHandler;

    @ViewById
    DrawerLayout drawer_layout;

    @ViewById
    Toolbar toolbar;

    @ViewById
    NavigationView nav_view;

    @ViewById
    ProgressBar planning_progress;

    @ViewById
    TextView week_num;

    private GUser gUser = new GUser();

    private boolean isConnected() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }

    @UiThread
    void sHeader(View header) {
        nav_view.addHeaderView(header);
    }

    @UiThread
    void sImg(Guserinfos user_info) {
        Picasso.with(getApplicationContext()).load(user_info.getPicture()).transform(new CircleTransform()).into((ImageView) findViewById(R.id.user_img));
    }

    void initMenu() {
        Guserinfos user_info = new Guserinfos();
        GUser user = new GUser();
        View header = LayoutInflater.from(this).inflate(R.layout.nav_header, null);
        sHeader(header);
        TextView name = (TextView) header.findViewById(R.id.user_name);
        name.setText(user_info.getTitle() + " (" + user.getLogin() + ")");
        TextView email = (TextView) header.findViewById(R.id.user_email);
        email.setText(user_info.getEmail());
        sImg(user_info);
    }

    @UiThread
    void prog() {
        planning_progress.setVisibility(nav_view.INVISIBLE);
    }

    private ArrayList<Schedule_content> generateData() {
        ArrayList<Schedule_content> items = new ArrayList<Schedule_content>();
        List<Planning> pl = Planning.findWithQuery(Planning.class, "Select * from Planning where token = ? ", gUser.getToken());
        for (int i = 0; i < pl.size(); i++) {
            Planning info = pl.get(i);
            items.add(new Schedule_content(info.getTitlemodule().substring(0, 2), info.getActi_title(), info.getStart().substring(0, info.getStart().length() - 3), info.getEnd().split("\\ ")[1].substring(0, 5), info.getScolaryear(), info.getRegisterevent(), info.getCodemodule(), info.getCodeinstance(), info.getCodeacti(), info.getCodeevent(), info.getAllow_token()));
        }
        prog();
        return items;
    }

    @UiThread
    void dispWeek(String s, String e) {
        week_num.setText("Du " + s + " au " + e);
    }

    @UiThread
    void sAdpater(ListView listView, ScheduleAdpater adapter) {
        listView.setAdapter(adapter);
    }

    void titi(String s, String e) {
        dispWeek(s, e);
        ScheduleAdpater adapter = new ScheduleAdpater(this, generateData());

        ListView listView = (ListView) findViewById(R.id.schedulelistview);
        sAdpater(listView, adapter);
    }

    @Background
    void loadInfos() {
        Calendar c = GregorianCalendar.getInstance(Locale.FRANCE);
        c.add(Calendar.DATE, 7 * week);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

        String startDate = df.format(c.getTime());
        c.add(Calendar.DATE, 6);
        String endDate = df.format(c.getTime());

        if (isConnected() == true) {
            Planning.deleteAll(Planning.class, "token = ?", gUser.getToken());
            api.setCookie("PHPSESSID", gUser.getToken());

            Pplanning pl = new Pplanning(api.getplanning(startDate, endDate));
        }
        initMenu();
        titi(startDate, endDate);
    }

    @AfterViews
    void init() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.setDrawerListener(toggle);
        toggle.syncState();

        nav_view.setNavigationItemSelectedListener(this);
        loadInfos();
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            drawer_layout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, ProfileActivity_.class));
        } else if (id == R.id.nav_marks) {
            drawer_layout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, MarksActivity_.class));
        } else if (id == R.id.nav_modules) {
            drawer_layout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, ModulesActivity_.class));
        } else if (id == R.id.nav_projects) {
            drawer_layout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, ProjectsActivity_.class));
        } else if (id == R.id.nav_schedule) {
            drawer_layout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, ScheduleActivity_.class));
        } else if (id == R.id.nav_schedule_modules) {
            drawer_layout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, ScheduleModulesActivity_.class));
        } else if (id == R.id.nav_schedule_all) {
            drawer_layout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, ScheduleAllActivity_.class));
        } else if (id == R.id.nav_logout) {
            drawer_layout.closeDrawer(GravityCompat.START);
            List<User> users = User.find(User.class, "connected = ?", "true");
            User user = users.get(0);
            user.setConnected("false");
            user.save();
            startActivity(new Intent(this, LoginActivity_.class));
        }
        return true;
    }

    @Click(R.id.prev_week)
    void PrevClicked() {
        week -= 1;
        Planning.deleteAll(Planning.class, "token = ?", gUser.getToken());
        startActivity(new Intent(this, ScheduleAllActivity_.class));
    }

    @Click(R.id.next_week)
    void NextClicked() {
        week += 1;
        Planning.deleteAll(Planning.class, "token = ?", gUser.getToken());
        startActivity(new Intent(this, ScheduleAllActivity_.class));
    }
}
