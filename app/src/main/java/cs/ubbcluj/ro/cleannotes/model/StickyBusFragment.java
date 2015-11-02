package cs.ubbcluj.ro.cleannotes.model;

/**
 * Created by motan on 02.11.2015.
 */
public class StickyBusFragment extends BaseBusFragment {

    @Override
    public void onStart() {
        bus().registerSticky(this);
        super.onStart();
    }

}
