package cs.ubbcluj.ro.cleannotes.model;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import cs.ubbcluj.ro.cleannotes.R;
import de.greenrobot.event.EventBus;

/**
 * Created by motan on 02.11.2015.
 */
public class BaseActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    public interface OnBackStackPoppedListener {
        void onPopped();
    }

    private OnBackStackPoppedListener mListener;

    public void setOnBackStackPoppedListener(OnBackStackPoppedListener listener) {
        this.mListener = listener;
    }

/*
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
*/


    /**
     * Back button support for fragments.
     */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Pops the back stack of fragments and signals it to listener, if set.
     */
    private void popBackStack() {
        getFragmentManager().popBackStack();
        signalBackStackPopped();
    }

    private void signalBackStackPopped() {
        if (mListener != null) {
            mListener.onPopped();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                //RealmUtils.cleanupTable(this, Note.class);
                //TODO clearAll in manager
                //mAdapter.notifyDataSetChanged();
                break;
            case android.R.id.home:
                popBackStack();
                break;
        }
        return true;
    }

    /**
     * Back as home "up" button support.
     */
    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp(){
        //Enable Up button only  if there are entries in the back stack
        boolean canback = getFragmentManager().getBackStackEntryCount()>0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
    }


}
