package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MonthGridPane extends GridPane {
	private YearMonth yearMonth;
    private ArrayList<VBoxNode> allCalendarDays = new ArrayList<>(35);
	
	public MonthGridPane(YearMonth yearMonth) throws IOException
	{
		super();
		
		this.yearMonth = yearMonth;
		
		LocalDate dayMonthYearDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
		String sayOfWeekString = dayMonthYearDate.getDayOfWeek().toString();
		while (!dayMonthYearDate.getDayOfWeek().toString().equals("SUNDAY"))
            dayMonthYearDate = dayMonthYearDate.minusDays(1);
		
		for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                VBoxNode vb = new VBoxNode();              
                vb.setPrefSize(150,150);
                vb.setDate(dayMonthYearDate);
                vb.getChildren().add(new Text(String.valueOf(dayMonthYearDate.getDayOfMonth())));
                dayMonthYearDate = dayMonthYearDate.plusDays(1);
                this.add(vb,j,i);
                allCalendarDays.add(vb);
                
                if(vb.getDate().isBefore(LocalDate.now()))
                	vb.setVBoxComplete();
                
            }
        }	
	}
	
	public void addTasks(int year, int month, int day, boolean isComplete, String task)
    {
    	for(VBoxNode ap : allCalendarDays)
    	{
    		if(ap.getDate().getYear() == year && ap.getDate().getMonthValue() == month && ap.getDate().getDayOfMonth() == day)
    		{
    			TaskButton tb = new TaskButton(task);
    			tb.setIsComplete(isComplete);
    			ap.addTaskButton(tb);
    		}
    	}
    }
	public ArrayList<VBoxNode> getDays()
	{
		return allCalendarDays;
	}
	
}
