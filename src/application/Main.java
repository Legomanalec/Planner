
package application;

import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        System.out.println(FullView.db.getTask(17, 11, 2020));

    }
    
   
    
    
}
