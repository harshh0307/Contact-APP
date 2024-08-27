package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecylerContactAdapter extends RecyclerView.Adapter<RecylerContactAdapter.ViewHolder> {
    Context context;
    private int p=-1;
    ArrayList<ContactModel> contactModels;

    RecylerContactAdapter(Context context,ArrayList<ContactModel> contactModels){
        this.context=context;
        this.contactModels=contactModels;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,  int pos) {

        ContactModel model=(ContactModel)contactModels.get(holder.getAdapterPosition());
        holder.img.setImageResource(model.img);
        holder.name.setText(model.name);
        holder.contnum.setText(model.number);
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = String.format("tel: %s",holder.contnum.getText().toString());
                Intent idial=new Intent(Intent.ACTION_DIAL);
                idial.setData(Uri.parse(phoneNumber));
                context.startActivity(idial);

            }
        });
        setAnimation(holder.itemView,pos);
        holder.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.add_update);
                EditText edt2 = dialog.findViewById(R.id.name);
                EditText edt1 = dialog.findViewById(R.id.number);
                EditText edt3=dialog.findViewById(R.id.s);
                Button btn = dialog.findViewById(R.id.buttonaction);
                TextView t=dialog.findViewById(R.id.txttitle);
                t.setText("Update Contact");
                btn.setText("Update");
                edt2.setText(( contactModels.get(holder.getAdapterPosition())).name);
                edt1.setText(( contactModels.get(holder.getAdapterPosition())).number);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", number = "";
                        if (!edt2.getText().toString().equals("")) {
                            name = edt2.getText().toString();
                        } else {
                            Toast.makeText(context, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                        }
                        if (!edt1.getText().toString().equals("")) {
                            number = edt1.getText().toString();
                        }
                        else {
                            Toast.makeText(context, "Please Enter Your Number", Toast.LENGTH_SHORT).show();
                        }
                        contactModels.set(holder.getAdapterPosition(), new ContactModel(((ContactModel) contactModels.get(holder.getAdapterPosition())).img,name,number));
                        notifyItemChanged(holder.getAdapterPosition());
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });

        holder.l.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context).setTitle("Delete Contact")
                        .setMessage("Are you sure want to delete!").
                        setIcon(R.drawable.baseline_delete_24).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                contactModels.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                Toast.makeText(context,"Contact Deleted successfully",Toast.LENGTH_LONG).show();



                            }

                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {

        return contactModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,contnum;
        ImageView img;
        LinearLayout l;
        Button b;

        public ViewHolder(View itemview){
            super(itemview);
            name=itemview.findViewById(R.id.txtname);
            contnum=itemview.findViewById(R.id.txtname1);
            img=itemview.findViewById(R.id.img);
            b=itemview.findViewById(R.id.btncall);
            l=itemview.findViewById(R.id.row);


        }
    }
    public void setAnimation(View v,int position){
        if(position>p) {
            Animation a = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            v.setAnimation(a);
            p=position;
        }

    }
}
