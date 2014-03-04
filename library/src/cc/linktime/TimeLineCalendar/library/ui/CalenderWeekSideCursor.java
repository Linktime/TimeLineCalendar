package cc.linktime.TimeLineCalendar.library.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import cc.linktime.TimeLineCalender.library.R;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/26/14
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalenderWeekSideCursor extends View implements ValueAnimator.AnimatorUpdateListener {
    private int weekday=0;
    private int lastWeekday = 0;
    private int colorIndex=0;
    private int cursorHeight;

    private final int [] colors = {getResources().getColor(R.color.sun_bg),
            getResources().getColor(R.color.mon_bg),
            getResources().getColor(R.color.tue_bg),
            getResources().getColor(R.color.wed_bg),
            getResources().getColor(R.color.thu_bg),
            getResources().getColor(R.color.fri_bg),
            getResources().getColor(R.color.sat_bg),};

    public CalenderWeekSideCursor(Context context) {
        super(context);
    }

    public CalenderWeekSideCursor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalenderWeekSideCursor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.
        int cursorHeight = getBottom() - getTop();
        int cursorWidth = getRight() - getLeft();
        Paint paint = new Paint();
        paint.setColor(colors[colorIndex]);
//        LinearGradient lg = new LinearGradient(cursorHeight*3/4,cursorHeight/2,cursorHeight,cursorHeight/2,Color.rgb(204,204,204),Color.rgb(153,255,153), Shader.TileMode.MIRROR);
//        paint.setShader(lg);
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();



        path.moveTo(cursorWidth,cursorHeight/4);
        path.lineTo(0, cursorHeight / 2);
        path.lineTo(cursorWidth, cursorHeight*3/4);
        path.close();
        canvas.drawPath(path,paint);
        //canvas.drawLine(0,0,50,50,paint);
    }

    public void setCursorColor(int weekday){
        this.lastWeekday = this.weekday;
        this.weekday = weekday;
    }

    public void setCursorHeight(int cursorHeight) {
        this.cursorHeight = cursorHeight;
    }

    public int getCursorHeight() {
        return cursorHeight;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        //To change body of implemented methods use File | Settings | File Templates.
        //FIXME
        colorIndex = lastWeekday + (int)((float)(animation.getDuration()-animation.getStartDelay())/animation.getDuration()*(weekday-lastWeekday));
    }
}
