package com.nico_11_riv.intranetepitech;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nico_11_riv.intranetepitech.API.APIErrorHandler;
import com.nico_11_riv.intranetepitech.API.IntraAPI;
import com.nico_11_riv.intranetepitech.API.Requests.RegisterEventRequest;
import com.nico_11_riv.intranetepitech.API.Requests.TokenRequest;
import com.nico_11_riv.intranetepitech.API.Requests.unRegisterEventRequest;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.Infos.GInfos;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;
import com.nico_11_riv.intranetepitech.Database.Planning;
import com.nico_11_riv.intranetepitech.UI.Contents.Schedule_content;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.Objects;

@EFragment(R.layout.listschedule)
public class ListScheduleFragment extends Fragment implements AdapterView.OnItemClickListener {
    @RestService
    IntraAPI API;
    @Bean
    APIErrorHandler ErrorHandler;
    @ViewById
    ListView schedulelistview;
    private ArrayList<Schedule_content> mMarkItemList = null;
    private GUser gUser = new GUser();
    private GInfos gInfos = null;

    @AfterViews
    void init() {
        schedulelistview.setOnItemClickListener(this);
    }

    @AfterInject
    void afterInject() {
        API.setRestErrorHandler(ErrorHandler);
    }

    private boolean isConnected() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }

    @UiThread
    void toastt() {
        Toast.makeText(getActivity().getApplicationContext(), "Connexion Internet Requise", Toast.LENGTH_LONG).show();
    }

    @Background
    void sendToken(Schedule_content toto, CharSequence input) {
        gInfos = new GInfos();
        if (isConnected() == true) {
            Planning.deleteAll(Planning.class, "token = ?", gUser.getToken());
            TokenRequest tt = new TokenRequest(gUser.getToken(), gInfos.getPromo(), toto.getCodemodule(), toto.getCodeinstance(), toto.getCodeacti(), toto.getCodeevent(), input.toString());
            API.validateToken(tt);
        } else {
            toastt();
        }
    }

    @UiThread
    void disPopUp(final Schedule_content item) {
        new MaterialDialog.Builder(getActivity())
                .title("Token")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("Entrez Token", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        sendToken(item, input);
                        Toast.makeText(getActivity().getApplicationContext(), "Token validé", Toast.LENGTH_SHORT).show();
                    }
                })
                .positiveText("Envoyer")
                .negativeText("Annuler")
                .show();
    }

    @Background
    void register(final Schedule_content item) {
        gInfos = new GInfos();
        if (isConnected() == true) {
            RegisterEventRequest rer = new RegisterEventRequest(gUser.getToken(), item.getScolaryear(), item.getCodemodule(), item.getCodeinstance(), item.getCodeacti(), item.getCodeevent());
            API.registerEvent(rer);
        } else {
            toastt();
        }
    }

    @Background
    void unregister(final Schedule_content item) {
        gInfos = new GInfos();
        if (isConnected() == true) {
            unRegisterEventRequest rer = new unRegisterEventRequest(gUser.getToken(), item.getScolaryear(), item.getCodemodule(), item.getCodeinstance(), item.getCodeacti(), item.getCodeevent());
            API.unregisterEvent(rer);
        } else {
            toastt();
        }
    }

    @UiThread
    void disRegister(final Schedule_content item) {
        new MaterialDialog.Builder(getActivity())
                .title("S'inscrire à l'event")
                .positiveText("S'inscrire")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        register(item);
                        Toast.makeText(getActivity().getApplicationContext(), "Inscription réussie", Toast.LENGTH_SHORT).show();
                    }
                })
                .negativeText("Annuler")
                .show();
    }

    @UiThread
    void disunRegister(final Schedule_content item) {
        new MaterialDialog.Builder(getActivity())
                .title("Se désinscrire de l'event")
                .positiveText("Se désinscrire")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        unregister(item);
                        Toast.makeText(getActivity().getApplicationContext(), "Désinscription réussie", Toast.LENGTH_SHORT).show();
                    }
                })
                .negativeText("Annuler")
                .show();
    }

    @Background
    void popUp(AdapterView<?> parent, int position) {
        final Schedule_content item = (Schedule_content) parent.getItemAtPosition(position);
        // Si on est registered ou pas
        if (Objects.equals(item.getAllow_token(), "true")) {
            disPopUp(item);
        }
        else if (Objects.equals(item.getEventreg(), "false")) {
            disRegister(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        popUp(parent, position);
    }
}
