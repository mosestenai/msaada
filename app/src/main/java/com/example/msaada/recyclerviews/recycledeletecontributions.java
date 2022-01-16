package com.example.msaada.recyclerviews;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msaada.admin.verifyActivity;
import com.example.msaada.helpers.Model;
import com.example.msaada.R ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.msaada.helpers.Anime;
import com.example.msaada.helpers.User;
import com.example.msaada.api.AbstractAPIListener;

import org.w3c.dom.Text;

import java.util.List;

public class recycledeletecontributions extends RecyclerView.Adapter<recycledeletecontributions.MyViewHolder> {

    private final Context mContext;
    private final List<Anime> mData;
    RequestOptions option;


    public recycledeletecontributions(Context mContext, List<Anime> mData) {

        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_delete,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(mData.get(position).getTitle());
        holder.description.setText(mData.get(position).getDescription());
        holder.amount.setText(mData.get(position).getAmount());
        holder.id.setText(mData.get(position).getId());
        holder.ref1.setText(mData.get(position).getRef1());
        holder.ref2.setText(mData.get(position).getRef2());
        holder.phone1.setText(mData.get(position).getPhone1());
        holder.phone2.setText(mData.get(position).getPhone2());
        //load image from the internet and set it to Image view using glide
        // Glide.with(mContext).load(mData.get(position).getUrl()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,description,amount,id,ref1,ref2,phone1,phone2;
        Button delete;
        public MyViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            amount = itemView.findViewById(R.id.amount);
            delete= itemView.findViewById(R.id.delete);
            id = itemView.findViewById(R.id.id);
            ref1 = itemView.findViewById(R.id.ref1);
            ref2 = itemView.findViewById(R.id.ref2);
            phone1 = itemView.findViewById(R.id.phone1);
            phone2 = itemView.findViewById(R.id.phone2);



            delete.setOnClickListener(v->{
                String idd = id.getText().toString();
                final ProgressDialog progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setMessage("Deleting...");
                progressDialog.show();

                final Model model = Model.getInstance((Application) v.getContext().getApplicationContext());
                model.delete(idd, new AbstractAPIListener() {
                    @Override
                    public void onDelete(User user) {
                        model.setUser(user);
                       if(user.getSuccess() != null) {
                           Toast.makeText(v.getContext(),user.getSuccess(),Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(v.getContext(),com.example.msaada.admin.deleteActivity.class);
                           v.getContext().startActivity(intent);
                          // v.getContext().notifyAll();

                       }else {
                           Toast.makeText(v.getContext(), user.getError(), Toast.LENGTH_SHORT).show();
                       }
                        progressDialog.dismiss();
                    }


                });
            });
        }

    }
}
