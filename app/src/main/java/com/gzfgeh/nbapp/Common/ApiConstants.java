package com.gzfgeh.nbapp.Common;

/**
 * Description:
 * Created by guzhenfu on 2016/10/31 17:36.
 */

public class ApiConstants {

    public static final String JU_HE_NEWS_HOST = "http://v.juhe.cn";

    public static final String KUKA_HOST = "http://kaku.com";

    public static final String GANK_IO_HOST = "http://gank.io/api/";

    public static final String JU_HE_KEY = "b190fe33fd81b3fd99a021767dd2bdec";

    public static final String JU_HE_STYLE = "toutiao";


    public static final String GANDK_IO_ANDROID = "Android";
    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.JU_HE_NEWS:
                host = JU_HE_NEWS_HOST;
                break;
            case HostType.GANK_IO_DATA:
                host = GANK_IO_HOST;
                break;
            case HostType.NEWS_DETAIL_HTML_PHOTO:
                host = KUKA_HOST;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }
}
