package org.o7planning.appbandoan.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.o7planning.appbandoan.R;
import org.o7planning.appbandoan.adapter.DoUongAd;
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

public class DoUong extends AppCompatActivity {
    RecyclerView recyclerViewDouong;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    org.o7planning.appbandoan.ketnoi.cuahang cuahang;
    private DrawerLayout drawerLayout;
    ArrayList<mathang> mathangs;
    DoUongAd doUongAd;
    List<mathang> mathangg;
    int IDSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_uong);
        cuahang = client.getInstance(maychu.BASE_URL).create(cuahang.class);
        loaimonan();
        Anhxa();
        IDSP= getIntent().getIntExtra("IDSP",11);
    }

    private void Anhxa() {
        drawerLayout = findViewById(R.id.draw);
        recyclerViewDouong = findViewById(R.id.recyviewdu);
        mathangs = new ArrayList<>();
        doUongAd = new DoUongAd(getApplicationContext(), mathangs);
        recyclerViewDouong.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewDouong.setLayoutManager(layoutManager);
        recyclerViewDouong.setAdapter(doUongAd);
        mathangg =new ArrayList<>();
    }

    private void loaimonan() {
        compositeDisposable.add(cuahang.ctdouong(IDSP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mathangmd -> {
                            if(mathangmd.isSuccess()) {
                                // Toast.makeText(getApplicationContext(),"thanh cong",Toast.LENGTH_LONG).show();
                                mathangg = mathangmd.getResult();
                                doUongAd = new DoUongAd(getApplicationContext(), mathangg);
                                recyclerViewDouong.setAdapter(doUongAd);

                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không kết nối được " + throwable.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                ));
    }
}