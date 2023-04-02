package org.o7planning.appbandoan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import org.o7planning.appbandoan.Interface.Imclick;
import org.o7planning.appbandoan.R;
import org.o7planning.appbandoan.model.giohang;

import java.text.DecimalFormat;
import java.util.List;

public class thanhtoanad1 extends RecyclerView.Adapter<thanhtoanad1.Myholder> {
    Context context;
    List<giohang> listgiohang;


    public thanhtoanad1(Context context, List<giohang> listgiohang) {
        this.context = context;
        this.listgiohang = listgiohang;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thanhtoan1,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        giohang giohang= listgiohang.get(position);
        holder.tenmathanggiohang.setText(giohang.getTenmathang());
        holder.soluong.setText(giohang.getSoluong() + " ");
        Glide.with(context).load(giohang.getHinhanhmathang()).into(holder.hinhmathanggiohang);
        DecimalFormat decimalFormat = new DecimalFormat("###.###");
        holder.giamat1.setText("Giá: "+(giohang.getGiasp())+"đ");
        long gia = giohang.getSoluong()*giohang.getGiasp();
        holder.tonggia.setText(decimalFormat.format(gia)+"đ");
    }

    @Override
    public int getItemCount() {
        return listgiohang.size();
    }


    public class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView hinhmathanggiohang,tru,cong;
        TextView tenmathanggiohang,giamat1,soluong,tonggia;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            hinhmathanggiohang = itemView.findViewById(R.id.hinhmathanggiohang);
            tenmathanggiohang = itemView.findViewById(R.id.tenmathanggiohang);
            soluong = itemView.findViewById(R.id.soluong);
            giamat1 = itemView.findViewById(R.id.giamat1);
            tonggia = itemView.findViewById(R.id.tonggia);


        }

        public void setImclick(Imclick imclick) {

        }

        @Override
        public void onClick(View view) {

        }
    }
}
