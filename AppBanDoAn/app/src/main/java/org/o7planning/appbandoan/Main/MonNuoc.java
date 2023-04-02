package org.o7planning.appbandoan.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.o7planning.appbandoan.R;
import org.o7planning.appbandoan.adapter.MonChinhad;
import org.o7planning.appbandoan.adapter.MonNuocAd;
import org.o7planning.appbandoan.adapter.MonanvatAd;
import org.o7planning.appbandoan.adapter.mathangadapter;
import org.o7planning.appbandoan.ketnoi.client;
import org.o7planning.appbandoan.ketnoi.cuahang;
import org.o7planning.appbandoan.ketnoi.maychu;
import org.o7planning.appbandoan.model.mathang;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MonNuoc extends AppCompatActivity {
    RecyclerView recyclerViewmonnuoc;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    org.o7planning.appbandoan.ketnoi.cuahang cuahang;
    private DrawerLayout drawerLayout;
    ArrayList<mathang> mathangs;
    MonNuocAd monNuocAd;
    List<mathang> mathangg;
    int IDSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_nuoc);
        cuahang = client.getInstance(maychu.BASE_URL).create(cuahang.class);
        loaimonan();
        Anhxa();
        IDSP= getIntent().getIntExtra("IDSP",3);
    }

    private void Anhxa() {
        drawerLayout = findViewById(R.id.draw);
        recyclerViewmonnuoc = findViewById(R.id.recyviewmn);
        mathangs = new ArrayList<>();
        monNuocAd= new MonNuocAd(getApplicationContext(), mathangs);
        recyclerViewmonnuoc.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewmonnuoc.setLayoutManager(layoutManager);
        recyclerViewmonnuoc.setAdapter(monNuocAd);
        mathangg =new ArrayList<>();
    }

    private void loaimonan() {
        compositeDisposable.add(cuahang.ctmonnuoc(IDSP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mathangmd -> {
                            if(mathangmd.isSuccess()) {
                                // Toast.makeText(getApplicationContext(),"thanh cong",Toast.LENGTH_LONG).show();
                                mathangg = mathangmd.getResult();
                                monNuocAd = new MonNuocAd(getApplicationContext(), mathangg);
                                recyclerViewmonnuoc.setAdapter(monNuocAd);

                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không kết nối được " + throwable.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                ));
    }
}