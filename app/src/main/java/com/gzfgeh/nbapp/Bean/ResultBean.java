package com.gzfgeh.nbapp.Bean;

import java.util.List;

/**
 * Description:
 * Created by guzhenfu on 2016/11/1 15:55.
 */

public class ResultBean {
    private String stat;
    /**
     * title : 东方八卦:刘翔女友吴莎微博手撕前妻葛天，小甜甜布兰妮遭父亲软禁8年
     * date : 2016-11-01 10:55
     * author_name : 东方头条
     * thumbnail_pic_s : http://05.imgmini.eastday.com/mobile/20161101/20161101105537_02b0ea4d01f14c9b59e51e9517308dd8_1_mwpm_03200403.jpg
     * thumbnail_pic_s02 : http://05.imgmini.eastday.com/mobile/20161101/20161101105537_02b0ea4d01f14c9b59e51e9517308dd8_1_mwpl_05500201.jpg
     * thumbnail_pic_s03 : http://05.imgmini.eastday.com/mobile/20161101/20161101105537_02b0ea4d01f14c9b59e51e9517308dd8_1_mwpl_05500201.jpg
     * url : http://mini.eastday.com/mobile/161101105537097.html?qid=juheshuju
     * uniquekey : 161101105537097
     * type : 头条
     * realtype : 娱乐
     */

    private List<DataBean> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
}
