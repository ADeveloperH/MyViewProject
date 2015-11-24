package project.hjking.cn.myview.youkumenu;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2015/11/24 0024.
 *
 *
 * 注意：Animation动画是一个假象，自身的图片位置没有变化，只是让一个图片做了动画。实际上原图片还在原来的位置
 */
public class MyAnimationUtil {

    /**
     * 立即隐藏指定的View
     * 做旋转动画.从0-180
     * 旋转的圆心相对于自身，在中心处，原点在左上角，因此x轴方向为0.5，y轴方向为1
     *
     * @param view
     */
    public static void hideView(RelativeLayout view) {
        hideView(view, 0);
    }

    /**
     * 显示指定的View
     * 做旋转动画.从180-360
     * 旋转的圆心相对于自身，在中心处，原点在左上角，因此x轴方向为0.5，y轴方向为1
     *
     * @param view
     */
    public static void showView(RelativeLayout view) {
        showView(view, 0);
    }

    /**
     * 隐藏指定的View
     * 做旋转动画.从0-180
     * 旋转的圆心相对于自身，在中心处，原点在左上角，因此x轴方向为0.5，y轴方向为1
     *
     * @param view   要执行动画 view
     * @param offset 延迟执行动画的时间
     */
    public static void hideView(RelativeLayout view, int offset) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1f);
        //设置执行动画的时间
        rotateAnimation.setDuration(600);
        //设置动画完成后，保持完成的状态
        rotateAnimation.setFillAfter(true);
        //当view。startAnimation方法执行后延迟offset时间执行
        rotateAnimation.setStartOffset(offset);
        //开始执行动画
        view.startAnimation(rotateAnimation);


        //解决看不见但是还能点击的bug.遍历当前的ViewGroup将所有的子控件都设置为不可用
//        view.getChildCount();//获取ViewGroup中子View的个数
//        view.getChildAt(i);//获取ViewGroup中指定的子view
        for (int i = 0; i < view.getChildCount(); i++) {
            view.getChildAt(i).setEnabled(false);
        }
    }

    /**
     * 显示指定的View
     * 做旋转动画.从180-360
     * 旋转的圆心相对于自身，在中心处，原点在左上角，因此x轴方向为0.5，y轴方向为1
     *
     * @param view
     * @param offset
     */
    public static void showView(RelativeLayout view, int offset) {

        RotateAnimation rotateAnimation = new RotateAnimation(180, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1f);

        rotateAnimation.setDuration(600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setStartOffset(offset);
        view.startAnimation(rotateAnimation);

        //解决看不见但是还能点击的bug.遍历当前的ViewGroup将所有的子控件都设置为可用
//        view.getChildCount();//获取ViewGroup中子View的个数
//        view.getChildAt(i);//获取ViewGroup中指定的子view
        for (int i = 0; i < view.getChildCount(); i++) {
            view.getChildAt(i).setEnabled(true);
        }
    }
}
