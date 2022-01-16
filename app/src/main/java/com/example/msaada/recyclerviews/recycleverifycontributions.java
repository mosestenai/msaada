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

import com.example.msaada.helpers.Model;
import com.example.msaada.R ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.msaada.helpers.Anime;
import com.example.msaada.helpers.User;
import com.example.msaada.admin.verifyActivity;
import com.example.msaada.api.AbstractAPIListener;

import org.w3c.dom.Text;

import java.util.List;

public class recycleverifycontributions extends RecyclerView.Adapter<recycleverifycontributions.MyViewHolder> {

    private final Context mContext;
    private final List<Anime> mData;
    RequestOptions option;

    public recycleverifycontributions(Context mContext, List<Anime> mData) {

        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_verify,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
        holder.description.setText(mData.get(position).getDescription());
        holder.amount.setText(mData.get(position).getAmount());
        holder.id.setText(mData.get(position).getId());
        holder.status.setText(mData.get(position).getStatus());
        holder.ref1.setText(mData.get(position).getRef1());
        holder.ref2.setText(mData.get(position).getRef2());
        holder.phone1.setText(mData.get(position).getPhone1());
        holder.phone2.setText(mData.get(position).getPhone2());
        holder.name.setText(mData.get(position).getName());
        holder.amountt.setText(mData.get(position).getAmountt());
        //load image from the internet and set it to Image view using glide
        // Glide.with(mContext).load(mData.get(position).getUrl()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,description,amount,id,ref1,ref2,phone1,phone2,status,name,amountt;
        Button verify;
        public MyViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            amount = itemView.findViewById(R.id.amount);
            verify= itemView.findViewById(R.id.verify);
            status = itemView.findViewById(R.id.status);
            id = itemView.findViewById(R.id.id);
            ref1 = itemView.findViewById(R.id.ref1);
            ref2 = itemView.findViewById(R.id.ref2);
            phone1 = itemView.findViewById(R.id.phone1);
            phone2 = itemView.findViewById(R.id.phone2);
            name = itemView.findViewById(R.id.name);
            amountt = itemView.findViewById(R.id.amountt);


            verify.setOnClickListener(v->{
                String idd = id.getText().toString();
                String descriptionn = description.getText().toString();
                String titlee = title.getText().toString();
                String amounttt = amount.getText().toString();
                String reff1 = ref1.getText().toString();
                String reff2 = ref2.getText().toString();
                String phonee1 = phone1.getText().toString();
                String phonee2 = phone2.getText().toString();
                String namee = name.getText().toString();
                String amounnt = amountt.getText().toString();
                final ProgressDialog progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setMessage("Verifying...");
                progressDialog.show();

                final Model model = Model.getInstance((Application) v.getContext().getApplicationContext());
                model.verify(idd,titlee,descriptionn,amounttt,reff1,reff2,phonee1,phonee2,namee,amounnt, new AbstractAPIListener() {
                    @Override
                    public void onVerify(User user) {
                        model.setUser(user);
                        if (user.getSuccess() != null) {
                            Toast.makeText(v.getContext(), user.getSuccess(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(v.getContext(), verifyActivity.class);
                            v.getContext().startActivity(intent);
                        } else {
                            Toast.makeText(v.getContext(), user.getError(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


                });
            });
        }

    }
}
