package net.dewep.intranetepitech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

import net.dewep.intranetepitech.api.request.LoginAPI;
import ru.noties.simpleprefs.SimplePref;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.gc.materialdesign.views.ButtonRectangle;
import com.google.gson.JsonObject;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.activity_login_login) EditText login;
    @Bind(R.id.activity_login_password) EditText password;
    @Bind(R.id.activity_login_error) TextView error;
    @Bind(R.id.activity_login_progress) ProgressBarCircularIndeterminate progress;
    @Bind(R.id.activity_login_button) ButtonRectangle button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        final SimplePref prefs = new SimplePref(this, "EpitechAccount");

        progress.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        login.setText(prefs.get("login", ""));
        password.setText(prefs.get("password", ""));
    }

    @OnClick(R.id.activity_login_button)
    void testConnection() {
        if (button.getVisibility() != View.GONE) {
            progress.setVisibility(View.VISIBLE);
            button.setVisibility(View.GONE);
            login.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            error.setVisibility(View.GONE);

            final SimplePref prefs = new SimplePref(this, "EpitechAccount");
            prefs.batch()
                    .set("login", login.getText().toString())
                    .set("password", password.getText().toString())
                    .apply();
            new LoginAPI(login.getText().toString(), password.getText().toString()) {
                @Override
                public void onSuccess(JsonObject result) {
                    error.setText("perfect! " + result.toString());
                    progress.setVisibility(View.GONE);
                    button.setVisibility(View.VISIBLE);
                    login.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                    error.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError(JsonObject e) {
                    error.setText(e.toString());
                    progress.setVisibility(View.GONE);
                    button.setVisibility(View.VISIBLE);
                    login.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                    error.setVisibility(View.VISIBLE);
                }
            };
        }
    }

}
