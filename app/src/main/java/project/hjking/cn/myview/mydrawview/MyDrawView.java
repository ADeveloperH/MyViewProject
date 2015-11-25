package project.hjking.cn.myview.mydrawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/11/25 0025.
 */

/**
 *
 * 自定义View 的几个重要步骤：
 *
 * 1、测量大小：onMeasure(int widthMeasureSpec, int heightMeasureSpec)
 *
 * 2、指定位置:onLayout(boolean changed, int left, int top, int right, int bottom)
 *
 * 3、绘制内容:onDraw(Canvas canvas)
 *
 *
 *
 */
public class MyDrawView extends View {
    public MyDrawView(Context context) {
        super(context);
    }

    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    /**
     * 测量大小: 该方法的目的就是为了调用setMeasuredDimension方法，告诉系统我的宽和高
     *
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(200,100);
    }

    @Override
    /**
     * 指定位置:告诉系统当的view所在的位置
     * changed：当前view的位置或大小是否改变
     * left/top/right/bottom：当前view在父view中的位置
     */
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    @Override
    /**
     * 绘制自身的内容：
     * canvas: 画布，可以在上边画东西
     *
     */
    protected void onDraw(Canvas canvas) {
        //当前的view全部显示红色
        canvas.drawColor(Color.RED);
        //画笔
        Paint paint = new Paint();
        //画笔的颜色
        paint.setColor(Color.WHITE);
        //指定画笔的的填充样式
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth()/2,getHeight()/2,30,paint);
    }
}
