package material.com.materialdesign;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import material.com.materialdesignexample.R;

public class Splash extends AppCompatActivity {

    ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashImage=findViewById(R.id.splash_image);
        RotateAnimation rotate = new RotateAnimation(30, 360, Animation.RELATIVE_TO_SELF, 0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setDuration(100);
        splashImage.startAnimation(rotate);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent homeSplash=new Intent(Splash.this, MainIndexScreenActivity.class);
                startActivity(homeSplash);
                Splash.this.finish();

            }
        }, 500);

    }
}
