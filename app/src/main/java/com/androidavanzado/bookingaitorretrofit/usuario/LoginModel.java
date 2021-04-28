package com.androidavanzado.bookingaitorretrofit.usuario;

import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Usuario;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.EMAIL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.LOGIN;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.PASSWORD;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.USUARIO;

public class LoginModel implements LoginContract.Model {

    private static final String TAG = "LoginModel";

    @Override
    public void getLogin(OnLoginListener onLoginListener, Usuario usuario) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, USUARIO);
        params.put(QUERY, LOGIN);
        params.put(EMAIL, usuario.getEmail());
        params.put(PASSWORD, usuario.getPassword());

        Call<ArrayList<Usuario>> call = apiService.login(params);
        call.enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                ArrayList<Usuario> usuarios = response.body();
                if (usuarios == null || usuarios.size() == 0) return;
                onLoginListener.onResolve(usuarios.get(0));
                Log.d(TAG, "Usuarios cargados");
                Log.d(TAG, usuarios.get(0).toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onLoginListener.onReject(t);
            }
        });
    }
}
