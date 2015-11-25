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

/**
 * Created by Administrator on 2015/11/25 0025.
 */
public class MyRingView extends View {

    private  float cx;//圆心的x坐标
    private  float cy;//圆心的y坐标
    private  int radius;//圆环的半径
    private Paint paint;//画笔
    private  boolean isRunning = false;
    public MyRingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        isRunning = false;
        radius = 0;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(radius / 4);
        paint.setColor(Color.BLUE);
        paint.setAlpha(255);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            cx = getWidth()/2;
            cy = getHeight()/2;
        }
    }

    @Override
    /**
     * onAttachedToWindow是在第一次onDraw前调用的。
     * 也就是我们写的View在没有绘制出来时调用的，但只会调用一次。
     *
     */
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        startAni();
    }

    @Override
    /**
     * 我们销毁View的时候。我们写的这个View不再显示。
     * 销毁时我们要停止发handler。
     */
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRunning = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //当按下时
            cx = event.getX();
            cy = event.getY();
            init();
            startAni();
        }
        return  true;

    }

    //开始执行动画
    private void startAni() {
        isRunning = true;
        handler.sendEmptyMessageDelayed(88, 50);

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // 设置透明度
            int alpha = paint.getAlpha();
            if (alpha == 0) {
                isRunning = false;
            }
            alpha = Math.max(0, alpha-10);
            paint.setAlpha(alpha);
            // 设置半径
            radius += 5;
            paint.setStrokeWidth(radius / 3);
            invalidate();
            if (isRunning) {
                handler.sendEmptyMessageDelayed(0, 50);
            }

        }
    };


}
