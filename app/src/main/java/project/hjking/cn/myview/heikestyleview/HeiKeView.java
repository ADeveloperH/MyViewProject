package project.hjking.cn.myview.heikestyleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/11/26 0026.
 */
public class HeiKeView extends View {
    private char[] counts = new char[]{'A','B','C','D','E','F','G','H','J','K','L','M','N','O'};
    private Paint paint;//画笔
    private int textSize = 20;//字体的大小

    private Context ctx;
    public HeiKeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setTextSize(textSize);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        textSize = getWidth()/10;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    private  float left = 150;
    private  float left_bottom = 200;
    private int seed = 0;
    private int stepCount = 11;
    @Override
    protected void onDraw(Canvas canvas) {
        left = 10;
        left_bottom = 400;
        for (int i = 0; i < 20; i++) {
            int seed_tem = seed;
            int alpha = 255 - (i + seed_tem) * 25;
            paint.setAlpha(alpha);
            canvas.drawText(counts, i % counts.length, 1, left, left_bottom, paint);
            left_bottom = (float) (left_bottom - textSize * 0.6);
        }
        seed = (seed + 1) % stepCount;
        handler.sendEmptyMessageDelayed(seed, 500);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            invalidate();
        }
    };

}
