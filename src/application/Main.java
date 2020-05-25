
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Alec Meyer's Planner");
        //primaryStage.setScene(new Scene(new FullCalendarView(YearMonth.now()).getView()));
        //primaryStage.setScene(new Scene(new MonthGridPane(YearMonth.now())));
        primaryStage.setScene(new Scene(new FullView().getFullView()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
    
   
    
    
}
