package jf.andro.wearconfirmationdialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends WearableActivity implements DelayedConfirmationView.DelayedConfirmationListener {

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setAmbientEnabled();

        setContentView(R.layout.confirmation_layout);

        DelayedConfirmationView mDelayedView =
                (DelayedConfirmationView) findViewById(R.id.delayed_confirm);
        mDelayedView.setListener(this);

        // Two seconds to cancel the action
        mDelayedView.setTotalTimeMs(5000);
        // Start the timer
        mDelayedView.start();

    }

    @Override
    public void onTimerFinished(View view) {
        //finish();
        setContentView(R.layout.activity_main_wearable);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CardFragment cardFragment = CardFragment.create("Méchant !", "-1 à l'examen !", R.drawable.dandelion);
        fragmentTransaction.add(R.id.frame_layout, cardFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onTimerSelected(View view) {

    }
}
