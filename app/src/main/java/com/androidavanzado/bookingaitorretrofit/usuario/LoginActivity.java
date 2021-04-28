package com.androidavanzado.bookingaitorretrofit.usuario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
    private ArrayList<Usuario> usuarios;
    private String etEmailValue, etPasswordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        presenter = new LoginPresenter(this);
        initComponents();

        btnLogin.setOnClickListener(v -> {

            if (etEmail.length() == 0 && etPassword.length() == 0) {
                textInputLayoutUser.setError("Error");
                textInputLayoutPassword.setError("Error");
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();
            } else if (etEmail.length() == 0) {
                textInputLayoutUser.setError("Rellena este campo");
            } else if (etPassword.length() == 0) {
                textInputLayoutPassword.setError("Rellena este campo");
            }
            etEmailValue = etEmail.getText().toString();
            etPasswordValue = etPassword.getText().toString();
            Usuario usuario = new Usuario();
            usuario.setEmail(etEmailValue);
            usuario.setPassword(etPasswordValue);
            presenter.doLoginPresenter(usuario);
        });
    }


    public void initComponents() {
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
