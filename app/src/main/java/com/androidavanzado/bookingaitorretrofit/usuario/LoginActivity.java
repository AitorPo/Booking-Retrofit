package com.androidavanzado.bookingaitorretrofit.usuario;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidavanzado.bookingaitorretrofit.DashboardActivity;
import com.androidavanzado.bookingaitorretrofit.LoginToDashboardLottieActivity;
import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.Splashscreen;
import com.androidavanzado.bookingaitorretrofit.beans.Usuario;
import com.androidavanzado.bookingaitorretrofit.utils.Util;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID_USUARIO;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.USUARIO;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private static final String TAG = "LoginActivity";
    private TextInputLayout textInputLayoutUser;
    private TextInputLayout textInputLayoutPassword;
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private LoginPresenter presenter;
    private Usuario usuario;
    private String etEmailValue, etPasswordValue;

    // Obtejos para gestionar las SharedPreferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Switch aSwitch;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Ocultamos el título de la App
//        getSupportActionBar().hide();

        usuario = new Usuario();
        presenter = new LoginPresenter(this);

        initComponents();
        setCredentialsIfExist();

        btnLogin.setOnClickListener(v -> {
            etEmailValue = etEmail.getText().toString();
            etPasswordValue = etPassword.getText().toString();

            /*if (etEmail.length() == 0 && etPassword.length() == 0) {
                textInputLayoutUser.setError("Error");
                textInputLayoutPassword.setError("Error");
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();
            } else if (etEmail.length() == 0) {
                textInputLayoutUser.setError("Rellena este campo");
                // Comprobamos el formato/patrón del email
            } else if(Patterns.EMAIL_ADDRESS.matcher(etEmailValue).matches()){
                textInputLayoutUser.setError("Formato de email inválido");
            }else if (etPassword.length() == 0) {
                textInputLayoutPassword.setError("Rellena este campo");
            }*/
            /*etEmailValue = etEmail.getText().toString();
            etPasswordValue = etPassword.getText().toString();*/
            usuario.setEmail(etEmailValue);
            usuario.setPassword(etPasswordValue);
            login(usuario);
            presenter.doLoginPresenter(usuario);
        });
    }

    private boolean login(Usuario usuario){
        if (usuario.getEmail().isEmpty()){
            // TODO settear errores en los ET
            etEmail.setError("Email inválido");
            textInputLayoutUser.setError("Email inválido");
        } else if(usuario.getPassword().isEmpty()){
            // TODO settear errores en los ET
            etPassword.setError("Contraseña inválida");
            textInputLayoutPassword.setError("Contraseña inválida");
        }
        return true;
    }

    private void goToDashboard(Usuario usuario){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);

        Toast.makeText(this, "Hola de nuevo " + usuario.getEmail(), Toast.LENGTH_LONG).show();
        intent = new Intent(LoginActivity.this, LoginToDashboardLottieActivity.class);
        // Flag para gestionar que no se pulse "atrás" y se vuelva al login.
        // Funciona igual que finish(): destruye la Activity anterior para no volver a ella pulsando "Atrás"
        intent.putExtra("Email", usuario.getEmail());
        intent.putExtra(ID_USUARIO, usuario.getId());
        Log.d(TAG, "goToDashboard: " + usuario.getId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent, options.toBundle());
    }

    private void saveOnSharedPreferences(String email, String password, int id){
        if (aSwitch.isChecked()){
            // Creamos un editor para AÑADIR información a las SharedPreferences
            //SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Email", email);
            editor.putString("Password", password);
            editor.putInt("id", id);
            editor.apply();
        }
    }

    private void setCredentialsIfExist(){
        String prefsEmail = Util.getUserMailPrefs(sharedPreferences);
        String prefsPassword = Util.getUserPasswordPrefs(sharedPreferences);
        if (!prefsEmail.isEmpty() && !prefsPassword.isEmpty()){
            etEmail.setText(prefsEmail);
            etPassword.setText(prefsPassword);
        }
    }

   public void initComponents() {
        // Instanciamos el objeto de SharedPreferences para LEER las preferencias
        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);

        editor = sharedPreferences.edit();

        aSwitch = findViewById(R.id.switchRemember);
        etEmail = findViewById(R.id.tvEmail);
        etPassword = findViewById(R.id.tvPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textInputLayoutUser = findViewById(R.id.textInputLayoutUser);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
    }

    @Override
    public void onSuccess(Usuario usuario) {
        saveOnSharedPreferences(usuario.getEmail(), usuario.getPassword(), usuario.getId());

        if(login(usuario)){
            goToDashboard(usuario);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e(TAG, throwable.toString());
        Toast.makeText(this, "INCORRECTO", Toast.LENGTH_LONG).show();
        textInputLayoutPassword.setError(throwable.getLocalizedMessage());
    }
}
