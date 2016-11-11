package com.gzfgeh.nbapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzfgeh.nbapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my, container, false);
        String html = "<p><img alt=\\\"\\\" src=\\\"http://ww4.sinaimg.cn/large/610dc034jw1f9mp3xhjdhj20u00u0ta9.jpg\\\" style=\\\"height:560px; width:560px\\\" /></p>\\r\\n\\r\\n<h3>iOS</h3>\\r\\n\\r\\n<ul>\\r\\n\\t<li><a href=\\\"http://www.welkinx.com/2016/08/14/11/\\\" target=\\\"_blank\\\">\\u4e3a\\u89c6\\u56fe\\u6dfb\\u52a0\\u4e1d\\u6ed1\\u7684\\u6c34\\u6ce2\\u7eb9</a>&nbsp;(Welkin Xie)\\r\\n\\r\\n\\t<ul>\\r\\n\\t\\t<li><a href=\\\"http://www.welkinx.com/2016/08/14/11/\\\" target=\\\"_blank\\\"><img src=\\\"http://img.gank.io/a18f9509-dc3d-4fd3-ac43-7fe1087300c5\\\" title=\\\"\\u4e3a\\u89c6\\u56fe\\u6dfb\\u52a0\\u4e1d\\u6ed1\\u7684\\u6c34\\u6ce2\\u7eb9\\\" /></a></li>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n\\t<li><a href=\\\"https://github.com/wutongke/react-native-one\\\" target=\\\"_blank\\\">\\u4f7f\\u7528react native\\u5f00\\u53d1\\u7684\\u4e00\\u4e2aone\\u5ba2\\u6237\\u7aef</a>&nbsp;(\\u68a7\\u6850)\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n</ul>\\r\\n\\r\\n<h3>Android</h3>\\r\\n\\r\\n<ul>\\r\\n\\t<li><a href=\\\"https://github.com/mogujie/ThinRPlugin\\\" target=\\\"_blank\\\">apk \\u7626\\u8eab\\u4e4b\\u53bb\\u9664 R.class</a>&nbsp;(\\u848b\\u670b)\\r\\n\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n\\t<li><a href=\\\"https://github.com/skyhacker2/SQLiteOnWeb-Android\\\" target=\\\"_blank\\\">\\u901a\\u8fc7\\u6d4f\\u89c8\\u5668\\u7ba1\\u7406sqlite\\u6570\\u636e\\u5e93</a>&nbsp;(pcyan)\\r\\n\\t<ul>\\r\\n\\t\\t<li><a href=\\\"https://github.com/skyhacker2/SQLiteOnWeb-Android\\\" target=\\\"_blank\\\"><img src=\\\"http://img.gank.io/fb674796-3ebb-4684-9a0c-4d18097eb771\\\" title=\\\"\\u901a\\u8fc7\\u6d4f\\u89c8\\u5668\\u7ba1\\u7406sqlite\\u6570\\u636e\\u5e93\\\" /></a></li>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n\\t<li><a href=\\\"https://github.com/burgessjp/GetWordTextView\\\" target=\\\"_blank\\\">\\u4e00\\u4e2a\\u652f\\u6301\\u9009\\u8bcd\\u7684 TextView\\uff0c\\u7c7b\\u4f3c\\u4e0e\\u5355\\u8bcd app \\u4e2d\\u70b9\\u51fb\\u5355\\u8bcd\\u7ffb\\u8bd1\\u7684\\u6548\\u679c</a>&nbsp;(solid)\\r\\n\\t<ul>\\r\\n\\t\\t<li><a href=\\\"https://github.com/burgessjp/GetWordTextView\\\" target=\\\"_blank\\\"><img src=\\\"http://img.gank.io/cfc82bd9-731f-4ac9-80db-67254f15baa1\\\" title=\\\"\\u4e00\\u4e2a\\u652f\\u6301\\u9009\\u8bcd\\u7684 TextView\\uff0c\\u7c7b\\u4f3c\\u4e0e\\u5355\\u8bcd app \\u4e2d\\u70b9\\u51fb\\u5355\\u8bcd\\u7ffb\\u8bd1\\u7684\\u6548\\u679c\\\" /></a></li>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n\\t<li><a href=\\\"https://zhuanlan.zhihu.com/p/23442027\\\" target=\\\"_blank\\\">\\u4e00\\u89e6\\u5373\\u53d1 App\\u542f\\u52a8\\u4f18\\u5316\\u6700\\u4f73\\u5b9e\\u8df5</a>&nbsp;(LHF)\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n\\t<li><a href=\\\"http://blog.csdn.net/yanbober/article/details/53071792\\\" target=\\\"_blank\\\">React Native Android \\u4ece\\u5b66\\u8f66\\u5230\\u8865\\u80ce\\u548c\\u6210\\u529f\\u53d1\\u8f66\\u7ecf\\u5386</a>&nbsp;(wuzheng)\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n</ul>\\r\\n\\r\\n<h3>\\u524d\\u7aef</h3>\\r\\n\\r\\n<ul>\\r\\n\\t<li><a href=\\\"https://github.com/WhitestormJS/whitestorm.js\\\" target=\\\"_blank\\\">js 3d \\u5f00\\u53d1\\u5f15\\u64ce</a>&nbsp;(\\u4ee3\\u7801\\u5bb6)\\r\\n\\r\\n\\t<ul>\\r\\n\\t\\t<li><a href=\\\"https://github.com/WhitestormJS/whitestorm.js\\\" target=\\\"_blank\\\"><img src=\\\"http://img.gank.io/89e919e4-68cd-4255-a36e-e7b389169be2\\\" title=\\\"js 3d \\u5f00\\u53d1\\u5f15\\u64ce\\\" /></a></li>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n</ul>\\r\\n\\r\\n<h3>\\u62d3\\u5c55\\u8d44\\u6e90</h3>\\r\\n\\r\\n<ul>\\r\\n\\t<li><a href=\\\"http://www.getmarkman.com/\\\" target=\\\"_blank\\\">\\u9ad8\\u6548\\u7684\\u8bbe\\u8ba1\\u7a3f\\u6807\\u6ce8\\u3001\\u6d4b\\u91cf\\u5de5\\u5177</a>&nbsp;(liuzheng)\\r\\n\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n\\t<li><a href=\\\"https://github.com/cameronfabbri/Colorful-Image-Colorization\\\" target=\\\"_blank\\\">\\u5229\\u7528\\u6df1\\u5ea6\\u5b66\\u4e60\\u65b9\\u6cd5\\u7ed9\\u9ed1\\u767d\\u56fe\\u7247\\u56fe\\u7247\\u4e0a\\u8272</a>&nbsp;(\\u4ee3\\u7801\\u5bb6)\\r\\n\\t<ul>\\r\\n\\t\\t<li><a href=\\\"https://github.com/cameronfabbri/Colorful-Image-Colorization\\\" target=\\\"_blank\\\"><img src=\\\"http://img.gank.io/4c38c84a-8280-4697-b8b4-ae2c3715b7eb\\\" title=\\\"\\u5229\\u7528\\u6df1\\u5ea6\\u5b66\\u4e60\\u65b9\\u6cd5\\u7ed9\\u9ed1\\u767d\\u56fe\\u7247\\u56fe\\u7247\\u4e0a\\u8272\\\" /></a></li>\\r\\n\\t\\t<li><a href=\\\"https://github.com/cameronfabbri/Colorful-Image-Colorization\\\" target=\\\"_blank\\\"><img src=\\\"http://img.gank.io/c93f7216-fa7e-4c14-bbf9-60257a1313a7\\\" title=\\\"\\u5229\\u7528\\u6df1\\u5ea6\\u5b66\\u4e60\\u65b9\\u6cd5\\u7ed9\\u9ed1\\u767d\\u56fe\\u7247\\u56fe\\u7247\\u4e0a\\u8272\\\" /></a></li>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n</ul>\\r\\n\\r\\n<h3>\\u4f11\\u606f\\u89c6\\u9891</h3>\\r\\n\\r\\n<ul>\\r\\n\\t<li><a href=\\\"http://weibo.com/tv/v/EgMsAbOaI?fid=1034:037eab745ddcbc30e436e78cdae67900\\\" target=\\\"_blank\\\">\\u65b0\\u9c9c\\u7684\\u56db\\u516d\\u7ea7\\u8d44\\u6599\\u5df2\\u5230\\u8d27\\uff1a\\u7279\\u6717\\u666e\\u80dc\\u9009\\u6f14\\u8bb2\\u53cc\\u8bed\\u5b57\\u5e55\\u7248&nbsp;</a>(lxxself)\\r\\n\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n</ul>\\r\\n\\r\\n<p><iframe frameborder=\\\"0\\\" height=\\\"498\\\" src=\\\"http://player.youku.com/embed/XMTY2NTkzNTQwNA==\\\" width=\\\"510\\\"></iframe></p>\\r\\n\\r\\n<p>\\u611f\\u8c22\\u6240\\u6709\\u9ed8\\u9ed8\\u4ed8\\u51fa\\u7684\\u7f16\\u8f91\\u4eec\\uff0c\\u613f\\u5927\\u5bb6\\u6709\\u7f8e\\u597d\\u4e00\\u5929\\u3002</p>";
        TextView textView = (TextView) view.findViewById(R.id.tv);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setText(Html.fromHtml(html));
        return view;
    }

}
