package cc.linktime.TimeLineCalendar.library.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
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

    public interface EventListListener{
        public int updateEventList(int weekday);
    }

    private int totalWidth;
    private int totalHeight;
    private int cellHeight;
    private int cellWidth;
    private int cursorHeight;
    private EventListListener eventListListener;
    private int weekday=0;

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
//        Paint blackPaint = new Paint();
//        blackPaint.setColor(Color.BLACK);
//        canvas.drawRect(child.getLeft()+5,child.getBottom()-2,child.getRight()-5,child.getBottom()+2,whitePaint);
//        canvas.drawCircle(child.getRight()-15,(child.getBottom()+child.getTop())/2,10,whitePaint);
//        canvas.drawText("1",child.getRight()-15,(child.getBottom()+child.getTop())/2,blackPaint);

        return ret;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Paint d = new Paint();
        int borderWidth = 5;
        LinearGradient lg = new LinearGradient(0,totalHeight/2,borderWidth,totalHeight/2,Color.rgb(204,204,204),Color.rgb(255,255,255), Shader.TileMode.MIRROR);
        d.setShader(lg);
        d.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(cursorHeight/4,getTop(),cursorHeight/4+borderWidth,getBottom(),d);
        super.dispatchDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        totalHeight = MeasureSpec.getSize(heightMeasureSpec);
        cellHeight = totalHeight/9;
        cellWidth = totalWidth-cursorHeight/4;

        CalenderWeekSideCursor cursor = (CalenderWeekSideCursor)findViewById(R.id.cursor);
        cursor.setCursorHeight(cellHeight);
        cursorHeight = cellHeight;

        refresh();

        for (int i=0;i<getChildCount();i++) {
            getChildAt(i).measure(cellWidth,cellHeight);
        }
        cursor.measure(cursorHeight/4,cursorHeight);
        setMeasuredDimension(totalWidth,totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.
        CalenderWeekSideCursor cursor = (CalenderWeekSideCursor)findViewById(R.id.cursor);
        for (int i=0;i<getChildCount();i++) {
            getChildAt(i).layout(cursorHeight/4,cellHeight * i, totalWidth, cellHeight * (i+1));
        }
        setCursor(0, cursorHeight*weekday, cursorHeight/4, cursorHeight*(weekday+1));
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
                        //cursorListener.updateCursor(weekday);
                        updateCursor(weekday);
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

    public void setCursor(int left, int top, int right, int bottom) {
        View cursor = findViewById(R.id.cursor);
        cursor.layout(left, top, right, bottom);
    }

    public void updateCursor(int weekday) {
        //To change body of implemented methods use File | Settings | File Templates.
        CalenderWeekSideCursor cursor = (CalenderWeekSideCursor)findViewById(R.id.cursor);
        TranslateAnimation ta = new TranslateAnimation(0,0,
                totalHeight/9*(this.weekday - weekday),0);
        ta.setDuration(500);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(colors[this.weekday],colors[weekday]);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(cursor);

        this.weekday = weekday;
        cursor.setCursorColor(weekday);
        cursor.startAnimation(ta);
        valueAnimator.start();
        setCursor(0, cursorHeight * weekday, cursorHeight/4, cursorHeight * (weekday + 1));
//        cursor.setCursorColor(colors[weekday]);

    }

    public void initEventCount(int [] counts) {
        for (int i=0;i<7;i++) {
            CalenderWeekCell cellChild = (CalenderWeekCell)getChildAt(i);
            TextView event_count = (TextView)cellChild.findViewById(R.id.event_count);
            if (counts[i]!=0) {
                event_count.setVisibility(VISIBLE);
                if (counts[i]<10) {
                    event_count.setTextSize(12);
                    event_count.setPadding(5,2,5,2);
                } else {
                    event_count.setTextSize(10);
                    event_count.setPadding(2,2,2,2);
                }
                event_count.setTextColor(colors[i]);
                event_count.setText(String.valueOf(counts[i]));
            } else {
                event_count.setVisibility(GONE);
            }
        }
    }
}
