package org.o7planning.appbandoan.model;
import java.io.Serializable;

public class mathang implements Serializable {
    int mamathang;
    String tenmathang;
    long gia;
    String hinhanhmathang;

    int IDSP;
    String mota;



    public int getMamathang() {
        return mamathang;
    }

    public void setMamathang(int mamathang) {
        this.mamathang = mamathang;
    }

    public String getTenmathang() {
        return tenmathang;
    }

    public void setTenmathang(String tenmathang) {
        this.tenmathang = tenmathang;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getHinhanhmathang() {
        return hinhanhmathang;
    }

    public void setHinhanhmathang(String hinhanhmathang) {
        this.hinhanhmathang = hinhanhmathang;
    }



    public int getIDSP() {
        return IDSP;
    }

    public void setIDSP(int IDSP) {
        this.IDSP = IDSP;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}

