package ogare.show;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by hp on 16/05/2019.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder >{

    ArrayList<String> arrayList ;
    Context context ;

    public ListAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subfolder, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.editText.setText(arrayList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return (arrayList.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        EditText editText ;

        public MyViewHolder(View itemView) {
            super(itemView);

            editText = itemView.findViewById(R.id.sub);
        }
    }
}
