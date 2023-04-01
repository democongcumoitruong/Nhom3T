package org.o7planning.appbandoan.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.o7planning.appbandoan.R;
import org.o7planning.appbandoan.adapter.GioHangad;
import org.o7planning.appbandoan.ketnoi.cuahang;
import org.o7planning.appbandoan.ketnoi.maychu;
import org.o7planning.appbandoan.model.eventbus.tong;
import org.o7planning.appbandoan.model.mathang;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

public class Giohang extends AppCompatActivity {
    TextView txtronggh, txttongtien,txttongtxt,txtkhuyenmai,txtkhuyenmai1,txtkhuyenmai2,txtkhuyenmai3;
    Toolbar toolbar;
    ImageView imgback1;
    ImageButton btnhome;
    RecyclerView recyclerView;
    Button btnthanhtoan;
    GioHangad gioHangad;
    long tongtien =0;
    mathang mathangg;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        txtkhuyenmai=findViewById(R.id.khuyenmaitxt);
        txtkhuyenmai1=findViewById(R.id.khuyenmaitxt1);
        txtkhuyenmai2=findViewById(R.id.khuyenmaitxt2);
        txtkhuyenmai3=findViewById(R.id.khuyenmaitxt3);
        txttongtxt=findViewById(R.id.tongtientxt);
        txtronggh= (TextView) findViewById(R.id.txttrong);
        txttongtien= (TextView) findViewById(R.id.tongtien);
        recyclerView=(RecyclerView) findViewById(R.id.recyview);
        btnthanhtoan=(Button) findViewById(R.id.thanhtoan);
        btnhome = findViewById(R.id.home);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        gioHangad =new GioHangad(getApplicationContext(), maychu.dshang);
        recyclerView.setAdapter(gioHangad);

        if(maychu.dshang.size()==0){
            btnthanhtoan.setVisibility(View.INVISIBLE);
            txttongtien.setVisibility(View.INVISIBLE);
            txttongtxt.setVisibility(View.INVISIBLE);
            txtronggh.setVisibility(View.VISIBLE);
            txtkhuyenmai2.setVisibility(View.INVISIBLE);
            txtkhuyenmai.setVisibility(View.INVISIBLE);
            txtkhuyenmai1.setVisibility(View.INVISIBLE);
            txtkhuyenmai3.setVisibility(View.INVISIBLE);

        }else
        {
            txtronggh.setVisibility(View.INVISIBLE);
            txttongtxt.setVisibility(View.VISIBLE);
            txttongtien.setVisibility(View.VISIBLE);
            btnthanhtoan.setVisibility(View.VISIBLE);
        }
        tongtien();
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getApplicationContext(),ThanhToan.class);
                intent.putExtra("tongtien",tongtien);
                startActivity(intent);
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

    private void tongtien() {
        tongtien =0;
        for (int i=0; i<maychu.dshang.size();i++) {
            tongtien = tongtien + (maychu.dshang.get(i).getGiasp() * maychu.dshang.get(i).getSoluong());
        }

        DecimalFormat decimalFormat = new DecimalFormat("###.###");
        txttongtien.setText(decimalFormat.format(tongtien)+"đ");
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void tinhtong(tong event){
        if(event!=null){
            tongtien();
        }
    }
}

