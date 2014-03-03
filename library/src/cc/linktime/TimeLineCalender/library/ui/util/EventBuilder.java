package cc.linktime.TimeLineCalender.library.ui.util;

import android.content.Context;
import android.view.LayoutInflater;
import cc.linktime.TimeLineCalender.library.R;
import cc.linktime.TimeLineCalender.library.ui.CalenderTimeLineEvent;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 3/3/14
 * Time: 11:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventBuilder {
    private Context context;
    private LayoutInflater layoutInflater;
    private int start;
    private int end;
    private String info;
    public EventBuilder(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public EventBuilder setStart(int start){
         this.start = start;
        return this;
    }

    public EventBuilder setEnd(int end) {
        this.end = end;
        return this;
    }

    public EventBuilder setDuring(int start,int end) {
        this.start = start;
        this.end = end;
        return this;
    }

    public EventBuilder setInfo(String info) {
        this.info = info;
        return this;
    }

    public CalenderTimeLineEvent builder(){
        CalenderTimeLineEvent event = (CalenderTimeLineEvent)layoutInflater.inflate(R.layout.day_timeline_event,null);
        event.setDuring(start,end);
        event.setEventInfo(info);
        return event;
    }
}
