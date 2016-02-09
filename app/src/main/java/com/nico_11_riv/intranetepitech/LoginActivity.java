package com.nico_11_riv.intranetepitech;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nico_11_riv.intranetepitech.API.APIErrorHandler;
import com.nico_11_riv.intranetepitech.API.IntraAPI;
import com.nico_11_riv.intranetepitech.API.Requests.LoginRequest;
import com.nico_11_riv.intranetepitech.API.api;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.SUser;
import com.nico_11_riv.intranetepitech.Database.User;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    @RestService
    IntraAPI API;

    @RestService
    api restapi;

    @Bean
    APIErrorHandler ErrorHandler;

    @ViewById
    AutoCompleteTextView vlogin;

    @ViewById
    EditText vpasswd;

    @ViewById
    Button login_button;

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

    @AfterViews
    void init() {
        ErrorHandler = new APIErrorHandler(getApplicationContext());
        if (User.count(User.class, "connected = ?", new String[]{"true"}) == 1) {
            startActivity(new Intent(this, ProfileActivity_.class));
        }
    }

    @UiThread
    void connect() {
        vlogin.setText(restapi.getCookie("PHPSESSID"));
    }

    boolean connectNetwork(String login, String passwd) {
        LoginRequest lr = new LoginRequest(login, passwd);
        restapi.setCookie("PHPSESSID", "toto");
        restapi.gettoken(lr);
        connect();
        /*SUser suser = new SUser(login, passwd, restapi.gettoken(login, passwd));
        if (suser.isError() == true) {
            return true;
        }*/
        return false;
    }

    @UiThread
    void settingFieldError() {
        vlogin.setError(null);
        vpasswd.setError(null);
        login_button.setText("Connexion");
    }

    @UiThread
    void setLoginRequired() {
        vlogin.setError(getString(R.string.error_field_required));
    }

    @UiThread
    void setPasswdRequired() {
        vpasswd.setError(getString(R.string.error_field_required));
    }

    @UiThread
    void setView(View focusView) {
        focusView.requestFocus();
    }

    @UiThread
    void internetError() {
        Toast.makeText(getApplicationContext(), "Connection Internet Requise", Toast.LENGTH_LONG).show();
    }

    @UiThread
    void wrongPasswd() {
        Toast.makeText(getApplicationContext(), "Mauvais Mot de Passe", Toast.LENGTH_LONG).show();
    }

    @Background
    void attemptLogin() {
        String login = vlogin.getText().toString().replaceAll("\\s","");
        String passwd = vpasswd.getText().toString();

        View focusView = null;

        boolean cancel = false;

        settingFieldError();

        if (TextUtils.isEmpty(passwd)) {
            setPasswdRequired();
            focusView = vpasswd;
            cancel = true;
        }
        if (TextUtils.isEmpty(login)) {
            setLoginRequired();
            focusView = vlogin;
            cancel = true;
        }
        if (cancel)
            setView(focusView);
        else {
            if (User.count(User.class, "login = ? and passwd = ?", new String[]{login, passwd}) == 1) {
                SUser sUser = new SUser(login);
                startActivity(new Intent(this, ProfileActivity_.class));
            } else if (User.count(User.class, "login = ?", new String[]{login}) == 1) {
                wrongPasswd();
            } else if (isConnected() == false) {
                internetError();
            } else {
                if (connectNetwork(login, passwd) == false) {
                    //startActivity(new Intent(this, ProfileActivity_.class));
                }
            }
        }
    }

    @Click(R.id.login_button)
    void SignInClicked() {
        attemptLogin();
    }
}
