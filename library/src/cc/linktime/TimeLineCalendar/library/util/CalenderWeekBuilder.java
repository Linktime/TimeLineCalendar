package cc.linktime.TimeLineCalendar.library.util;

import android.content.Context;
import android.view.LayoutInflater;
import cc.linktime.TimeLineCalender.library.R;
import cc.linktime.TimeLineCalendar.library.ui.CalenderTimeLineEvent;
import cc.linktime.TimeLineCalendar.library.ui.CalenderWeek;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 3/3/14
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalenderWeekBuilder {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ArrayList<CalenderTimeLineEvent>> eventList;
    public CalenderWeekBuilder(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        eventList = new ArrayList<ArrayList<CalenderTimeLineEvent>>();
        for (int i=0;i<7;i++) {
            eventList.add(new ArrayList<CalenderTimeLineEvent>());
        }
    }

    public CalenderWeekBuilder setEventList(ArrayList<ArrayList<CalenderTimeLineEvent>> eventList) {
        this.eventList = eventList;
        return this;
    }

    public ArrayList<ArrayList<CalenderTimeLineEvent>> getEventList() {
        return eventList;
    }

    public CalenderWeek builder() {
        CalenderWeek week = (CalenderWeek)layoutInflater.inflate(R.layout.week,null);
        week.setEventList(eventList);
        return week;
    }
}
