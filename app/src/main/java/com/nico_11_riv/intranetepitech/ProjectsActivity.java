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
import android.widget.TextView;

import com.nico_11_riv.intranetepitech.API.APIErrorHandler;
import com.nico_11_riv.intranetepitech.API.herokuapi;
import com.nico_11_riv.intranetepitech.API.Requests.InfosRequest;
import com.nico_11_riv.intranetepitech.Database.Current_Projets;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.Infos.CircleTransform;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.Infos.GInfos;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.Infos.SInfos;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;
import com.nico_11_riv.intranetepitech.Database.Infos;
import com.nico_11_riv.intranetepitech.Database.User;
import com.nico_11_riv.intranetepitech.UI.Adapters.ProjectsAdapter;
import com.nico_11_riv.intranetepitech.UI.Contents.Projects_content;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_projects)
public class ProjectsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @RestService
    herokuapi API;

    @Bean
    APIErrorHandler ErrorHandler;

    @ViewById
    DrawerLayout drawer_layout;

    @ViewById
    Toolbar toolbar;

    @ViewById
    NavigationView nav_view;

    private GUser gUser = new GUser();

    @AfterInject
    void afterInject() {
        API.setRestErrorHandler(ErrorHandler);
    }

    private boolean isConnected() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }

    @UiThread
            void sAdapter(ListView listView, ProjectsAdapter adapter) {
        listView.setAdapter(adapter);
    }

    void display_cur_projs() {
        ProjectsAdapter adapter = new ProjectsAdapter(this, generateData());

        ListView listView = (ListView) findViewById(R.id.projslistview);
        sAdapter(listView, adapter);
    }

    private ArrayList<Projects_content> generateData() {
        ArrayList<Projects_content> items = new ArrayList<Projects_content>();
        List<Current_Projets> project = Select.from(Current_Projets.class).where(Condition.prop("token").eq(gUser.getToken())).list();
        for (int i = 0; i < project.size(); i++) {
            Current_Projets info = project.get(i);
            items.add(new Projects_content(info.getTitle(), info.getTimeline_start(), info.getTimeline_end()));
        }
        return items;
    }

    @UiThread
    void disHeader(View header) {
        nav_view.addHeaderView(header);
    }

    @UiThread
    void dispImg(GInfos user_info) {
        Picasso.with(getApplicationContext()).load(user_info.getPicture()).transform(new CircleTransform()).into((ImageView) findViewById(R.id.user_img));
    }

    void initMenu() {
        GInfos user_info = new GInfos();
        GUser user = new GUser();
        View header = LayoutInflater.from(this).inflate(R.layout.nav_header, null);
        disHeader(header);
        TextView name = (TextView) header.findViewById(R.id.user_name);
        name.setText(user_info.getTitle() + " (" + user.getLogin() + ")");
        TextView email = (TextView) header.findViewById(R.id.user_email);
        email.setText(user_info.getEmail());
        dispImg(user_info);
        display_cur_projs();
    }

    @Background
    void loadInfos() {
        if (isConnected() == true) {
            InfosRequest ir = new InfosRequest(gUser.getToken());
            Infos.deleteAll(Infos.class, "token = ?", gUser.getToken());
            Current_Projets.deleteAll(Current_Projets.class, "token = ?", gUser.getToken());
            SInfos sinfos = new SInfos(API.getInfos(ir), API.getTrombi(gUser.getToken(), gUser.getLogin()));
        }
        GInfos gInfos = new GInfos();
        initMenu();
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
}
