package com.gzfgeh.nbapp.Bean;

import com.gzfgeh.nbapp.Model.BaseModel;

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

    public static class DataBean {
        private String title;
        private String date;
        private String author_name;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String thumbnail_pic_s03;
        private String url;
        private String uniquekey;
        private String type;
        private String realtype;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }

        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }

        public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
        }

        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }

        public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUniquekey() {
            return uniquekey;
        }

        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRealtype() {
            return realtype;
        }

        public void setRealtype(String realtype) {
            this.realtype = realtype;
        }
    }
}
