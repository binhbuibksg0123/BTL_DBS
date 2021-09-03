package com.example.test0;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Comment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class commentAdapt extends RecyclerView.Adapter<commentAdapt.viewHolder> {
    public ArrayList<comment> comments;
    Context context;
    String idCustomer;
    static Connection connect = ConnectionHelper.getConnect();
    public commentAdapt(Context context, ArrayList<comment> comments,String name) {
        this.context = context;
        this.comments = comments;
        this.idCustomer = name;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cmtcardview, parent, false);
        return new commentAdapt.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        comment a = comments.get(position);
        holder.tvName.setText(a.getUserName());
        holder.cmt.setText(a.getCmt());
        holder.time.setText(a.getTime());
        holder.eva.setRating(a.getEva());
        holder.ship.setRating(a.getShip());
        holder.ser.setRating(a.getSer());
        if(comments.get(position).getId().equals(this.idCustomer)){
            holder.del.setVisibility(View.VISIBLE);
        }
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this comment ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            Statement smt = connect.createStatement();
                            ResultSet rs = smt.executeQuery("DELETE FROM EBook.Content\n" +
                                    "WHERE Time = '" + comments.get(holder.getAdapterPosition()).getTime() + "'");
                        } catch (Exception e) {

                        }
                        try {
                            Statement smt = connect.createStatement();
                            ResultSet rs = smt.executeQuery("DELETE FROM EBook.RComment\n" +
                                    "WHERE RTime = '" + comments.get(holder.getAdapterPosition()).getTime() + "'");
                        } catch (Exception e) {

                        }
                        try {
                            Statement smt = connect.createStatement();
                            ResultSet rs = smt.executeQuery("DELETE FROM EBook.Comment\n" +
                                    "WHERE CmtTime = '" + comments.get(holder.getAdapterPosition()).getTime() + "'");
                        } catch (Exception e) {

                        }
                        comments.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyItemRangeChanged(holder.getAdapterPosition(), comments.size());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        CardView cvCmt;
        TextView tvName,time,cmt,del;
        RatingBar eva,ship,ser;
        ConstraintLayout parent;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cvCmt = itemView.findViewById(R.id.parrentCmtSQL);
            parent = itemView.findViewById(R.id.parrentLayoutToDel);
            tvName = itemView.findViewById(R.id.tvUserNameSQL);
            time= itemView.findViewById(R.id.tvTimeSQL);
            cmt = itemView.findViewById(R.id.tvCmtSQL);
            eva = itemView.findViewById(R.id.ratingBarEvaCmt);
            ship = itemView.findViewById(R.id.ratingBarShipCmt);
            ser = itemView.findViewById(R.id.ratingBarSerSQL);
            del = itemView.findViewById(R.id.tvDelCmtSQL);
        }
    }
}
