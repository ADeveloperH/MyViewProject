package project.hjking.cn.myview.myringview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/11/25 0025.
 */
public class MyRingViewSimple extends View {

    private  float cx;//圆心的x坐标
    private  float cy;//圆心的y坐标
    private  float radius = 0;//圆环的半径

    private Paint paint;//画笔
    private Paint lPaint;

    public MyRingViewSimple(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     *
     * 进行初始化操作
     */
    private void init() {
        radius = 50;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(radius / 4);
        paint.setColor(Color.BLUE);
        paint.setAlpha(255);

        lPaint = new Paint();
        lPaint.setAntiAlias(true);
        lPaint.setStyle(Paint.Style.STROKE);
        lPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(200,200);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            cx = getWidth()/2;
            cy = getWidth()/2;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画一个横竖线交叉的方格
        for (int i = 0; i < getWidth(); i+=20) {
            canvas.drawLine(0,i,getWidth(),i,lPaint);
            canvas.drawLine(i,0,i,getHeight(),lPaint);
        }
        canvas.drawLine(0,getHeight()-1,getWidth(),getHeight()-1,lPaint);
        canvas.drawLine(getWidth()-1,0,getWidth()-1,getHeight(),lPaint);

//        canvas.translate(-20,-20);//在原坐标的基础上移动
        canvas.drawCircle(cx,cy,radius,paint);

    }
}
