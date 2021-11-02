package vidu.demo.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        imageView = this.findViewById (R.id.img_man_hinh_chao);
        new Handler ().postDelayed (new Runnable () {
            @Override
            public void run() {
                imageView.setVisibility (View.GONE);
                Intent intent = new Intent (MainActivity.this,ManHinhLogin.class);
                startActivity (intent);
            }
        },2000);
    }
}