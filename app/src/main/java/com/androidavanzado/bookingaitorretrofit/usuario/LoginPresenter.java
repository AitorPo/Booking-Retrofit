package com.androidavanzado.bookingaitorretrofit.usuario;

import com.androidavanzado.bookingaitorretrofit.beans.Usuario;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View view;
    private LoginModel model;

    public LoginPresenter(LoginContract.View view){
        model = new LoginModel();
        this.view = view;
    }

    @Override
    public void doLoginPresenter(Usuario usuario) {
        model.getLogin(new LoginContract.Model.OnLoginListener() {
            @Override
            public void onResolve(Usuario usuario) {
                view.onSuccess(usuario);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        }, usuario);
    }
}
