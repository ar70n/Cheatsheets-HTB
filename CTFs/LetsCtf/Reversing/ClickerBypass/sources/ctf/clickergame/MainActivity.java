package ctf.clickergame;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView FlagViewText;
    ImageButton button;
    long coins;
    TextView counter;
    View flagView;
    Button flag_button;
    int multiplier;
    Button multiplier_btn;
    long multiplier_cost;
    TextView multiplier_cost_text;
    String textSet;

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        getSupportActionBar().hide();
        this.button = (ImageButton) findViewById(R.id.coin_pic_button);
        this.multiplier_btn = (Button) findViewById(R.id.multiplier);
        this.flag_button = (Button) findViewById(R.id.flag_button);
        this.counter = (TextView) findViewById(R.id.counter);
        this.multiplier_cost_text = (TextView) findViewById(R.id.multiplier_cost);
        this.flagView = findViewById(R.id.FlagView);
        this.FlagViewText = (TextView) findViewById(R.id.FlagViewText);
        this.multiplier_cost_text.setText(String.valueOf(this.multiplier_cost));
        this.button.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(MainActivity.this.button, "scaleX", new float[]{0.8f});
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(MainActivity.this.button, "scaleY", new float[]{0.8f});
                    ofFloat.setDuration(100);
                    ofFloat2.setDuration(100);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(ofFloat).with(ofFloat2);
                    animatorSet.start();
                } else if (action == 1) {
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(MainActivity.this.button, "scaleX", new float[]{1.0f});
                    ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(MainActivity.this.button, "scaleY", new float[]{1.0f});
                    ofFloat3.setDuration(100);
                    ofFloat4.setDuration(100);
                    AnimatorSet animatorSet2 = new AnimatorSet();
                    animatorSet2.play(ofFloat3).with(ofFloat4);
                    animatorSet2.start();
                }
                return false;
            }
        });
        this.multiplier_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.coins >= MainActivity.this.multiplier_cost) {
                    MainActivity.this.multiplier++;
                    MainActivity.this.coins -= MainActivity.this.multiplier_cost;
                    MainActivity.this.multiplier_cost *= 4;
                    MainActivity.this.multiplier_cost_text.setText(String.valueOf(MainActivity.this.multiplier_cost));
                    MainActivity.this.counter.setText(String.valueOf(MainActivity.this.coins));
                }
            }
        });
        this.flag_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.coins >= 100000) {
                    MainActivity.this.coins -= 100000;
                    MainActivity.this.counter.setText(String.valueOf(MainActivity.this.coins));
                    MainActivity.this.flagView.setVisibility(0);
                    MainActivity.this.FlagViewText.setVisibility(0);
                    MainActivity.this.FlagViewText.setText("letsctf{" + MainActivity.this.textSet + "}");
                }
            }
        });
        this.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.coins += (long) (MainActivity.this.multiplier * 1);
                MainActivity.this.counter.setText(String.valueOf(MainActivity.this.coins));
                MainActivity.this.button.setImageResource(R.drawable.coin_pic);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        this.coins = 0;
        this.multiplier = 1;
        this.multiplier_cost = 100;
        this.textSet = new c("asdfff").getHex();
    }
}
