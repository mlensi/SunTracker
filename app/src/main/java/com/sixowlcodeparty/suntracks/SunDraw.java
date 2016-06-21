package com.sixowlcodeparty.suntracks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;

import java.util.Date;

/**
 * Created by Michael Lensi on 6/21/2016.
 */
public class SunDraw extends View {

    public static final int colorOrange = 0xFF9900;
    Paint mPaint;

    public SunDraw(Context context) {
        super(context);
        init();
    }
    public SunDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float paddingLeft = getPaddingLeft();
        float paddingRight= getPaddingRight();
        float paddingTop = getPaddingTop();
        float paddingBottom = getPaddingBottom();
        float width = getWidth() - (paddingLeft+paddingRight);
        float height = getHeight() - (paddingTop+paddingBottom);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(colorOrange);

        float fTextSize = 50f;
        float fScale = getContext().getResources().getDisplayMetrics().density;
        mPaint.setTextSize(fTextSize * fScale);

        Date d = new Date();
        String strTime  = (String) DateFormat.format("HH:mm:ss", d.getTime());

        //canvas.drawText(strTime, 0f, 0f, mPaint);

    }

}
