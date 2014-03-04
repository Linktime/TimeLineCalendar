package cc.linktime.TimeLineCalendar.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import cc.linktime.TimeLineCalendar.library.ui.CalenderTimeLineEvent;
import cc.linktime.TimeLineCalendar.library.ui.CalenderWeek;
import cc.linktime.TimeLineCalendar.library.util.CalenderWeekBuilder;
import cc.linktime.TimeLineCalendar.library.util.EventBuilder;


import java.util.ArrayList;

public class sample extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout root = (LinearLayout)findViewById(R.id.root);

        CalenderWeekBuilder weekBuilder = new CalenderWeekBuilder(this);   //首先生成一个周视图builder
        CalenderWeek week = weekBuilder.builder();  //用builder生产一个周试图

        ArrayList<ArrayList<CalenderTimeLineEvent>> eventList = weekBuilder.getEventList(); // 获取事件列表

        EventBuilder eventBuilder = new EventBuilder(this);
        CalenderTimeLineEvent event = eventBuilder.setDuring(2, 5).setInfo("我没空").builder(); //使用事件builder生成事件

        (eventList.get(3)).add(event); //添加到对应某一天中，其中eventlist内部有7个ArrayList，对应7天

        (eventList.get(1)).add(event);
        (eventList.get(1)).add(eventBuilder.setDuring(6,7).builder());


        week.setEventList(eventList);


        root.addView(week);                     //添加到布局中
    }
}