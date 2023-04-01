package org.o7planning.appbandoan.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import org.o7planning.appbandoan.R;

import org.o7planning.appbandoan.adapter.mathangadapter;
import org.o7planning.appbandoan.ketnoi.cuahang;
import org.o7planning.appbandoan.ketnoi.maychu;
import org.o7planning.appbandoan.model.giohang;
import org.o7planning.appbandoan.model.mathang;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class thongtinsanpham extends AppCompatActivity {
    TextView txttenmathang,txtgia,txtmota;
    Button btnthem;

    private AppBarConfiguration appBarConfiguration;
    // private ActivityMainBinding binding;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    List<mathang> mathangg;
    Spinner spinner;
    cuahang cuahang;
    DrawerLayout drawerLayout;
    ArrayList<mathang> mathangs;
    ImageView imhinhmathang;
    ImageButton btnhome;
    mathang mathanggg;
    NotificationBadge badge;
    // RecyclerView recyclerView,recyclerViewAnVat;
    //mathangadapter mathangadapterr;
    //DSnewad dSnewad;
    //int IDSP;
    // MonanvatAd monanvatAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinsanpham);
        drawerLayout = findViewById(R.id.iddrawerlayout);

        //recyclerViewAnVat = findViewById(R.id.recyviewnew);
        //  dsmathang();

        /*mathangs = new ArrayList<>();
        monanvatAd = new MonanvatAd(getApplicationContext(), mathangs);
        recyclerViewAnVat.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewAnVat.setLayoutManager(layoutManager);
        recyclerViewAnVat.setAdapter(monanvatAd);
        mathangg =new ArrayList<>();*/


        txtgia = (TextView) findViewById(R.id.gia);
        txtmota = (TextView) findViewById(R.id.mota);
        txttenmathang = (TextView) findViewById(R.id.txttenmathang);

        btnthem = (Button) findViewById(R.id.themgiohang);
        spinner = (Spinner) findViewById(R.id.spinner);
        imhinhmathang = (ImageView) findViewById(R.id.imthongtin);
        badge = (NotificationBadge) findViewById(R.id.solg);
        FrameLayout frameLayout=findViewById(R.id.giohangfr);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Giohang.class);
                startActivity(intent);
            }
        });
        them();
        themgiohang();
        if (maychu.dshang != null) {
            int solgtthem = 0;
            for(int i = 0; i< maychu.dshang.size(); i++){
                solgtthem = solgtthem+maychu.dshang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(solgtthem));
        }

    }


    private void themgiohang() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themgh();
            }

            private void themgh() {


                if(maychu.dshang.size() > 0)
                {
                    boolean flag =false;
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    for(int i =0; i < maychu.dshang.size(); i++){
                        if(maychu.dshang.get(i).getMamathang() == mathanggg.getMamathang()){
                            maychu.dshang.get(i).setSoluong(soluong + maychu.dshang.get(i).getSoluong()) ;
                            long gia =(mathanggg.getGia())/* * maychu.dshang.get(i).getSoluong()**/;
                            maychu.dshang.get(i).setGiasp(gia);
                            flag=true;
                        }
                    }
                    if(flag==false){
                        giohang giohang = new giohang();
                        long gia=(mathanggg.getGia());//*soluong;
                        giohang.setGiasp(gia);
                        giohang.setSoluong(soluong);
                        giohang.setMamathang(mathanggg.getMamathang());
                        giohang.setTenmathang(mathanggg.getTenmathang());
                        giohang.setHinhanhmathang(mathanggg.getHinhanhmathang());
                        maychu.dshang.add(giohang);

                    }

                }else {
                    giohang giohang = new giohang();
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long gia=( mathanggg.getGia());//* soluong;
                    giohang.setGiasp(gia);;
                    giohang.setSoluong(soluong);
                    giohang.setMamathang(mathanggg.getMamathang());
                    giohang.setTenmathang(mathanggg.getTenmathang());
                    giohang.setHinhanhmathang(mathanggg.getHinhanhmathang());
                    maychu.dshang.add(giohang);
                }
                int solgtthem = 0;
                for(int i =0; i<maychu.dshang.size();i++) {
                    solgtthem = solgtthem + maychu.dshang.get(i).getSoluong();
                }
                badge.setText(String.valueOf(solgtthem));

            }
        });
        btnhome = findViewById(R.id.idhome);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Trangchu.class);
                startActivity(intent);
                finish();
            }
        });

    }



    private void them() {
        mathanggg =mathanggg= (mathang) getIntent().getSerializableExtra("thongtin");
        txttenmathang.setText(mathanggg.getTenmathang());
        txtmota.setText(mathanggg.getMota());
        DecimalFormat decimalFormat = new DecimalFormat("###.###");
        Glide.with(getApplicationContext()).load(mathanggg.getHinhanhmathang()).into(imhinhmathang);
        txtgia.setText("Gía: "+(mathanggg.getGia())+"đ");

        Integer[] SO = new Integer[]{1};
        ArrayAdapter<Integer> adins = new ArrayAdapter<>(this, io.paperdb.R.layout.support_simple_spinner_dropdown_item, SO);
        spinner.setAdapter(adins);
    }

    @Override
    protected void onResume() {
        super.onResume();
        themgiohang();
        if (maychu.dshang != null) {
            int solgtthem = 0;
            for(int i =0; i<maychu.dshang.size();i++){
                solgtthem = solgtthem+maychu.dshang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(solgtthem));
        }

    }
   /* private void dsmathang() {
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
    }*/

}


