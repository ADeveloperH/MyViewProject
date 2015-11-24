package project.hjking.cn.myview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import project.hjking.cn.myview.myviewpager.MyViewPagerActivity;
import project.hjking.cn.myview.youkumenu.MyYouKuView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMyYouKu;
    private Button btnMyViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void initView() {
        btnMyYouKu = (Button) findViewById(R.id.btn_myyouku);
        btnMyViewPager = (Button) findViewById(R.id.btn_myviewpager);
    }

    private void setListener() {
        btnMyYouKu.setOnClickListener(this);
        btnMyViewPager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_myyouku:
                startActivity(new Intent(this, MyYouKuView.class));
                break;
            case R.id.btn_myviewpager:
                startActivity(new Intent(this, MyViewPagerActivity.class));
                break;
        }
    }
}
