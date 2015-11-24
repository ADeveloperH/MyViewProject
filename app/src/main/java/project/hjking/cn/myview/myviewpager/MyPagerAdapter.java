package project.hjking.cn.myview.myviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24 0024.
 * ViewPager说明：
 * ViewPager默认会创建当前页面view和屏幕左边和屏幕右边的view
 * 如果超出了三个页面就对调用destoryItem方法来销毁页面
 *
 * 这里是一个无限循环的viewpager
 */
public class MyPagerAdapter extends PagerAdapter {

    private final List<String> dataList;
    private final List viewList;
    

    public MyPagerAdapter(List<String> dataList,List viewList) {
        this.dataList = dataList;
        this.viewList = viewList;
                
    }

    @Override
    public int getCount() {
//        return dataList.size();
        return Integer.MAX_VALUE;
    }

    @Override
    /**
     * 当前的view和object的对应关系
     * view是instantiateItem方法中根据position获得的view对象
     * object是instantiateItem方法的返回值
     *
     */
    public boolean isViewFromObject(View view, Object object) {
        if (view == object) {
            return true;
        }
        return false;
    }

    @Override
    /**
     *
     * 实例化每个页面：
     * container：viewPager本身
     * position：要实例化的页面的下标
     *
     *
     */
    public Object instantiateItem(ViewGroup container, int position) {
        //根据下标返回一个view并将view添加到viewpager中去
//        View view = (View) viewList.get(position);
        View view = (View) viewList.get(position % dataList.size());
        container.addView(view);
        //返回一个和view有关系的对象
        return view;
    }

    @Override
    /**
     * 销毁条目时调用的方法（ViewPager是不存在复用，太多占用过多的内存，所以需要修改）
     * container： 是ViewPager本身
     * position；要销毁页面的下标
     * object：是instantiateItem的返回值，即根据position返回的对象
     *
     */
    public void destroyItem(ViewGroup container, int position, Object object) {

//        super.destroyItem(container, position, object);//这里需要查看super中的方法操作，这里需要将其注掉
        container.removeView((View) object);
//        object = null;//object是否需要置为null要根据具体的逻辑
    }
}
