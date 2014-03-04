package cc.linktime.TimeLineCalendar.library.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/26/14
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalenderHour extends ViewGroup {

    public CalenderHour(Context context) {
        super(context);
    }

    public CalenderHour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalenderHour(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        int totalHeight = MeasureSpec.getSize(heightMeasureSpec);
        getChildAt(0).measure(totalWidth,totalHeight);
        setMeasuredDimension(totalWidth,totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.
        getChildAt(0).layout(l,t,r,b);
    }
}
