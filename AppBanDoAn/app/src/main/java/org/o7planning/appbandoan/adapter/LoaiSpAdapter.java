package org.o7planning.appbandoan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import org.o7planning.appbandoan.Interface.ItemClickListener;

import org.o7planning.appbandoan.Main.DoUong;
import org.o7planning.appbandoan.Main.MonChinh;
import org.o7planning.appbandoan.Main.MonNuoc;
import org.o7planning.appbandoan.Main.Monanvat;
import org.o7planning.appbandoan.R;
import org.o7planning.appbandoan.model.LoaiSp;

import java.util.List;

public class LoaiSpAdapter extends RecyclerView.Adapter<LoaiSpAdapter.holderhv> {
    Context context;
    List<LoaiSp> array;


    public LoaiSpAdapter(Context context, List<LoaiSp> array) {
        this.context = context;
        this.array = array;

    }

    @NonNull
    @Override
    public holderhv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        return new holderhv(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderhv holder, int position) {
        LoaiSp loaiSp=array.get(position);
        holder.txttenloai.setText(loaiSp.getTenloai());
        Glide.with(context).load(loaiSp.getHinhanh()).into(holder.anhsp);
       holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick)
                {
                    switch (pos){
                        case 0:
                            Intent anvat = new Intent(context, Monanvat.class);
                            anvat.putExtra("IDSP",1);
                            anvat.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(anvat);
                            break;
                        case 1:
                            Intent monchinh = new Intent(context, MonChinh.class);
                            monchinh.putExtra("IDSP",2);
                            monchinh.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(monchinh);
                            break;
                        case 2:
                            Intent monnuoc = new Intent(context, MonNuoc.class);
                            monnuoc.putExtra("IDSP",3);
                            monnuoc.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(monnuoc);
                            break;
                        case 3:
                            Intent douong = new Intent(context, DoUong.class);
                            douong.putExtra("IDSP",11);
                            douong.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(douong);
                            break;
                      }

                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return array.size();
    }


    public class holderhv extends RecyclerView.ViewHolder  implements View.OnClickListener {

        TextView txttenloai;
        ImageView anhsp;
        private ItemClickListener itemClickListener;

        public holderhv(@NonNull View itemView) {
            super(itemView);
            anhsp= (ImageView) itemView.findViewById(R.id.item_image);
            txttenloai= (TextView)itemView.findViewById(R.id.item_tensp);
            itemView.setOnClickListener(this);
        }


       public void setItemClickListener(ItemClickListener itemClickListener) {
          this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }
}
