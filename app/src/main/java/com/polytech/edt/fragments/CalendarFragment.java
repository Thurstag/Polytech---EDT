package com.polytech.edt.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polytech.edt.R;
import com.polytech.edt.calendar.ADEDateTimeInterpreter;
import com.polytech.edt.calendar.ADEEventClickListener;
import com.polytech.edt.calendar.ADEMonthChangeListener;
import com.polytech.edt.model.ADECalendar;
import com.polytech.edt.model.ADEWeekView;
import com.polytech.edt.util.LOGGER;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    private ADEWeekView weekView;
    private ADECalendar calendar;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // Set calendar
        calendar = (ADECalendar) getArguments().getSerializable("calendar");

        // Get a reference for the week view in the layout.
        weekView = view.findViewById(R.id.weekView);

        // Init week view
        initWeekView();
        goToFirstEvent();

        // Inflate the layout for this fragment
        return view;
    }

    /**
     * Method to init week view
     */
    public void initWeekView() {
        // Implement month listener
        weekView.setMonthChangeListener(new ADEMonthChangeListener(calendar));

        // Change DateTimeInterpreter
        weekView.setDateTimeInterpreter(new ADEDateTimeInterpreter(weekView));

        // Implement click callback
        weekView.setOnEventClickListener(new ADEEventClickListener(this));
    }

    /**
     * Method to go to the first event
     */
    private void goToFirstEvent() {
        if (calendar == null) {
            LOGGER.warning("Calendar is null");
            return;
        }
        weekView.goToDate(calendar.events().get(0).getStartTime());
    }

    /**
     * Method to change day visibility
     *
     * @param i Count day
     */
    public void changeVisibility(int i) {
        weekView.setNumberOfVisibleDays(i);
        goToFirstEvent();
    }
}
