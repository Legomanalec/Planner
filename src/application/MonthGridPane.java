package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

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
                
                populateDay(vb);
            }
        }
		
	}
	
	public void populateDay(VBoxNode vbox)
	{
		LocalDate date = vbox.getDate();

		ArrayList<String> list = FullView.db.getTask(date);
		int id = 0;
		String task = "";
		boolean isComplete = false;
		System.out.println(FullView.db.getTask(date));
		for(int i = 0; i < list.size(); i++)
		{
			id = Integer.parseInt(list.get(i++));
			task = list.get(i++);
			isComplete = Integer.parseInt(list.get(i)) != 0;
			vbox.addTaskButton(task, id, isComplete);
		}
		
	}
	
	public ArrayList<VBoxNode> getDays()
	{
		return allCalendarDays;
	}
	
}
