package com.sixowlcodeparty.suntracks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Date;

/**
 * Created by Michael Lensi on 6/21/2016.
 */
public class TimeDraw extends View {

    public static final int colorOrange = 0xFF9900;
    Paint mPaint;

    public TimeDraw(Context context) {
        super(context);
        init();
    }
    public TimeDraw(Context context, AttributeSet attrs) {
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
        mPaint.setStrokeWidth(15);

        // add alpha channel to color integer
        int r = Color.red(colorOrange);
        int g = Color.green(colorOrange);
        int b = Color.blue(colorOrange);
        mPaint.setColor(Color.argb(255, r, g, b));
        //mPaint.setColor(colorOrange);

        float fTextSize = height * 0.25f;
        float fScale = getContext().getResources().getDisplayMetrics().density;
        mPaint.setTextSize(fTextSize * fScale);

        Date d = new Date();
        String strTime  = (String) DateFormat.format("HH:mm:ss", d.getTime());
        Log.d("TimeDraw", strTime);

        canvas.drawText(strTime, 5f, height*0.90f, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();
        return true;
    }

}
