package org.o7planning.appbandoan.ketnoi;


import org.o7planning.appbandoan.model.LoaiSpModel;
import org.o7planning.appbandoan.model.mathangmd;
import org.o7planning.appbandoan.model.usermd;

import io.reactivex.rxjava3.core.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import org.o7planning.appbandoan.model.usermd;

public interface cuahang {
    @GET("getloaisp.php")
    Observable<LoaiSpModel> getLoaiSp();
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
    @POST("chitietloai.php")
    @FormUrlEncoded
    Observable<mathangmd> chitietloai(
            @Field("IDSP") int IDSP
    );
    @POST("ctmonchinh.php")
    @FormUrlEncoded
    Observable<mathangmd> ctmonchinh(
            @Field("IDSP") int IDSP
    );
    @POST("ctmonnuoc.php")
    @FormUrlEncoded
    Observable<mathangmd> ctmonnuoc(
            @Field("IDSP") int IDSP
    );
    @POST("ctdouong.php")
    @FormUrlEncoded
    Observable<mathangmd> ctdouong(
            @Field("IDSP") int IDSP
    );
    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<mathangmd> seach(
            @Field("seach") String seach
    );

}


