package org.o7planning.appbandoan.ketnoi;


import org.o7planning.appbandoan.model.mathangmd;
import org.o7planning.appbandoan.model.usermd;

import io.reactivex.rxjava3.core.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import org.o7planning.appbandoan.model.usermd;

public interface cuahang {
    @POST("user.php")
    @FormUrlEncoded
    Observable<usermd> user(
            @Field("SDT") String SDT,
            @Field("matkhau") String matkhau,
            @Field("tenuser") String tenuser,
            @Field("gmail") String gmail
    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<usermd> dangnhap(
            @Field("SDT") String SDT,
            @Field("matkhau") String matkhau

    );
    @GET("themsanpham.php")
    Observable<mathangmd> getThemsanpham();
}


