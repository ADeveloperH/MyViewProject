package project.hjking.cn.myview.myviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.hjking.cn.myview.R;

/**
 * Created by Administrator on 2015/11/24 0024.
 */
public class MyViewPagerActivity extends Activity {
    private ViewPager viewPager;
    private TextView tvDesc;
    private LinearLayout llPointGroup;

    private List<String> dataList;
    private List<ImageView> imageViewList;

    //图片资源ID
    private final int[] imageIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };
    //图片标题集合
    private final String[] imageDescriptions = {
            "巩俐不低俗，我就不能低俗",
            "扑树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送",
            "热血屌丝的反杀"
    };
    private MyPagerAdapter pagerAdapter;

    private int lastPageIndex = 0;//上一次页面的下标，默认为0

    private boolean isAutoChangePage = false;//是否是自动切换页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_layout);
        assignViews();
        initData();

        setAdapter();
        setListener();

        isAutoChangePage = true;
        handler.sendEmptyMessageDelayed(99, 2000);
    }

    private void assignViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
    }

    private void initData() {
        dataList = new ArrayList<String>();
        imageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            dataList.add(imageDescriptions[i]);
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            imageViewList.add(imageView);

            //添加指示点
            ImageView pointImageView = new ImageView(this);
            pointImageView.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                pointImageView.setEnabled(true);
            } else {
                pointImageView.setEnabled(false);
            }
            //创建一个线性布局使用的布局参数
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, -2);
            layoutParams.leftMargin = 10;
            llPointGroup.addView(pointImageView, layoutParams);
        }
        tvDesc.setText(imageDescriptions[0]);
    }

    private void setAdapter() {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(dataList, imageViewList);
        viewPager.setAdapter(pagerAdapter);
        // 切换到一个足够大的数，并且这个数还是imageList.size()的整数倍
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % dataList.size()));
    }

    private void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //当页面滑动时回调此方法
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            //当页面改变时回调此方法
            public void onPageSelected(int position) {
                position = position % dataList.size();

                tvDesc.setText(imageDescriptions[position]);

                //将当前的圆点置为白色，上一个圆点置为灰色
                llPointGroup.getChildAt(position).setEnabled(true);
                llPointGroup.getChildAt(lastPageIndex).setEnabled(false);
                lastPageIndex = position;

            }

            @Override
            //当页面的滑动状态发生改变时回调此方法
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (isAutoChangePage) {
                handler.sendEmptyMessageDelayed(99, 2000);
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isAutoChangePage) {
            isAutoChangePage = false;
        }
    }
}
