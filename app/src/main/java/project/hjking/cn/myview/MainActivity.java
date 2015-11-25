package project.hjking.cn.myview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import project.hjking.cn.myview.mydrawview.MyDrawViewAcitivity;
import project.hjking.cn.myview.mypoupwindow.MyPopWindowActivity;
import project.hjking.cn.myview.myringview.MyRingViewActivity;
import project.hjking.cn.myview.mytogglebtn.MyToggleBtnActivity;
import project.hjking.cn.myview.myviewpager.MyViewPagerActivity;
import project.hjking.cn.myview.youkumenu.MyYouKuView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMyYouKu;
    private Button btnMyViewPager;
    private Button btnPoupWindow;
    private Button btnMyDrawView;
    private Button btnMyToggleBtn;
    private Button btnMyRingView;

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
        btnPoupWindow = (Button) findViewById(R.id.btn_mypoupwindow);
        btnMyDrawView = (Button) findViewById(R.id.btn_mydrawview);
        btnMyToggleBtn = (Button) findViewById(R.id.btn_mytogglebtn);
        btnMyRingView = (Button) findViewById(R.id.btn_myringview);
    }

    private void setListener() {
        btnMyYouKu.setOnClickListener(this);
        btnMyViewPager.setOnClickListener(this);
        btnPoupWindow.setOnClickListener(this);
        btnMyDrawView.setOnClickListener(this);
        btnMyToggleBtn.setOnClickListener(this);
        btnMyRingView.setOnClickListener(this);
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
            case R.id.btn_mypoupwindow:
                startActivity(new Intent(this, MyPopWindowActivity.class));
                break;
            case R.id.btn_mydrawview:
                startActivity(new Intent(this, MyDrawViewAcitivity.class));
                break;
            case R.id.btn_mytogglebtn:
                startActivity(new Intent(this, MyToggleBtnActivity.class));
                break;
            case R.id.btn_myringview:
                startActivity(new Intent(this, MyRingViewActivity.class));
                break;
        }
    }
}
