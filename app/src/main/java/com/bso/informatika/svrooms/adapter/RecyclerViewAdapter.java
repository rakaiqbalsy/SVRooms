package com.bso.informatika.svrooms.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bso.informatika.svrooms.activities.RumahActivity;
import com.bso.informatika.svrooms.model.rumah;

import java.util.List;
import com.bso.informatika.svrooms.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by rakaiqbalsy on 5/12/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<rumah> mdata;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<rumah> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;

        //request Option
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.home_row_item, parent, false);

        final MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, RumahActivity.class);
                i.putExtra("home_name", mdata.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("home_description", mdata.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("home_perusahaan", mdata.get(viewHolder.getAdapterPosition()).getPerusahaan());
                i.putExtra("home_category", mdata.get(viewHolder.getAdapterPosition()).getCategory());
                i.putExtra("home_img", mdata.get(viewHolder.getAdapterPosition()).getImg_url());
                i.putExtra("home_harga", mdata.get(viewHolder.getAdapterPosition()).getHarga());
                i.putExtra("home_alamat", mdata.get(viewHolder.getAdapterPosition()).getAlamat());

                mContext.startActivity(i);

            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mdata.get(position).getName());
        holder.tv_category.setText(mdata.get(position).getCategory());
        holder.tv_rating.setText(mdata.get(position).getRating());
        holder.tv_perusahaan.setText(mdata.get(position).getPerusahaan());

        //glide Image

        Glide.with(mContext).load(mdata.get(position).getImg_url()).apply(option).into(holder.img_tumbnail);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_category;
        TextView tv_rating;
        TextView tv_perusahaan;
        ImageView img_tumbnail;
        LinearLayout view_container;


        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.idRumah);
            tv_category = itemView.findViewById(R.id.idcategory);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_perusahaan = itemView.findViewById(R.id.idperusahaan);
            img_tumbnail = itemView.findViewById(R.id.thubnail);
        }
    }
}
