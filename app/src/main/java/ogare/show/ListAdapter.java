package ogare.show;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by hp on 16/05/2019.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder >{

    ArrayList<File> arrayList ;
    Context context ;
    int type;

    public ListAdapter(ArrayList<File> arrayList,int type, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.type = type;
    }

    @Override
    public int getItemViewType(int position) {
        return  type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        if(viewType == 1)
        { v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subfolder, parent, false); }

        else
        { v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false); }

        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if(type==1) {

            holder.textView.setText(arrayList.get(position).getName());
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(context, DisplayActivity.class);
                    intent.putExtra("File", arrayList.get(position).getAbsoluteFile());
                    context.startActivity(intent);

                }
            });
        }
        else {

           //convert file to bitmap to set in image view

          //  String filePath = arrayList.get(position).getPath();
            /*Bitmap bitmap = BitmapFactory.decodeFile(filePath);*/

            Uri imageUri = Uri.fromFile(arrayList.get(position));

            Glide.with(context)
                    .load(imageUri)
                    .into(holder.imageview);

         //   holder.imageview.setImageBitmap(bitmap);

        }


    }

    @Override
    public int getItemCount() {
        return (arrayList.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageview;
        LinearLayout linearLayout;


        public MyViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);
            imageview = itemView.findViewById(R.id.img);
            linearLayout = itemView.findViewById(R.id.detail);

        }


    }


}
