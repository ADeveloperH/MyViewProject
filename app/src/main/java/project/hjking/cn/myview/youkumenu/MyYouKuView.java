package project.hjking.cn.myview.youkumenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import project.hjking.cn.myview.R;

/**
 * Created by Administrator on 2015/11/24 0024.
 */
public class MyYouKuView extends Activity implements View.OnClickListener {
    private final String TAG = "MyYouKuView";
    private RelativeLayout level1;
    private ImageView channel1;
    private ImageView iconMyyouku;
    private ImageView channel7;
    private RelativeLayout level2;
    private ImageView iconMenu;
    private RelativeLayout level3;
    private ImageView iconHome;
    //判断三级菜单是否显示，默认是显示的。如果显示就隐藏，如果隐藏就显示
    private boolean isLevel3Show = true;
    //判断二级菜单是否显示，默认是显示的。如果显示就隐藏，如果隐藏就显示
    private boolean isLevel2Show = true;
    //判断一级菜单是否显示，默认是显示的。如果显示就隐藏，如果隐藏就显示
    private boolean isLevel1Show = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youkumenu_layout);

        assignViews();
        setListener();
    }


    /**
     * 设置监听
     */
    private void setListener() {
        iconHome.setOnClickListener(this);
        iconMenu.setOnClickListener(this);
    }


    private void assignViews() {
        level1 = (RelativeLayout) findViewById(R.id.level1);
        channel1 = (ImageView) findViewById(R.id.channel1);
        iconMyyouku = (ImageView) findViewById(R.id.icon_myyouku);
        channel7 = (ImageView) findViewById(R.id.channel7);
        level2 = (RelativeLayout) findViewById(R.id.level2);
        iconMenu = (ImageView) findViewById(R.id.icon_menu);
        level3 = (RelativeLayout) findViewById(R.id.level3);
        iconHome = (ImageView) findViewById(R.id.icon_home);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_home://点击的是home按键
                //判断二级菜单是否显示
                if(isLevel2Show){
                    //如果二级菜单显示。就隐藏二级菜单，并判断三级菜单是否显示，如果三级的也显示同样隐藏
                    MyAnimationUtil.hideView(level2,200);
                    if(isLevel3Show){
                        //如果当前是三级菜单也是显示的就都隐藏
                        MyAnimationUtil.hideView(level3);
                        isLevel3Show = !isLevel3Show;
                    }
                }else{
                    //二级菜单隐藏.点击后就显示二级
                    MyAnimationUtil.showView(level2);
                }
                isLevel2Show = !isLevel2Show;
                break;
            case R.id.icon_menu://点击的是menu按键
                //判断三级菜单是否显示
                if(isLevel3Show){
                    //如果当前是显示的
                    MyAnimationUtil.hideView(level3);
                }else {
                    //如果当前是隐藏的
                    MyAnimationUtil.showView(level3);
                }
                isLevel3Show = !isLevel3Show;
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户点击的是否是menu按钮
        if(keyCode == KeyEvent.KEYCODE_MENU) {
            //判断一级菜单是否显示
            if(isLevel1Show){
                //如果一级菜单显示就隐藏一级菜单，同时，判断二三级菜单的状态并隐藏
                MyAnimationUtil.hideView(level1,400);
                isLevel1Show = false;

                if (isLevel2Show) {
                    MyAnimationUtil.hideView(level2,200);
                    isLevel2Show = false;
                    if (isLevel3Show) {
                        MyAnimationUtil.hideView(level3);
                        isLevel3Show = false;
                    }
                }
            }else {
                //如果一级菜单当前状态为隐藏就显示一级和二级菜单
                if (!isLevel1Show) {
                    MyAnimationUtil.showView(level1);
                    isLevel1Show = true;
                }
                if (!isLevel2Show) {
                    MyAnimationUtil.showView(level2, 200);
                    isLevel2Show = true;
                }
            }
            return true;//表示不用系统来处理，自己处理这个点击事件
        }
        return super.onKeyDown(keyCode, event);
    }
}
