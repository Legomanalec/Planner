package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.YearMonth;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FullView {
	private VBox fullView;
	private Label calendarTitle;
	private GridPane dayLabels;
	private YearMonth currentMonth;
	private HBox titleBar;
	private MonthGridPane monthPane;

	public FullView() throws IOException
	{
		currentMonth = YearMonth.now();
		initDayLabels();
		initTitleBar();
		monthPane = new MonthGridPane(currentMonth);
		fullView = new VBox(titleBar, dayLabels, monthPane);
		
	}
	
	public VBox getFullView()
	{
		return fullView;
	}
	
	public void nextMonth() throws IOException
	{
		currentMonth = currentMonth.plusMonths(1);
		initDayLabels();
		initTitleBar();
		fullView.getChildren().clear();
		monthPane = new MonthGridPane(currentMonth);		
		fullView.getChildren().add(titleBar);
		fullView.getChildren().add(dayLabels);
		fullView.getChildren().add(monthPane);

	}
	
	public void previousMonth() throws IOException
	{
		currentMonth = currentMonth.minusMonths(1);
		initDayLabels();
		initTitleBar();
		fullView.getChildren().clear();
		monthPane = new MonthGridPane(currentMonth);		
		fullView.getChildren().add(titleBar);
		fullView.getChildren().add(dayLabels);
		//monthPane.populate();
		fullView.getChildren().add(monthPane);
		
	}
	
	public void initTitleBar()
	{
		calendarTitle = new Label(currentMonth.getMonth().toString());
        Button previousMonth = new Button("<-");
        previousMonth.setOnAction(e -> {
				try {
					previousMonth();
				} catch (IOException e1) {
				}
		});
        Button nextMonth = new Button("->");
        nextMonth.setOnAction(e -> {
				try {
					nextMonth();
				} catch (IOException e1) {
				}
		});
        titleBar = new HBox(previousMonth, calendarTitle, nextMonth);
        titleBar.setAlignment(Pos.BASELINE_CENTER);
	}
	
	public void initDayLabels()
	{
		Text[] dayNames = new Text[]{ new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
                new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
                new Text("Saturday") };
		dayLabels = new GridPane();
		dayLabels.setPrefWidth(600);
		Integer col = 0;
		for (Text txt : dayNames) {
			AnchorPane ap = new AnchorPane();
			ap.setPrefSize(200, 10);
			ap.setBottomAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			dayLabels.add(ap, col++, 0);
		}
	}
}
