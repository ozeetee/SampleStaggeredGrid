package io.togoto.samplestaggeredgrid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author GT
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private List<Entry> mDataset;
    private static final int DATASET_COUNT = 60;
    private MyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyAdapter(mDataset);
        mRecyclerView.setAdapter(adapter);
        return rootView;
    }

    private void initDataset() {
        mDataset = new ArrayList<>();
        for (int i = 0; i < DATASET_COUNT; i++) {
            Entry e = new Entry();
            e.setHeading("Heading #" + i);
            int count = randInt();
            for(int j = 0; j < count;j++){
                e.addPoint("Point num. : " + j);
            }
            mDataset.add(e);
        }
    }

    public static int randInt() {
        int min = 2;
        int max = 11;

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }


    public MyAdapter getAdapter(){
        return this.adapter;
    }

    public RecyclerView getmRecyclerView(){
        return mRecyclerView;
    }
}
