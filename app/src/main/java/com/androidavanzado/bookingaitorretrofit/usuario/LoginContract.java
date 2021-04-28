package com.androidavanzado.bookingaitorretrofit.usuario;

import com.androidavanzado.bookingaitorretrofit.beans.Usuario;

public interface LoginContract {
    interface Model {
        interface OnLoginListener {
            void onResolve(Usuario usuario);

            void onReject(Throwable throwable);
        }

        void getLogin(OnLoginListener onLoginListener, Usuario usuario);
    }

    interface Presenter {
        void doLoginPresenter(Usuario usuario);
    }

    interface View {
        void onSuccess(Usuario usuario);

        void onFailure(Throwable throwable);
    }
}
