package com.gzfgeh.nbapp.Widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class GuideViewPager extends ViewPager {
    private Bitmap bg;
    private Paint b = new Paint(20);

    public GuideViewPager(Context context) {
        this(context, null);
    }

    public GuideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 绘制ViewPager的子View用dispatchDraw
     *
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (bg != null) {
            b.setStrokeWidth(10);
            int width = bg.getWidth();
            int height = bg.getHeight();
            int count = getAdapter().getCount();
            int x = getScrollX();
            int n = height * getWidth() / getHeight();
            int w = x * ((width - n) / (count - 1)) / getWidth();
            canvas.drawBitmap(bg, new Rect(w, 0, n + w, height),
                    new Rect(x, 0, x + getWidth(), getHeight()), b);
        }
        super.dispatchDraw(canvas);
    }

    public void setBackGround(Bitmap paramBitmap) {
        this.bg = paramBitmap;
        this.b.setFilterBitmap(true);
    }


}
