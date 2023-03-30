package org.o7planning.appbandoan.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

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

import com.bumptech.glide.Glide;
import com.nex3z.notificationbadge.NotificationBadge;

import org.o7planning.appbandoan.R;
import org.o7planning.appbandoan.ketnoi.maychu;
import org.o7planning.appbandoan.model.mathang;
import org.o7planning.appbandoan.ketnoi.cuahang;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

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

    }


    private void themgiohang() {

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

        }

    }




