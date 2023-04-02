package org.o7planning.appbandoan.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.o7planning.appbandoan.R;
import org.o7planning.appbandoan.adapter.MonChinhad;
import org.o7planning.appbandoan.adapter.MonanvatAd;
import org.o7planning.appbandoan.adapter.mathangadapter;
import org.o7planning.appbandoan.ketnoi.client;
import org.o7planning.appbandoan.ketnoi.cuahang;
import org.o7planning.appbandoan.ketnoi.maychu;
import org.o7planning.appbandoan.model.mathang;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Monanvat extends AppCompatActivity {
    RecyclerView recyclerViewAnVat;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    org.o7planning.appbandoan.ketnoi.cuahang cuahang;
    private DrawerLayout drawerLayout;
    ArrayList<mathang> mathangs;
    MonanvatAd monanvatAd;
    List<mathang> mathangg;
    int IDSP;
    TextView monanvat;
    ImageButton btnhome;
    FrameLayout frameLayout,frameLayout2;
    EditText searchView;
    ImageView timiemic;
    NotificationBadge badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monanvat);
        cuahang = client.getInstance(maychu.BASE_URL).create(cuahang.class);
        loaimonan();
        Anhxa();
        IDSP= getIntent().getIntExtra("IDSP",1);
        clikc();
        badge = findViewById(R.id.solg);
        if (maychu.dshang == null) {
            maychu.dshang = new ArrayList<>();
        } else {
            int solgtthem = 0;
            for (int i = 0; i < maychu.dshang.size(); i++) {
                solgtthem = solgtthem + maychu.dshang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(solgtthem));
        }
    }

    private void clikc() {
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Trangchu.class);
                startActivity(intent);
                finish();
            }
        });

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Giohang.class);
                startActivity(intent);
            }
        });
        timiemic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchView.setVisibility(View.VISIBLE);

            }
        });
        frameLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setVisibility(View.VISIBLE);


            }
        });
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(searchView.getText().length()==0){
                    Intent intent= new Intent(getApplicationContext(),Monanvat.class);
                    startActivity(intent);
                    finish();


                }
                else {
                    monanvat.setVisibility(View.INVISIBLE);
                    timkiem();
                }

            }

            private void timkiem() {
                String timkiem = searchView.getText().toString().trim();
                compositeDisposable.add(cuahang.seach(timkiem)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                mathangmd -> {
                                    if (mathangmd.isSuccess()) {

                                        monanvatAd= new MonanvatAd(getApplicationContext(), mathangmd.getResult());
                                        recyclerViewAnVat.setAdapter(monanvatAd);


                                    }


                                }, throwable -> {
                                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                        ));
            }
        });
    }

    private void Anhxa() {
        drawerLayout = findViewById(R.id.draw);
        btnhome = findViewById(R.id.home);
        monanvat = findViewById(R.id.moanvat);
        searchView =findViewById(R.id.timkiem);
        frameLayout=findViewById(R.id.giohangfr);
        frameLayout2 = findViewById(R.id.frame2);
        timiemic=findViewById(R.id.timkiemic);
        recyclerViewAnVat = findViewById(R.id.recyviewdt);
        mathangs = new ArrayList<>();
        monanvatAd = new MonanvatAd(getApplicationContext(), mathangs);
        recyclerViewAnVat.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewAnVat.setLayoutManager(layoutManager);
        recyclerViewAnVat.setAdapter(monanvatAd);
        mathangg =new ArrayList<>();
    }

    private void loaimonan() {
        compositeDisposable.add(cuahang.chitietloai(IDSP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mathangmd -> {
                            if(mathangmd.isSuccess()) {
                                // Toast.makeText(getApplicationContext(),"thanh cong",Toast.LENGTH_LONG).show();
                                mathangg = mathangmd.getResult();
                                monanvatAd = new MonanvatAd(getApplicationContext(), mathangg);
                                recyclerViewAnVat.setAdapter(monanvatAd);

                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không kết nối được " + throwable.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                ));
    }
    @Override
    protected void onResume() {
        super.onResume();
        int solgtthem = 0;
        for(int i =0; i<maychu.dshang.size();i++) {
            solgtthem = solgtthem + maychu.dshang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(solgtthem));
    }
}