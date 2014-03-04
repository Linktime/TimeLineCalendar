package cc.linktime.TimeLineCalendar.library.ui;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import cc.linktime.TimeLineCalender.library.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/26/14
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalenderTimeLineEventList extends ViewGroup implements CalenderWeekSide.EventListListener {

    private int totalWidth;
    private int totalHeight;
    private int weekday;

    private ArrayList<ArrayList<CalenderTimeLineEvent>> evenList;
    private int hour_height = getResources().getDimensionPixelSize(R.dimen.hour_height);


    public CalenderTimeLineEventList(Context context) {
        super(context);
    }

    public CalenderTimeLineEventList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalenderTimeLineEventList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean ret = super.drawChild(canvas, child, drawingTime);    //To change body of overridden methods use File | Settings | File Templates.
        int borderWidth = 5;
        Paint d = new Paint();
        LinearGradient lg = new LinearGradient(0,child.getBottom()-5,0,child.getBottom(),Color.rgb(204,204,204),Color.rgb(255,255,255), Shader.TileMode.MIRROR);
        d.setShader(lg);
        d.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(child.getLeft(),child.getBottom()-borderWidth,child.getRight(),child.getBottom(),d);
        return ret;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        totalHeight = MeasureSpec.getSize(heightMeasureSpec);
        totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        for (int i=0;i<getChildCount();i++) {
            CalenderTimeLineEvent child = (CalenderTimeLineEvent)getChildAt(i);
            child.measure(totalWidth,hour_height*child.getDuring());
        }


        setMeasuredDimension(totalWidth,totalHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.
        for (int i=0;i<getChildCount();i++) {
            CalenderTimeLineEvent child = (CalenderTimeLineEvent)getChildAt(i);
            child.layout(0,(int)(hour_height*(child.getStart()+1.5)),totalWidth,(int)(hour_height*(child.getEnd()+1.5)));
        }
    }

    public void setEvenList(ArrayList<ArrayList<CalenderTimeLineEvent>> evenList){
        this.evenList = evenList;
        updateEventList(weekday);
    }

    public ArrayList<ArrayList<CalenderTimeLineEvent>> getEvenList() {
        return evenList;
    }

    @Override
    public int updateEventList(int weekday){
        this.weekday = weekday;
        removeAllViews();
        for (CalenderTimeLineEvent event : evenList.get(weekday)) {
            addView(event);
        }
        return evenList.get(weekday).size();
    }

}
