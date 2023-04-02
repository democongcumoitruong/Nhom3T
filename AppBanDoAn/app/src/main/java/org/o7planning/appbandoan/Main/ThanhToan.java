package org.o7planning.appbandoan.Main;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.o7planning.appbandoan.R;
import org.o7planning.appbandoan.adapter.GioHangad;
import org.o7planning.appbandoan.adapter.thanhtoanad1;
import org.o7planning.appbandoan.ketnoi.client;
import org.o7planning.appbandoan.ketnoi.cuahang;
import org.o7planning.appbandoan.ketnoi.maychu;

import com.google.gson.Gson;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThanhToan extends AppCompatActivity {
    TextView ten,sdtT,mail,tongtien;
    EditText editTextdiachi;
    Button xacnhan,btnthanhtoanzalo;
    ImageView imthanhtoan;
    ImageButton imhome;
    FrameLayout frameLayout;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    cuahang cuahang;
    long tongtienn;
    TextView thanhtoanrong;
    LinearLayout linearLayout;
    int solgtthem,iddonhang;
    RecyclerView recyclerView;
    NotificationBadge badge;
    thanhtoanad1 gioHangad;
    // private String amount = "10000";
    //private String fee = "0";
    // int environment = 1;//developer default
    //  private String merchantName = "Demo SDK";
    // private String merchantCode = "SCB01";
    //  private String merchantNameLabel = "Nhà cung cấp";
    // private String description = "Thanh toán dịch vụ ABC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        linearLayout = findViewById(R.id.layoutthanhtoan);
        thanhtoanrong = findViewById(R.id.txtrongthanhtoan);
        imhome = findViewById(R.id.home);
        frameLayout = findViewById(R.id.giohangfr);

        //ZALO
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init

        cuahang = client.getInstance(maychu.BASE_URL).create(cuahang.class);
        //  linearLayout=findViewById(R.id.layoutthanhtoan);
        ten = findViewById(R.id.tenthanhtoan);
        sdtT = findViewById(R.id.sdtthanhtoan);
        mail = findViewById(R.id.mailthanhoan);
        tongtien = findViewById(R.id.tongtienthanhtoan);
        editTextdiachi = findViewById(R.id.diachi);
        recyclerView=(RecyclerView) findViewById(R.id.recyview);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        gioHangad =new thanhtoanad1(getApplicationContext(), maychu.dshang);
        recyclerView.setAdapter(gioHangad);

        DecimalFormat decimalFormat = new DecimalFormat("###.###");
        tongtienn = getIntent().getLongExtra("tongtien", 0);
        tongtien.setText(decimalFormat.format(tongtienn) + "đ");
        sdtT.setText(maychu.userdangnhap.getSDT());
        mail.setText(maychu.userdangnhap.getGmail());
        ten.setText(maychu.userdangnhap.getTenuser());
        imhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Trangchu.class);
                startActivity(intent);
                finish();
            }
        });
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
                finish();
            }
        });
        xacnhan = findViewById(R.id.btnxacnhanmua);
        demsl();
        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diachi = editTextdiachi.getText().toString().trim();
                if (TextUtils.isEmpty(diachi)) {
                    Toast.makeText(ThanhToan.this, "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("test", new Gson().toJson(maychu.dshang));
                    String sdt = maychu.userdangnhap.getSDT();
                    String email = maychu.userdangnhap.getGmail();
                    int iduser = maychu.userdangnhap.getIDuser();
                    String tenuser = maychu.userdangnhap.getTenuser();

                    compositeDisposable.add(cuahang.donhang1(sdt, email, String.valueOf(tongtienn), iduser, tenuser, diachi, solgtthem,new Gson().toJson(maychu.dshang))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    usermd -> {
                                        Toast.makeText(getApplicationContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Trangchu.class);
                                        maychu.dshang.clear();
                                        startActivity(intent);
                                        finish();
                                    },
                                    throwable -> {

                                        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });
        btnthanhtoanzalo = findViewById(R.id.thanhtoanzalo);


    }






    private void demsl() {
        solgtthem = 0;
        for(int i =0; i<maychu.dshang.size();i++){
            solgtthem = solgtthem+maychu.dshang.get(i).getSoluong();
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

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