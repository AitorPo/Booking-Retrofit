package com.androidavanzado.bookingaitorretrofit.usuario;

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

import com.androidavanzado.bookingaitorretrofit.LoginToDashboardLottieActivity;
import com.androidavanzado.bookingaitorretrofit.R;
import com.androidavanzado.bookingaitorretrofit.beans.Usuario;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

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
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = new Usuario();
        presenter = new LoginPresenter(this);

        initComponents();

        btnLogin.setOnClickListener(v -> {
            // if(login(etEmailValue, etPasswordValue)){
            //      goToDashboard();
            //      saveOnPreferences(etEmailValue, etPasswordValue();
            // }

            if (etEmail.length() == 0 && etPassword.length() == 0) {
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
            }
            etEmailValue = etEmail.getText().toString();
            etPasswordValue = etPassword.getText().toString();
            usuario.setEmail(etEmailValue);
            usuario.setPassword(etPasswordValue);
            presenter.doLoginPresenter(usuario);
        });
    }

    private boolean login(String email, String password){
        if (!validateEmail(email)){
            // TODO settear errores en los ET
            return false;
        } else if(!validatePassword(password)){
            // TODO settear errores en los ET
            return false;
        } else {
            return true;
        }
    }

    private boolean validateEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(etEmailValue).matches();
    }

    private boolean validatePassword(String password){
        return password.length() >= 3;
    }

    private void goToDashboard(){
        Toast.makeText(this, "Hola de nuevo " + usuario.getEmail(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginToDashboardLottieActivity.class);
        //Flag para gestionar que no se pulse "atrás" y se vuelva al login.
        // TODO comprobrobar si funciona igual que finish()
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void saveOnSharedPreferences(String email, String password){
        if (aSwitch.isChecked()){
            // Creamos un editor para AÑADIR información a las SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Email", email);
            editor.putString("Password", password);
            editor.apply();
        }
    }

    public void initComponents() {
        // Instanciamos el objeto de SharedPreferences para LEER las preferencias
        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);

        etEmail = findViewById(R.id.tvEmail);
        etPassword = findViewById(R.id.tvPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textInputLayoutUser = findViewById(R.id.textInputLayoutUser);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
    }

    @Override
    public void onSuccess(Usuario usuario) {
        Toast.makeText(this, "Hola de nuevo " + usuario.getEmail(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginToDashboardLottieActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e(TAG, throwable.toString());
        Toast.makeText(this, "INCORRECTO", Toast.LENGTH_LONG).show();
        textInputLayoutPassword.setError(throwable.getLocalizedMessage());
    }
}
