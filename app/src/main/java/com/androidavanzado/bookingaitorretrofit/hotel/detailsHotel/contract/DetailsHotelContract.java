package com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

public interface DetailsHotelContract {
    interface Model {
        interface OnDetailsHotelListener {
            void onResolve(Hotel hotel);

            void onReject(Throwable throwable);
        }

        void getHotelLS(OnDetailsHotelListener onDetailsHotelListener, int idHotel);
    }

    interface Presenter {
        void getDetailsHotel(int idHotel);
    }

    interface View {
        void onSuccess(Hotel hotel);

        void onFailure(Throwable throwable);
    }

}
