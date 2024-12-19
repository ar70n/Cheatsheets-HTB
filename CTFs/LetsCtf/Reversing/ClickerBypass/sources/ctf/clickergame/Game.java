package ctf.clickergame;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {
    Button button;
    Button multiplier_btn;

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.multiplier_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new a(new a("asddd").a);
            }
        });
        this.button.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(Game.this.button, "scaleX", new float[]{0.8f});
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(Game.this.button, "scaleY", new float[]{0.8f});
                    ofFloat.setDuration(100);
                    ofFloat2.setDuration(100);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(ofFloat).with(ofFloat2);
                    animatorSet.start();
                } else if (action == 1) {
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(Game.this.button, "scaleX", new float[]{1.0f});
                    ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(Game.this.button, "scaleY", new float[]{1.0f});
                    ofFloat3.setDuration(100);
                    ofFloat4.setDuration(100);
                    AnimatorSet animatorSet2 = new AnimatorSet();
                    animatorSet2.play(ofFloat3).with(ofFloat4);
                    animatorSet2.start();
                }
                return false;
            }
        });
    }
}
