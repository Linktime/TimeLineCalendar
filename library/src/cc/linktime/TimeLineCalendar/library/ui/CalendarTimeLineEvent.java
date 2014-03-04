package cc.linktime.TimeLineCalendar.library.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import cc.linktime.TimeLineCalender.library.R;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/26/14
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarTimeLineEvent extends ViewGroup {

    private int totalWidth;
    private int totalHeight;
    private int start;
    private int end;

    public CalendarTimeLineEvent(Context context) {
        super(context);
    }

    public CalendarTimeLineEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarTimeLineEvent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        totalHeight = MeasureSpec.getSize(heightMeasureSpec);
        totalWidth = MeasureSpec.getSize(widthMeasureSpec);

        findViewById(R.id.event_during).measure(totalWidth,20);
        findViewById(R.id.event_info).measure(totalWidth,totalHeight-20);

        setMeasuredDimension(totalWidth,totalHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.
        findViewById(R.id.event_during).layout(0,0,totalWidth,20);
        findViewById(R.id.event_info).layout(0,20,totalWidth,totalHeight);
    }

    public void setDuring(int start,int end){
        this.start = start;
        this.end = end;
        TextView textView = (TextView)findViewById(R.id.event_during);
        textView.setText(String.valueOf(start) + ":00-" + String.valueOf(end) + ":00");
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getDuring() {
        return end - start;
    }

    public void setEventInfo(String info) {
        TextView textView = (TextView)findViewById(R.id.event_info);
        textView.setText(info);
    }

}
