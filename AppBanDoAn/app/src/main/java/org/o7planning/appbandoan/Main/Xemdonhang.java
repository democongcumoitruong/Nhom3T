package org.o7planning.appbandoan.Main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.o7planning.appbandoan.R;
import org.o7planning.appbandoan.adapter.Donhangad;
import org.o7planning.appbandoan.ketnoi.client;

import org.o7planning.appbandoan.ketnoi.cuahang;
import org.o7planning.appbandoan.ketnoi.maychu;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Xemdonhang extends AppCompatActivity {
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    org.o7planning.appbandoan.ketnoi.cuahang cuahang;
    RecyclerView recyclerViewdonhang;
    Donhangad donhangad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemdonhang);
        cuahang = client.getInstance(maychu.BASE_URL).create(cuahang.class);
        recyclerViewdonhang =findViewById(R.id.recyviewdonhang);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewdonhang.setLayoutManager(layoutManager);
        themvao();
    }
    private void themvao() {
        compositeDisposable.add(cuahang.xemdonhang(maychu.userdangnhap.getIDuser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        donhangmd -> {
                            Donhangad donhangad =new Donhangad(getApplicationContext(),donhangmd.getResult());
                            recyclerViewdonhang.setAdapter(donhangad);
                        },throwable -> {


                        }
                ));
    }



    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}