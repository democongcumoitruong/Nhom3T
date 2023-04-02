package org.o7planning.appbandoan.Main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import org.o7planning.appbandoan.R;
import org.o7planning.appbandoan.adapter.LoaiSpAdapter;
import org.o7planning.appbandoan.adapter.mathangadapter;
import org.o7planning.appbandoan.ketnoi.client;
import org.o7planning.appbandoan.ketnoi.cuahang;
import org.o7planning.appbandoan.ketnoi.maychu;
import org.o7planning.appbandoan.model.LoaiSp;
import org.o7planning.appbandoan.model.mathang;
import org.o7planning.appbandoan.model.user;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Trangchu extends AppCompatActivity {

    NavigationView navigationView;
    ListView listView,listViewloai;
    Toolbar toolbar;
    private RecyclerView recyclerView,recyclerView5;
    private DrawerLayout drawerLayout;

    CompositeDisposable compositeDisposable= new CompositeDisposable();
    cuahang cuahang;

    ArrayList<mathang> mathangs;
    mathangadapter mathangadapterr;
    List<mathang> mathangg;

    NotificationBadge badge;
    TextView textViewtenuser;
    ViewFlipper viewFlipper;

    FrameLayout frameLayout,frameLayout2,frameLayout3,frameLayout4,frameLayout5;
    EditText searchView;
    ImageView timiemic;
    ImageButton btnhome;

    LoaiSpAdapter loaiSpAdapter;
    ArrayList<LoaiSp>mangLoaiSp;
    List<LoaiSp> loaispp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        cuahang = client.getInstance(maychu.BASE_URL).create(cuahang.class);
        Paper.init(this);
        if(Paper.book().read("user")!=null){
            user userr = Paper.book().read("user");
            maychu.userdangnhap=userr;
        }
        Anhxa();
        RunviewFlipper();
        getLoaiSanPham();
        dsmathang();
        acctionToolBar();
        menuu();


    }




    private void RunviewFlipper() {
        List <String> dsmonan = new ArrayList<>();
        dsmonan.add("https://img.pikbest.com/backgrounds/20210619/stylish-cool-food-burger-restaurant-web-banner_6022760.jpg!w700wp");
        dsmonan.add("https://img.pikbest.com/backgrounds/20210619/retro-simple-chinese-meal-noodles-web-banner_6022757.jpg!w700wp");
        dsmonan.add("https://img.pikbest.com/backgrounds/20210619/fashion-color-gradient-hot-dog-food-web-banner_6022753.jpg!w700wp");
        for(int i =0;i< dsmonan.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(dsmonan.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setInAnimation(out);
    }

    private void menuu() {
        frameLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Trangchu.class);
                startActivity(intent);
            }
        });
        frameLayout4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Xemdonhang.class);
                startActivity(intent);
            }
        }));
        frameLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().delete("user");
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Trangchu.class);
                startActivity(intent);
                finish();
            }
        });

    }




    private void acctionToolBar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void getLoaiSanPham() {


        compositeDisposable.add(cuahang.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if(loaiSpModel.isSuccess()) {
                                // Toast.makeText(getApplicationContext(),"thanh cong",Toast.LENGTH_LONG).show();
                                loaispp = loaiSpModel.getResult();
                                loaiSpAdapter = new LoaiSpAdapter(getApplicationContext(), loaispp);
                                recyclerView5.setAdapter(loaiSpAdapter);

                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không kết nối được " + throwable.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                ));

    }

    private void dsmathang() {
        compositeDisposable.add(cuahang.getThemsanpham()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mathangmd -> {
                            if(mathangmd.isSuccess()) {
                                // Toast.makeText(getApplicationContext(),"thanh cong",Toast.LENGTH_LONG).show();
                                mathangg = mathangmd.getResult();
                                mathangadapterr = new mathangadapter(getApplicationContext(), mathangg);
                                recyclerView.setAdapter(mathangadapterr);

                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không kết nối được " + throwable.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                ));

    }




    private void Anhxa() {
        toolbar=findViewById(R.id.tbtc);
        navigationView = findViewById(R.id.naviga);
        listView = findViewById(R.id.listview);
        //textViewtenuser=findViewById(R.id.tenuser);
        viewFlipper = findViewById(R.id.viewFlipper);
        // textViewtenuser.setText(maychu.userdangnhap.getTenuser());
        btnhome = findViewById(R.id.home);
        recyclerView = findViewById(R.id.recyview);
        recyclerView5 = findViewById(R.id.recyviewloai);
        drawerLayout = findViewById(R.id.iddrawblelayout);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        mangLoaiSp = new ArrayList<>();
        loaiSpAdapter = new LoaiSpAdapter(getApplicationContext(),mangLoaiSp);
        recyclerView5.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerr = new GridLayoutManager(this,2);
        recyclerView5.setLayoutManager(linearLayoutManager);

        mathangs = new ArrayList<>();
        mathangadapterr = new mathangadapter(getApplicationContext(), mathangs);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        frameLayout=findViewById(R.id.giohangfr);
        frameLayout2=findViewById(R.id.frane2);
        frameLayout3=findViewById(R.id.frmmenu);
        frameLayout4=findViewById(R.id.frmmen4);
        frameLayout5=findViewById(R.id.frmmen5);
        recyclerView.setAdapter(mathangadapterr);
        recyclerView5.setAdapter(loaiSpAdapter);
        mathangg =new ArrayList<>();
        loaispp =new ArrayList<>();
        badge = findViewById(R.id.solg);
        if(maychu.dshang==null){
            maychu.dshang= new ArrayList<>();
        }else{
            int solgtthem = 0;
            for(int i =0; i<maychu.dshang.size();i++) {
                solgtthem = solgtthem + maychu.dshang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(solgtthem));
        }
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Giohang.class);
                startActivity(intent);
            }
        });
        frameLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setVisibility(View.VISIBLE);


            }
        });
        timiemic=findViewById(R.id.timkiemic);
        timiemic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchView.setVisibility(View.VISIBLE);

            }
        });
        searchView =findViewById(R.id.timkiem);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                timkiem();

            }

            private void timkiem() {
                String timkiem = searchView.getText().toString().trim();
                compositeDisposable.add(cuahang.seach(timkiem)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                mathangmd -> {
                                    if (mathangmd.isSuccess()) {
                                        mathangadapterr = new mathangadapter(getApplicationContext(), mathangmd.getResult());
                                        recyclerView.setAdapter(mathangadapterr);

                                    }


                                }, throwable -> {
                                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                        ));
            }
        });

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
