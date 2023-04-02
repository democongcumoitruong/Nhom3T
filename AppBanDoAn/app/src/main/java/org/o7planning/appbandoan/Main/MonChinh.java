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

public class MonChinh extends AppCompatActivity {
    RecyclerView recyclerViewmonchinh;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    org.o7planning.appbandoan.ketnoi.cuahang cuahang;
    private DrawerLayout drawerLayout;
    ArrayList<mathang> mathangs;
    MonChinhad monChinh;
    List<mathang> mathangg;
    int IDSP;
    ImageButton btnhome;
    FrameLayout frameLayout,frameLayout2;
    EditText searchView;
    ImageView timiemic;
    NotificationBadge badge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monchinh);
        cuahang = client.getInstance(maychu.BASE_URL).create(cuahang.class);
        loaimonan();
        Anhxa();
        clikc();
        IDSP = getIntent().getIntExtra("IDSP", 2);
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
                    Intent intent= new Intent(getApplicationContext(),MonChinh.class);
                    startActivity(intent);
                    finish();


                }
                else {
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

                                        monChinh = new MonChinhad(getApplicationContext(), mathangmd.getResult());
                                        recyclerViewmonchinh.setAdapter(monChinh);


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
        recyclerViewmonchinh = findViewById(R.id.recyviewmc);
        btnhome = findViewById(R.id.home);
        searchView =findViewById(R.id.timkiem);
        frameLayout=findViewById(R.id.giohangfr);
        frameLayout2=findViewById(R.id.frame2);
        timiemic=findViewById(R.id.timkiemic);
        mathangs = new ArrayList<>();
        monChinh= new MonChinhad(getApplicationContext(), mathangs);
        recyclerViewmonchinh.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewmonchinh.setLayoutManager(layoutManager);
        recyclerViewmonchinh.setAdapter(monChinh);
        mathangg =new ArrayList<>();

    }

    private void loaimonan() {
        compositeDisposable.add(cuahang.ctmonchinh(IDSP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mathangmd -> {
                            if(mathangmd.isSuccess()) {
                                // Toast.makeText(getApplicationContext(),"thanh cong",Toast.LENGTH_LONG).show();
                                mathangg = mathangmd.getResult();
                                monChinh = new MonChinhad(getApplicationContext(), mathangg);
                                recyclerViewmonchinh.setAdapter(monChinh);

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