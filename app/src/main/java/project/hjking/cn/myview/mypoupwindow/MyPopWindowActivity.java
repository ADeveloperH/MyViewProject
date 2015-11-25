package project.hjking.cn.myview.mypoupwindow;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.hjking.cn.myview.R;


/**
 * Created by Administrator on 2015/11/25 0025.
 */
public class MyPopWindowActivity extends Activity implements View.OnClickListener {
    private EditText et;
    private ImageView ivArrow;

    private PopupWindow popupWindow;
    private ListView listView;
    private List<String> phoneList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poupwindow_layout);
        assignViews();
        setListener();
        initData();
    }

    private void assignViews() {
        et = (EditText) findViewById(R.id.et);
        ivArrow = (ImageView) findViewById(R.id.iv_arrow);
        listView = new ListView(this);
        listView.setBackgroundResource(R.color.bg_white);

    }

    private void setListener() {
        ivArrow.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et.setText(phoneList.get(position));
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    private void initData() {

        for (int i = 0; i < 50; i++) {
            phoneList.add("1232323233" + i);
        }

        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return phoneList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if(convertView == null){
               convertView = View.inflate(MyPopWindowActivity.this,
                       R.layout.item_listview_popupwindow, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }
            vh.tvmsg.setText(phoneList.get(position));
            vh.ivdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phoneList.remove(position);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }

        public class ViewHolder {
            public final ImageView ivicon;
            public final TextView tvmsg;
            public final ImageView ivdelete;
            public final View root;

            public ViewHolder(View root) {
                ivicon = (ImageView) root.findViewById(R.id.iv_icon);
                tvmsg = (TextView) root.findViewById(R.id.tv_msg);
                ivdelete = (ImageView) root.findViewById(R.id.iv_delete);
                this.root = root;
            }
        }
    }

    //显示PopupWindow
    private void showPoupWindow() {
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(et.getWidth());
        popupWindow.setHeight(600);
        popupWindow.setContentView(listView);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(et);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_arrow:
                if (popupWindow == null || !popupWindow.isShowing()) {
                    Log.i("this", "showPoupWindow");
                    showPoupWindow();
                } else {
                    popupWindow.dismiss();
                    Log.i("this", "dismissPoupWindow");
                }
                break;
        }
    }
}
