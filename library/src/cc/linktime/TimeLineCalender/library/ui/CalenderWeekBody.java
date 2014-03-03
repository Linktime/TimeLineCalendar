package cc.linktime.TimeLineCalender.library.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import cc.linktime.TimeLineCalender.library.R;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/26/14
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class CalenderWeekBody extends ViewGroup implements CalenderWeekSide.CursorListener {
    private int totalWidth;
    private int totalHeight;
    private int timelineWidth;
    private int timelineEventListWidth;
    private int weekday=0;
    private int hour_height = getResources().getDimensionPixelSize(R.dimen.hour_height);

    private final int [] colors = {getResources().getColor(R.color.sun_bg),
            getResources().getColor(R.color.mon_bg),
            getResources().getColor(R.color.tue_bg),
            getResources().getColor(R.color.wed_bg),
            getResources().getColor(R.color.thu_bg),
            getResources().getColor(R.color.fri_bg),
            getResources().getColor(R.color.sat_bg),};

    public CalenderWeekBody(Context context) {
        super(context);
    }

    public CalenderWeekBody(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalenderWeekBody(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        totalHeight = MeasureSpec.getSize(heightMeasureSpec);
        totalWidth = MeasureSpec.getSize(widthMeasureSpec);

        timelineWidth = totalWidth / 5;
        timelineEventListWidth = timelineWidth * 4;

        findViewById(R.id.timeline).measure(timelineWidth,hour_height*24 + getResources().getDimensionPixelSize(R.dimen.timeline_padding)*2);
        findViewById(R.id.timeline_eventlist).measure(timelineEventListWidth,totalHeight);

        findViewById(R.id.cursor).measure((totalHeight/9)/4,totalHeight/9);

        setMeasuredDimension(totalWidth, totalHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.
        findViewById(R.id.timeline).layout(0,0,timelineWidth,totalHeight);
        findViewById(R.id.timeline_eventlist).layout(timelineWidth,0,totalWidth,totalHeight);
        setCursor(totalWidth - (totalHeight/9)/4, totalHeight/9*weekday, totalWidth, totalHeight/9*(weekday+1));
    }

    public void setCursor(int left, int top, int right, int bottom) {
        View cursor = findViewById(R.id.cursor);
        cursor.layout(left, top, right, bottom);
    }

    @Override
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
        setCursor(totalWidth - (totalHeight / 9) / 4, totalHeight / 9 * weekday, totalWidth, totalHeight / 9 * (weekday + 1));
//        cursor.setCursorColor(colors[weekday]);

    }
}
