package com.gzfgeh.nbapp.Bean;

public class BaseBean<T>{
    /**
     * error : false
     * results : [{"_id":"5819912d421aa9137697460f","createdAt":"2016-11-02T15:09:33.261Z","desc":"【腾讯Bugly干货分享】Android ListView 与 RecyclerView 对比浅析\u2014缓存机制","publishedAt":"2016-11-04T11:48:56.654Z","source":"chrome","type":"Android","url":"https://zhuanlan.zhihu.com/p/23339185","used":true,"who":"LHF"},{"_id":"581ab6ff421aa90e799ec266","createdAt":"2016-11-03T12:03:11.967Z","desc":"一个能够让 View 执行漂亮的漂浮动画的库","images":["http://img.gank.io/b9456ed7-4b83-40a5-b5bd-f62d5483c338"],"publishedAt":"2016-11-04T11:48:56.654Z","source":"web","type":"Android","url":"https://github.com/UFreedom/FloatingView","used":true,"who":"UFreedom"},{"_id":"581aea95421aa91369f959df","createdAt":"2016-11-03T15:43:17.241Z","desc":"Android 仿业问 打字机效果","images":["http://img.gank.io/7b5a0968-e736-4f17-9273-9c7e3e55c76c"],"publishedAt":"2016-11-04T11:48:56.654Z","source":"web","type":"Android","url":"https://github.com/andyxialm/TyperEditText","used":true,"who":null},{"_id":"581b9e10421aa90e6f21b4b2","createdAt":"2016-11-04T04:29:04.61Z","desc":"【MotionEvent详解】目前最完整的一篇Android事件类型文章，真心不骗你。","publishedAt":"2016-11-04T11:48:56.654Z","source":"web","type":"Android","url":"http://www.gcssloop.com/customview/motionevent","used":true,"who":"sloop"},{"_id":"581bd70e421aa91376974629","createdAt":"2016-11-04T08:32:14.130Z","desc":"microG 计划：从头打造 Google 用户服务和依赖库。很有意义的一个是打造了 Gms，没准国内很快可以实现 Gms 推送。","publishedAt":"2016-11-04T11:48:56.654Z","source":"chrome","type":"Android","url":"https://microg.org/","used":true,"who":"代码家"},{"_id":"581bf09c421aa90e6f21b4b7","createdAt":"2016-11-04T10:21:16.686Z","desc":"简单易用的可定制展开角度的button menu","images":["http://img.gank.io/2b9c4b48-ad83-4238-bcee-16ae850a4eae"],"publishedAt":"2016-11-04T11:48:56.654Z","source":"web","type":"Android","url":"https://github.com/uin3566/AllAngleExpandableButton","used":true,"who":"Fang.Xu"},{"_id":"5816e3e0421aa90e6f21b489","createdAt":"2016-10-31T14:25:36.974Z","desc":"dex文件结构解析以及其应用","publishedAt":"2016-11-03T11:48:43.342Z","source":"web","type":"Android","url":"http://www.zjutkz.net/2016/10/27/dex%E6%96%87%E4%BB%B6%E7%BB%93%E6%9E%84%E5%8F%8A%E5%85%B6%E5%BA%94%E7%94%A8/","used":true,"who":null},{"_id":"581aa212421aa91376974619","createdAt":"2016-11-03T10:33:54.162Z","desc":"Android 方块儿展开菜单，很有新意。","images":["http://img.gank.io/c265503d-4cdb-4f1b-8998-008b20e01f9c"],"publishedAt":"2016-11-03T11:48:43.342Z","source":"chrome","type":"Android","url":"https://github.com/devsideal/SquareMenu","used":true,"who":"代码家"},{"_id":"581ab1b3421aa9137697461d","createdAt":"2016-11-03T11:40:35.971Z","desc":"Android root helper 工具库","publishedAt":"2016-11-03T11:48:43.342Z","source":"chrome","type":"Android","url":"https://github.com/AndroidDeveloperLB/RootHelper/blob/master/lib/src/main/java/com/lb/root_helper/lib/Root.java","used":true,"who":"代码家"},{"_id":"581ab1df421aa91369f959dc","createdAt":"2016-11-03T11:41:19.531Z","desc":"通过 elevation 阴影来创造更逼真的按下效果","images":["http://img.gank.io/d3560626-2de5-4061-976b-f0b1a9dfeda6"],"publishedAt":"2016-11-03T11:48:43.342Z","source":"chrome","type":"Android","url":"https://github.com/rubensousa/RaiflatButton","used":true,"who":"代码家"}]
     */

    private boolean error;
    /**
     * _id : 5819912d421aa9137697460f
     * createdAt : 2016-11-02T15:09:33.261Z
     * desc : 【腾讯Bugly干货分享】Android ListView 与 RecyclerView 对比浅析—缓存机制
     * publishedAt : 2016-11-04T11:48:56.654Z
     * source : chrome
     * type : Android
     * url : https://zhuanlan.zhihu.com/p/23339185
     * used : true
     * who : LHF
     */

    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }





}