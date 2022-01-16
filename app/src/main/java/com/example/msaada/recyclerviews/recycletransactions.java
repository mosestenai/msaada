package com.example.msaada.recyclerviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.msaada.R ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.msaada.helpers.Anime;

import org.w3c.dom.Text;

import java.util.List;

public class recycletransactions extends RecyclerView.Adapter<recycletransactions.MyViewHolder> {

    private final Context mContext;
    private final List<Anime> mData;
    RequestOptions option;

    public recycletransactions(Context mContext, List<Anime> mData) {

        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_transactions,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.phone.setText(mData.get(position).getPhone());
        holder.amount.setText(mData.get(position).getAmount());
        holder.id.setText(mData.get(position).getId());
        holder.date.setText(mData.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView phone,amount,id,date;
        public MyViewHolder(View itemView){
            super(itemView);
            phone = itemView.findViewById(R.id.phone);
            amount= itemView.findViewById(R.id.amount);
            id = itemView.findViewById(R.id.id);
            date  = itemView.findViewById(R.id.date);
        }

    }
}
