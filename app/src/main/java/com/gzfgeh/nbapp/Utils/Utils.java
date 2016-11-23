package com.gzfgeh.nbapp.Utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gzfgeh.nbapp.APP;
import com.gzfgeh.nbapp.Activity.MainActivity;

import net.youmi.android.AdManager;
import net.youmi.android.normal.spot.SplashViewSettings;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Created by guzhenfu on 2016/2/26 16:32.
 */
public class Utils {

    private Utils(){}

    private static final float DENSITY = Resources.getSystem().getDisplayMetrics().density;
    private static final Canvas sCanvas = new Canvas();

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2Px(int dp) {
        return Math.round(dp * DENSITY);
    }

    public static int dipToPx(Context c, float dipValue) {
        DisplayMetrics metrics = c.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static final int getHeightInPx(Context context) {
        final int height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    public static final int getWidthInPx(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static String getVersionCode() {
        String versionCode = null;
        try {
            versionCode = APP
                    .getContext()
                    .getPackageManager()
                    .getPackageInfo(APP.getContext().getPackageName(),
                            0).versionName;
        } catch (PackageManager.NameNotFoundException ex) {

        }
        return versionCode;
    }

    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static Bitmap createBitmapFromView(View view) {
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
        }
        view.clearFocus();
        Bitmap bitmap = createBitmapSafely(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888, 1);
        if (bitmap != null) {
            synchronized (sCanvas) {
                Canvas canvas = sCanvas;
                canvas.setBitmap(bitmap);
                view.draw(canvas);
                canvas.setBitmap(null);
            }
        }
        return bitmap;
    }

    public static Bitmap createBitmapSafely(int width, int height, Bitmap.Config config, int retryCount) {
        try {
            return Bitmap.createBitmap(width, height, config);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (retryCount > 0) {
                System.gc();
                return createBitmapSafely(width, height, config, retryCount - 1);
            }
            return null;
        }
    }

    /**
     * 判断集合是否为空
     * @param list 待判断的集合
     * @return 是否为空
     */
    public static boolean isEmpty(List list){
        if(null == list || list.isEmpty()){
            return true;
        }
        return false;
    }

    public static String getFormatDate(){
        long current = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(current);
        return format.format(date);
    }

    public static int getMaxValue(int a){
        int temp = 1;
        while(a > temp){
            temp *= 10;
        }
        return temp;
    }

    public static int getMinValue(int a){
        int temp = a;
        while(temp > 10){
            temp /= 10;
        }
        while (temp < a){
            temp *= 10;
        }
        return temp/10;
    }

    public static String getBeforeMonth(int monthTime){
        Date date = new Date();
        new SimpleDateFormat("yyyy-MM-dd").format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - monthTime);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * bitmap 转 File
     * @param bitmap
     * @return
     */
    public static File getImageFile(Bitmap bitmap) {
        String fileName = "/NBApp/photo/" + bitmap.hashCode() + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory(), fileName); // getFilesDir()等只能在程序内部访问不能用作分享路径
        if (!file.getParentFile().exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 分享单张图片
     */
    public static void sharePic(Context context, Uri uri){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/jpeg");
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }

    /**
     * 分享纯文本
     */
    public static void shareText(Context context, String text){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }

    /**
     * APP市场评价
     */
    public static void goToMarket(Context context){
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Couldn't launch the market !", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 系统邮件发送
     */
    public static void sendContent(Context context, String content){
        Intent data=new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:gzfgeh@qq.com"));
        data.putExtra(Intent.EXTRA_SUBJECT, "APP反馈");
        data.putExtra(Intent.EXTRA_TEXT, content);
        context.startActivity(data);
    }

    /**
     * 有米广告
     */
    public static void inAdvert(Context context, ViewGroup viewGroup, SpotListener listener){
        AdManager.getInstance(context).init("94756726a3849bc5", "092656f1bc114921", false, false);
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        splashViewSettings.setTargetClass(MainActivity.class);
        splashViewSettings.setSplashViewContainer(viewGroup);
        SpotManager.getInstance(context).showSplash(context,
                splashViewSettings, listener);
    }

    /**
     * 文件夹大小
     */
    /**
     * 获取文件夹大小
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file){
        long size = 0;
        try {
            java.io.File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                }else{
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        return size/1048576;
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     * @param deleteThisPath
     * @param
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    /**
     * copy  Assets目录下面的apk文件 到 sdcard 上面
     */
    public static boolean copyApkFromAssets(Context context, String fileName) {
        boolean copyIsFinish = false;
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + fileName;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }

}
