package Calendar;


import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarEngine {
    protected int countOfDay;
    protected int indent;
    protected int weekday;
    protected GregorianCalendar d = new GregorianCalendar();
    protected String[] weekdayNames;
    protected String[] monthNames;
    public void calculate() {
        int today = d.get(Calendar.DAY_OF_MONTH);//возвращает число месяца
        int month = d.get(Calendar.MONTH);//возвращает месяц считая от нуля
        d.set(Calendar.DAY_OF_MONTH, 1);
        weekday = d.get(Calendar.DAY_OF_WEEK);//возвращает день недели по счету начиная с воскресения
        int firstDayOfWeek = d.getFirstDayOfWeek();
        while (weekday != firstDayOfWeek) {
            indent++;
            d.add(Calendar.DAY_OF_MONTH, -1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }
        weekdayNames = new DateFormatSymbols().getShortWeekdays();
        do {
            System.out.printf("%4s", weekdayNames[weekday]);
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        } while (weekday != firstDayOfWeek);
        System.out.println();
        monthNames = new DateFormatSymbols().getShortMonths();
        for (int i = 0; i < indent ; i++) {
            System.out.print("    ");
        }
        d.set(Calendar.DAY_OF_MONTH, 1);
        do {
            int day = d.get(Calendar.DAY_OF_MONTH);
            System.out.printf("%3d", day);
            if (day == today) System.out.print("*");
            else System.out.print(" ");
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
            if (weekday == firstDayOfWeek) System.out.println();
            countOfDay++;
        }
        while (d.get(Calendar.MONTH) == month);
        d.add(Calendar.MONTH, -1);
        System.out.println();
    }


}
