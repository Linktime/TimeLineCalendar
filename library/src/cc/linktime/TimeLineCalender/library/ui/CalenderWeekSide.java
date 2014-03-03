package cc.linktime.TimeLineCalender.library.ui;

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
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class CalenderWeekSide extends ViewGroup {

    public interface CursorListener {
        public void updateCursor(int weekday);
    }

    public interface EventListListener{
        public void updateEventList(int weekday);
    }

    private int totalWidth;
    private int totalHeight;
    private int cellHeight;
    private CursorListener cursorListener;
    private EventListListener eventListListener;

    private final String [] WEEKDAY = {"Sun.","Mon.","Tue.","Wed.","Thu.","Fri.","Sat."};
    private final int [] colors = {getResources().getColor(R.color.sun_bg),
            getResources().getColor(R.color.mon_bg),
            getResources().getColor(R.color.tue_bg),
            getResources().getColor(R.color.wed_bg),
            getResources().getColor(R.color.thu_bg),
            getResources().getColor(R.color.fri_bg),
            getResources().getColor(R.color.sat_bg)};

    public CalenderWeekSide(Context context) {
        super(context);
    }

    public CalenderWeekSide(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalenderWeekSide(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean ret =  super.drawChild(canvas, child, drawingTime);    //To change body of overridden methods use File | Settings | File Templates.
//        Paint whitePaint = new Paint();
//        whitePaint.setColor(Color.WHITE);
//        canvas.drawRect(child.getLeft()+5,child.getBottom()-2,child.getRight()-5,child.getBottom()+2,whitePaint);
        return ret;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Paint d = new Paint();
        int borderWidth = 5;
//        d.setColor(Color.rgb(0,255,0));
//        canvas.drawLine(getLeft(),getTop(),getRight(),getBottom(),d);
//        Log.i("Side","dispatchDraw --- " + String.valueOf(getLeft()));
        LinearGradient lg = new LinearGradient(0,totalHeight/2,borderWidth,totalHeight/2,Color.rgb(204,204,204),Color.rgb(255,255,255), Shader.TileMode.MIRROR);
        d.setShader(lg);
        d.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(0,getTop(),borderWidth,getBottom(),d);
        super.dispatchDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        totalHeight = MeasureSpec.getSize(heightMeasureSpec);
        cellHeight = totalHeight/9;
        refresh();

        for (int i=0;i<getChildCount();i++) {
            getChildAt(i).measure(totalWidth,cellHeight);
        }

        setMeasuredDimension(totalWidth,totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.
        for (int i=0;i<getChildCount();i++) {
            getChildAt(i).layout(0,cellHeight * i, totalWidth, cellHeight * (i+1));
        }
    }

    public void setCursorListener(CursorListener cl) {
        cursorListener = cl;
    }

    public void setEventListListener(EventListListener ell) {
        eventListListener = ell;
    }

    public void refresh() {
        for (int i=0;i<7;i++) {
            CalenderWeekCell cellChild = (CalenderWeekCell)getChildAt(i);
            cellChild.setBackgroundColor(colors[i]);
            cellChild.setWeekDay(i);
            TextView date = (TextView)cellChild.getChildAt(0);
            TextView weekday = (TextView)cellChild.getChildAt(1);
            //date.setText(String.valueOf(i));
            weekday.setText(WEEKDAY[i]);
            cellChild.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //To change body of implemented methods use File | Settings | File Templates.
                    try {
                        int weekday = ((CalenderWeekCell)v).getWeekDay();
                        cursorListener.updateCursor(weekday);
                        eventListListener.updateEventList(weekday);
                        Log.i("Side","onClick --- " + weekday);

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
