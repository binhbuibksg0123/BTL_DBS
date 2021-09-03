package com.example.test0;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.viewHolder> {
    ArrayList<Book> books = new ArrayList<>();
    Context context;
    String CustomerID;

    public BookAdapter(Context context,String customerID) {
        this.context = context;
        CustomerID = customerID;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.tv0.setText(books.get(position).getName());
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getUrlImg())
                .into(holder.iv0);
        holder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,bookActivity.class);
                intent.putExtra("BookID",books.get(holder.getAdapterPosition()).getId());
                intent.putExtra("CustomerID",CustomerID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }


    class viewHolder extends RecyclerView.ViewHolder {
        CardView cv0;
        ImageView iv0;
        TextView tv0;
        RelativeLayout rel;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cv0 = itemView.findViewById(R.id.parent);
            iv0 = itemView.findViewById(R.id.imgB);
            tv0 = itemView.findViewById(R.id.tvNB);
            rel = itemView.findViewById(R.id.relParent);
        }
    }
}
