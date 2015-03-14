package io.togoto.samplestaggeredgrid;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {


    private RecyclerViewFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            fragment = new RecyclerViewFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_beg) {
            addEntry();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEntry(){
        Entry e = new Entry();
        e.setHeading("Heading ####");
        int count = RecyclerViewFragment.randInt();
        for(int j = 0; j < count;j++){
            e.addPoint("Point num. : " + j);
        }
        MyAdapter adapter = fragment.getAdapter();
        RecyclerView rv = fragment.getmRecyclerView();
        if(adapter != null){
            adapter.addEntryBeg(e);
            rv.scrollToPosition(0);
        }
    }
}
