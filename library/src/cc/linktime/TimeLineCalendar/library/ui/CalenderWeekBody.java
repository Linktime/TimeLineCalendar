package cc.linktime.TimeLineCalendar.library.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import cc.linktime.TimeLineCalender.library.R;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/26/14
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class CalenderWeekBody extends ViewGroup {
    private int totalWidth;
    private int totalHeight;
    private int timelineWidth;
    private int timelineEventListWidth;

    private float startY;
    private float lastY;

    private int screenHeight;

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

        findViewById(R.id.timeline).measure(timelineWidth,totalHeight);
        findViewById(R.id.timeline_eventlist).measure(timelineEventListWidth,totalHeight);

        setMeasuredDimension(totalWidth, totalHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.
        findViewById(R.id.timeline).layout(0,0,timelineWidth,totalHeight);
        findViewById(R.id.timeline_eventlist).layout(timelineWidth,0,totalWidth,totalHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retVal =  super.onTouchEvent(event);    //To change body of overridden methods use File | Settings | File Templates.
        if (event.getAction() == MotionEvent.ACTION_MOVE)
            Log.i("Body","onTouchEvent --- move");
        if (event.getAction() == MotionEvent.ACTION_SCROLL)
            Log.i("Body","onTouchEvent --- scroll");
        if (event.getAction() == MotionEvent.ACTION_CANCEL)
            Log.i("Body","onTouchEvent --- cancel");
        return retVal;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean ret =  super.dispatchTouchEvent(ev);    //To change body of overridden methods use File | Settings | File Templates.
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int temp = (int)(startY - ev.getY() + lastY);
                Log.i("Body", "dispatchTouchEvent --- " + String.valueOf(temp));
                if (temp<0) {
                    scrollTo(0,0);
                }  else if(temp>totalHeight-getScreenHeight() + hour_height) {
                    scrollTo(0,totalHeight-getScreenHeight() + hour_height);
                } else {
                    scrollTo(0,temp);
                }
                break;
            case MotionEvent.ACTION_UP:
                lastY += startY - ev.getY();
                break;
            default:
                break;
        }
        return true;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
