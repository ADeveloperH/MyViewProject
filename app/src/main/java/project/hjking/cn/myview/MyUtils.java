package project.hjking.cn.myview;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2015/12/1 0001.
 */
public class MyUtils {
    /**
     * 获取屏幕的宽和高
     * @return
     */
    public static int[] getDisplaySize(Activity act){
        int [] result = new int[2];
        //保存屏幕的分辨率
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        result[0] = mDisplayMetrics.widthPixels;
        result[1] = mDisplayMetrics.heightPixels;
        return result;
    }
}
