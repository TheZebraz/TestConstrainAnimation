package elatesoftware.com.testconstrainanimation;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean state = true; //t - start

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);

        final ConstraintLayout rootLayout = (ConstraintLayout) findViewById(R.id.content);

        final ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(rootLayout);

        final ConstraintSet constraintSetBig = new ConstraintSet();
        constraintSetBig.clone(this, R.layout.activity_main_finish);

        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(rootLayout);
                    if (state) {
                        constraintSet.applyTo(rootLayout);
                    }
                    else {
                        constraintSetBig.applyTo(rootLayout);
                    }
                    state = !state;
                }
            }
        });
    }
}
