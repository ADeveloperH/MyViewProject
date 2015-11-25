package project.hjking.cn.myview.myringview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/25 0025.
 */
public class MyRingWave extends View {

    private int [] colors = new int[]{Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN};

    private  final  static int MIN_DIS = 13;//两个相邻圆环中心点的最小距离

    private  boolean isRunning = false;

    private ArrayList<Wave> waveList;
    public MyRingWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        waveList = new ArrayList<Wave>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < waveList.size(); i++) {
            Wave wave = waveList.get(i);
            canvas.drawCircle(wave.x,wave.y,wave.radius,wave.paint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY(); 
                addPoint(x,y);
                break;
        }

        return true;

    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //刷新数据
            flushData();
            //刷新页面
            invalidate();
            //循环动画
            if (isRunning) {
                handler.sendEmptyMessageDelayed(88, 50);
            }
        }
    };

    /**
     * 刷新数据
     */
    private void flushData() {
        for (int i = 0; i < waveList.size(); i++) {

            Wave w = waveList.get(i);

            //如果透明度为 0 从集合中删除
            int alpha = w.paint.getAlpha();
            if(alpha == 0){
                waveList.remove(i);	//删除i 以后，i的值应该再减1 否则会漏掉一个对象，不过，在此处影响不大，效果上看不出来。
                continue;
            }
            alpha-=5;
            if(alpha<5){
                alpha =0;
            }
            //降低透明度
            w.paint.setAlpha(alpha);

            //扩大半径
            w.radius = w.radius+3;
            //设置半径厚度
            w.paint.setStrokeWidth(w.radius/3);
        }

		/*
		 * 如果集合被清空，就停止刷新动画
		 */
        if(waveList.size() == 0){
            isRunning = false;
        }
    }

    /**
     * 添加新的波浪中心点
     * @param x
     * @param y
     */
    private void addPoint(int x, int y) {
        if (waveList.size() == 0) {
            addPoint2List(x,y);
            //第一次启动动画
            isRunning = true;
            handler.sendEmptyMessage(88);
        } else {
            Wave w = waveList.get(waveList.size()-1);
            if(Math.abs(w.x - x)>MIN_DIS || Math.abs(w.y-y)>MIN_DIS){
                addPoint2List(x,y);
            }
        }
    }

    /**
     * 添加波浪到集合中去
     * @param x
     * @param y
     */
    private void addPoint2List(int x, int y) {
        Wave w = new Wave();
        w.x = x;
        w.y=y;
        Paint pa=new Paint();
        pa.setColor(colors[(int)(Math.random()*4)]);
        pa.setAntiAlias(true);
        pa.setStyle(Paint.Style.STROKE);
        w.paint = pa;
        waveList.add(w);
    }

    /**
     *
     * 定义一个圆环类
     */
    private  class Wave{
        //圆心
        int x;
        int y;

        //半径
        int radius;

        //画笔
        Paint paint;
    }
}
