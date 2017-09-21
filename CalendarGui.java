package Calendar;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarGui{
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}
class MyFrame extends JFrame {
    MyFrame() {
        MyPanel panel = new MyPanel();
        Container pane = getContentPane();
        pane.add(panel);
        pack();
    }
}
class MyPanel extends JPanel {
    private JPanel dayPanel;
    private JPanel actionPanel;
    private JButton previousMonth;
    private JButton nextMonth;
    private JLabel currentDate;
    private ArrayList<JLabel> dateList;
    private CalendarEngine engine;
    MyPanel() {
        engine = new CalendarEngine();
        engine.calculate();
        this.setLayout(new BorderLayout());
        dayPanel = new JPanel();
        dayPanel.setLayout(new GridLayout(7, 7));

        this.add(dayPanel, BorderLayout.CENTER);
        previousMonth = new JButton("<");
        nextMonth = new JButton(">");
        previousMonth.addActionListener(new buttonListener());
        nextMonth.addActionListener(new buttonListener());
        currentDate = new JLabel("");
        int aMonth = engine.d.get(Calendar.MONTH);
        int aYear = engine.d.get(Calendar.YEAR);
        for (int i = 0; i <= 11; i++) {
            if (i == aMonth) currentDate.setText(engine.monthNames[i] + " " + aYear);
        }
        currentDate.setHorizontalAlignment(JLabel.CENTER);
        actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());
        actionPanel.add(previousMonth, BorderLayout.WEST);
        actionPanel.add(currentDate, BorderLayout.CENTER);
        actionPanel.add(nextMonth, BorderLayout.EAST);
        this.add(actionPanel, BorderLayout.NORTH);
        for (int i = 2; i <= 7; i++) {
            engine.d.add(Calendar.DAY_OF_MONTH, 1);
            JLabel label = new JLabel(engine.weekdayNames[i]);
            label.setHorizontalAlignment(JLabel.CENTER);
            dayPanel.add(label);
            if (i == 7) {
                label = new JLabel(engine.weekdayNames[1]);
                dayPanel.add(label);
                label.setHorizontalAlignment(JLabel.CENTER);
            }
        }
        dateList = new ArrayList<>();
        for (int i = 1; i <= 42 ; i++) {
            JLabel label = new JLabel("");
            dateList.add(label);
            label.setHorizontalAlignment(JLabel.CENTER);
            dayPanel.add(label);
        }
        addDay(engine.indent, engine.countOfDay);
    }
    public void addDay(int indent, int countOfDay) {
        int dayOfMonth = 1;
        for (int i = 1; i <= 42; i++) {
            if (i <= indent || i > countOfDay + indent) {
                dateList.get(i-1).setText("");
            } else {
                dateList.get(i-1).setText(dayOfMonth + "");
                dayOfMonth++;
            }
            dateList.get(i).setHorizontalAlignment(JLabel.CENTER);
            dateList.add(dateList.get(i-1));
            dayPanel.add(dateList.get(i-1));
        }
    }
    private class buttonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            String command = event.getActionCommand();
            engine.indent = 0;
            engine.countOfDay = 0;
            engine.d.get(Calendar.MONTH);
            if (command.equals(">")) {
                engine.d.add(Calendar.MONTH, 1);
            } else if (command.equals("<")) engine.d.add(Calendar.MONTH, -1);
            engine.d.get(Calendar.MONTH);
            engine.calculate();
            addDay(engine.indent, engine.countOfDay);
            int aMonth = engine.d.get(Calendar.MONTH);
            int aYear = engine.d.get(Calendar.YEAR);
            for (int i = 0; i <= 11; i++) {
                if (i == aMonth) currentDate.setText(engine.monthNames[i] + " " + aYear);
            }
        }
    }
}
