package cc.linktime.TimeLineCalendar.library.util;

import android.content.Context;
import android.view.LayoutInflater;
import cc.linktime.TimeLineCalendar.library.ui.CalendarTimeLineEvent;
import cc.linktime.TimeLineCalendar.library.ui.CalendarWeek;
import cc.linktime.TimeLineCalender.library.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 3/3/14
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarWeekBuilder {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ArrayList<CalendarTimeLineEvent>> eventList;
    public CalendarWeekBuilder(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        eventList = new ArrayList<ArrayList<CalendarTimeLineEvent>>();
        for (int i=0;i<7;i++) {
            eventList.add(new ArrayList<CalendarTimeLineEvent>());
        }
    }

    public CalendarWeekBuilder setEventList(ArrayList<ArrayList<CalendarTimeLineEvent>> eventList) {
        this.eventList = eventList;
        return this;
    }

    public ArrayList<ArrayList<CalendarTimeLineEvent>> getEventList() {
        return eventList;
    }

    public CalendarWeek builder() {
        CalendarWeek week = (CalendarWeek)layoutInflater.inflate(R.layout.week,null);
        week.setEventList(eventList);
        return week;
    }
}
