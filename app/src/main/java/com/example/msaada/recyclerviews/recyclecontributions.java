package com.example.msaada.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msaada.R ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.msaada.helpers.Anime;
import com.example.msaada.contributor.payActivity;

import org.w3c.dom.Text;

import java.util.List;

public class recyclecontributions extends RecyclerView.Adapter<recyclecontributions.MyViewHolder> {

    private final Context mContext;
    private final List<Anime> mData;
    RequestOptions option;

    public recyclecontributions(Context mContext, List<Anime> mData) {

        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_contributions,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
        holder.description.setText(mData.get(position).getDescription());
        holder.amount.setText(mData.get(position).getAmount());
        holder.id.setText(mData.get(position).getId());
        holder.amountt.setText(mData.get(position).getRaised());
        //load image from the internet and set it to Image view using glide
       // Glide.with(mContext).load(mData.get(position).getUrl()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,description,amount,id,amountt;
        Button pay;
        public MyViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            amount = itemView.findViewById(R.id.amount);
            pay= itemView.findViewById(R.id.pay);
            id = itemView.findViewById(R.id.idd);
            amountt = itemView.findViewById(R.id.amountt);



            pay.setOnClickListener(v->{
                String idd = id.getText().toString();
                Intent intent = new Intent(v.getContext(), payActivity.class);
                intent.putExtra("id",idd);
                v.getContext().startActivity(intent);
            });
        }

    }
}
