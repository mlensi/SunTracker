package com.sixowlcodeparty.suntracks;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Date;

/**
 * Created by Michael Lensi on 6/21/2016.
 */
public class GeoDraw extends View {

    public static final int colorOrange = 0xFF9900;
    Paint mPaint;
    Location mLocation;

    public GeoDraw(Context context) {
        super(context);
        init();
    }
    public GeoDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        mLocation = null;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
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
        mPaint.setStrokeWidth(8);

        // add alpha channel to color integer
        int r = Color.red(colorOrange);
        int g = Color.green(colorOrange);
        int b = Color.blue(colorOrange);
        mPaint.setColor(Color.argb(255, r, g, b));
        //mPaint.setColor(colorOrange);

        float fTextSize = height * 0.25f * 0.5f;
        float fScale = getContext().getResources().getDisplayMetrics().density;
        mPaint.setTextSize(fTextSize * fScale);

        Date d = new Date();
        String strTime  = (String) DateFormat.format("HH:mm:ss", d.getTime());
        Log.d("TimeDraw", strTime);

        // default
        double dLat = 35.0;
        double dLon = -106.0;

        if (mLocation != null) {
            dLat = mLocation.getLatitude();
            dLon = mLocation.getLongitude();
        }

        String strNS = dLat > 0 ? "N" : "S";
        String strEW = dLon > 0 ? "E" : "W";

        String strLat = String.format("%07.3f %s", Math.abs(dLat), strNS);
        String strLon = String.format("%07.3f %s", Math.abs(dLon), strEW);

        canvas.drawText(strLat, 5f, height*0.40f, mPaint);
        canvas.drawText(strLon, 5f, height*0.90f, mPaint);
    }

}
