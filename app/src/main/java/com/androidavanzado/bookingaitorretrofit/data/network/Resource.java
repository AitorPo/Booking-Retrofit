package com.androidavanzado.bookingaitorretrofit.data.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

import static com.androidavanzado.bookingaitorretrofit.data.network.Status.ERROR;
import static com.androidavanzado.bookingaitorretrofit.data.network.Status.LOADING;
import static com.androidavanzado.bookingaitorretrofit.data.network.Status.SUCCESS;


/**
 * A generic class that holds a value with its loading status.
 *
 * @param <T>
 */
public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }
}
