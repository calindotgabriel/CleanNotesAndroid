package cs.ubbcluj.ro.cleannotes.model;

import android.app.Fragment;

import de.greenrobot.event.EventBus;

/**
 * Created by motan on 02.11.2015.
 */
public class BaseBusFragment extends Fragment {

    @Override
    public void onStop() {
        bus().unregister(this);
        super.onStop();
    }

    protected EventBus bus() {
        return EventBus.getDefault();
    }
}
