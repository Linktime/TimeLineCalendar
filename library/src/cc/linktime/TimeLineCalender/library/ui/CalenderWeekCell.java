package cc.linktime.TimeLineCalender.library.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/26/14
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class CalenderWeekCell extends ViewGroup {


    private int totalWidth;
    private int totalHeight;
    private int weekday;

    public CalenderWeekCell(Context context) {
        super(context);
    }

    public CalenderWeekCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalenderWeekCell(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setWeekDay(int weekday) {
        this.weekday = weekday;
    }

    public int getWeekDay() {
        return weekday;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        totalHeight = MeasureSpec.getSize(heightMeasureSpec);

        getChildAt(0).measure(totalWidth,totalHeight/3);
        getChildAt(1).measure(totalWidth,totalHeight*2/3);
        getChildAt(2).measure(24,24);
        setMeasuredDimension(totalWidth,totalHeight);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.
        getChildAt(0).layout(0,0,totalWidth,totalHeight/3);
        getChildAt(1).layout(0,totalHeight/3,totalWidth,totalHeight);
        Log.i("Cell", "onLayout --- " + String.valueOf(getRight()));
        getChildAt(2).layout(getRight()-49,(getBottom()-getTop())/2-12,getRight()-25,(getBottom()-getTop())/2+12);
    }

}
