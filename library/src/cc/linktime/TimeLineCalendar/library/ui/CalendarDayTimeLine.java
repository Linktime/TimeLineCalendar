package cc.linktime.TimeLineCalendar.library.ui;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cc.linktime.TimeLineCalender.library.R;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/26/14
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarDayTimeLine extends ViewGroup {

    private int totalWidth;
    private int totalHeight;
    private int halfWidth;
    private int middle;
    private final int HOUR_HEIGHT = (int)getResources().getDimensionPixelSize(R.dimen.hour_height);

    public CalendarDayTimeLine(Context context) {
        super(context);
    }

    public CalendarDayTimeLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarDayTimeLine(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean ret =  super.drawChild(canvas, child, drawingTime);    //To change body of overridden methods use File | Settings | File Templates.
        Paint point = new Paint();
//        point.setColor(Color.rgb(204,102,51));
        point.setColor(Color.rgb(0,153,255));
        int middle = (child.getLeft() + child.getRight())/2;
        int center =  (child.getTop()+child.getBottom())/2;
//        canvas.drawCircle((child.getLeft()+child.getRight())/2,(child.getTop()+child.getBottom())/2,5,point);
        canvas.drawRect(middle-4,center-2,middle+4,center+2,point);
        return ret;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Paint paint = new Paint();

        halfWidth = 6/2;
        Log.i("TimeLine", "TimeLine --- dispatchDraw middle " + middle);
        int padding = getResources().getDimensionPixelSize(R.dimen.timeline_padding);
        int [] colors = {Color.rgb(240,240,240),Color.rgb(0,153,255),Color.rgb(240,240,240)};
        LinearGradient lg = new LinearGradient(middle-halfWidth,(getTop()+getBottom())/2,middle+halfWidth,(getTop()+getBottom())/2,colors,null, Shader.TileMode.MIRROR);
        paint.setShader(lg);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect( middle - halfWidth, getTop() + padding, middle + halfWidth,getBottom() - padding,paint);

        paint.setShader(null);
        paint.setColor(Color.rgb(0,153,255));
        canvas.drawCircle(middle,getTop() + padding,15,paint);
        canvas.drawCircle(middle,totalHeight - padding,15,paint);
        super.dispatchDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.

        totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        totalHeight = MeasureSpec.getSize(heightMeasureSpec);
        middle = totalWidth/2;

        int textMarginTop = HOUR_HEIGHT - 24;
        textMarginTop = textMarginTop / 2;


        Log.i("TimeLine", "TimeLine --- onMeasure textHeight " + ((TextView)getChildAt(0)).getLineHeight());

        for (int i=0;i<getChildCount();i++) {
            getChildAt(i).measure(middle/2,HOUR_HEIGHT);
            getChildAt(i).setPadding(0,textMarginTop,0,0);
        }
        setMeasuredDimension(totalWidth,totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.

        for (int i=0;i<getChildCount();i++) {
            getChildAt(i).layout(middle/4,HOUR_HEIGHT*(i+1),r-middle/4,HOUR_HEIGHT*(i+2));
        }
    }
}
