package project.hjking.cn.myview.mytogglebtn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import project.hjking.cn.myview.R;

/**
 * Created by Administrator on 2015/11/25 0025.
 */
public class MyToggleBtn extends View implements View.OnClickListener {

    private Bitmap bgBitmap;
    private Bitmap slidBitmap;
    private Paint paint;

    //当前按钮的开关状态，true:开  false：关
    private  boolean curState = false;
    //当前是否是滑动事件
    private  boolean isSliding = false;
    //距离左边距的距离
    private int slidLeft = 0;
    //能够滑动的最大的左边距
    private int slidLeftMax;

    /**
     * 这个构造函数是在xml布局文件中使用该view时会调用这个构造方法
     *
     * @param context
     * @param attrs
     */
    public MyToggleBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     *
     * 进行相关的初始化
     */
    private void init() {
        bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        slidBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
        paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);

        slidLeftMax = bgBitmap.getWidth() - slidBitmap.getWidth();
        //设置点击事件
        setOnClickListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //指定当前view 的宽高，和背景图一致
        setMeasuredDimension(bgBitmap.getWidth(), bgBitmap.getHeight());
    }


    @Override
    //绘制
    protected void onDraw(Canvas canvas) {
        //绘制背景图
        canvas.drawBitmap(bgBitmap,0,0,paint);
        //绘制滑动图片
        canvas.drawBitmap(slidBitmap, slidLeft, 0, paint);


    }

    @Override
    /**
     * 切换开关的状态
     * 当前为开切换为关
     * 当前状态为关切换为开
     *
     */
    public void onClick(View v) {
        if (!isSliding) {
            //设置当前的状态
            curState = !curState;
            flushState();
        }
    }

    private  int lastX;//上一个触摸事件中的x值

    private  int downX;//发生down事件时的x值

    @Override
    /**
     * 处理触摸事件
     * 如果该view消费了该事件，需要返回true
     *
     */
    public boolean onTouchEvent(MotionEvent event) {
        //注销super.onTouchEvent(event);后点击事件也会失效。因为所有的触摸事件都是在这里处理的

        /**
         *
         * 这里点击事件和滑动的事件会产生冲突
         * 解决方法：
         * 设置一个标志位，当移动的距离超过10个像素就表明当前是滑动事件，就不在点击事件中处理相关的逻辑
         * 否则就是点击事件，用点击事件来处理相关的逻辑
         *
         *
         */
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
               downX = lastX = (int) event.getX();//获取相对于当前view的x坐标
                //按下的事件不是滑动事件
                isSliding = false;
                break;
            case MotionEvent.ACTION_MOVE:
                //求得移动的距离
                int disX = (int) (event.getX() - lastX);
                //改变按钮的左边距
                slidLeft += disX;
                //刷新页面，会重新绘制图片
                flushView();

                //为lastX重新赋值
                lastX = (int) event.getX();

                break;
            case MotionEvent.ACTION_UP:

                if(Math.abs(event.getX() - downX) >= 10){
                    //当前是滑动事件
                    isSliding = true;
                }

                if(isSliding){
                    //是滑动事件才处理滑动事件的逻辑.如果不是滑动事件就由点击事件处理相关逻辑
                    //如果slidLeft超过最大值的一半就切换为开，否则切换为关
                    if (slidLeft >= slidLeftMax / 2) {
                        curState = true;
                    } else {
                        curState = false;
                    }
                    flushState();
                }
                break;
        }

        return true;
    }

    //刷新view
    private void flushView() {
        if (slidLeft < 0) {
            slidLeft = 0;
        }
        if(slidLeft > slidLeftMax){
            slidLeft = slidLeftMax;
        }
        invalidate();
    }

    //根据curState刷新开关状态
    private void flushState() {
        if (curState) {
            //当前是开状态，切换为开
            slidLeft = slidLeftMax;
        } else {
            //当前是关状态，切换为关
            slidLeft = 0;
        }
        //刷新视图.如果在UI线程就使用invalidate方法来刷新当前的view，如果是在子线程需要使用postInvalidate方法
//        invalidate();
        flushView();
    }
}
