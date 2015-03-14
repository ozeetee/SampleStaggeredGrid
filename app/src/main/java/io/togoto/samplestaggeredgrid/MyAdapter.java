package io.togoto.samplestaggeredgrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * @author GT
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener{


    private static final String TAG = "MyAdapter";
    private List<Entry> dataSet;
    private Context mContext;
    public MyAdapter(List<Entry> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.mContext = viewGroup.getContext();
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.entry_layout, viewGroup, false);
        Button btnDel =  (Button) v.findViewById(R.id.btn_del);
        btnDel.setOnClickListener(this);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        Entry item = dataSet.get(i);
        String t = item.getHeading();
        viewHolder.getTextView().setText(t);
        List<String> points = item.getPoints();
        ViewGroup ll = viewHolder.getPointsContainerView();
        ll.removeAllViews();
        for (String point :points){
            TextView tv = new TextView(mContext);
            tv.setText(point);
            viewHolder.getPointsContainerView().addView(tv);
        }
        View itemView = viewHolder.itemView;
        View btnV = itemView.findViewById(R.id.btn_del);
        btnV.setTag(item);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onClick(View v) {
        Entry e = (Entry)v.getTag();
        deleteItem(e);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ViewGroup llPointsContainer;
        public MyViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.tv_heading);
            llPointsContainer =  (ViewGroup) v.findViewById(R.id.ll_points);
        }
        public TextView getTextView() {
            return textView;
        }
        public ViewGroup getPointsContainerView(){return llPointsContainer;}
    }

    public void deleteItem(Entry e){
        int position = dataSet.indexOf(e);
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void addEntryBeg(Entry e){
        addEntry(e,0);
    }
    public void addEntryEnd(Entry e){
        addEntry(e,dataSet.size());
    }

    public void addEntry(Entry e, int position){
        dataSet.add(position, e);
        notifyItemInserted(position);
    }
}
