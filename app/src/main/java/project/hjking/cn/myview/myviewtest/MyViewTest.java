package project.hjking.cn.myview.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/26 0026.
 * 自己写的一个绘画的效果
 */
public class MyViewTest extends View {

    //圆点的集合，这个集合是不断增多的
    private ArrayList<Point> pointsList;
    private Paint paint;//画笔

    public MyViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        pointsList = new ArrayList<Point>();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAlpha(255);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setTextSize(30);
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

        for (int i = 0; i <= 90; i++) {

            for (int j = 0; j <= 90; j++) {
                double r = Math.PI / 45 * i * (1 - Math.sin(Math.PI / 45 * j)) * 20;
                double x = r * Math.cos(Math.PI / 45 * j) * Math.sin(Math.PI / 45 * i) + 320 / 2;
                double y = -r * Math.sin(Math.PI / 45 * j) + 400 / 4;
                x+=getWidth()/4;
                y+=getHeight()/4;
                canvas.drawPoint((float) x, (float) y, paint);
//                canvas.drawText("倩", (float)x, (float)y, paint);
            }
        }

            for (int i = 0; i < pointsList.size(); i++) {
                Point point = pointsList.get(i);
//            canvas.drawPoint(point.x,point.y,paint);
//            canvas.drawCircle(point.x,point.y,20,paint);
                canvas.drawText("倩", point.x, point.y, paint);
                Log.i("onDraw", "x:" + point.x + "  y:" + point.y);
            }
        }


        @Override
        public boolean onTouchEvent (MotionEvent event){
            super.onTouchEvent(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    Point point = new Point(event.getX(), event.getY());
                    if (pointsList.size() >= 2) {
                        Point lastPoint = pointsList.get(pointsList.size() - 1);
                        if (Math.abs(lastPoint.x - point.x) > 15 ||
                                Math.abs(lastPoint.y - point.y) > 15) {
                            pointsList.add(point);
                        }
                    } else {
                        pointsList.add(point);
                    }
                    //刷新页面
                    invalidate();
                    break;
            }
            return true;
        }

        /**
         *
         * 绘制点的类
         */
        private class Point {
            float x;//点的x值
            float y;//点的y值

            public Point() {
            }

            public Point(float x, float y) {
                this.y = y;
                this.x = x;
            }
        }
    }
