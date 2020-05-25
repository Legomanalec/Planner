package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * Create an anchor pane that can store additional data.
 */
public class VBoxNode extends VBox{


    // Date associated with this pane
    private LocalDate date = null;
    //protected boolean removeable = false;
    protected static boolean isOverDue = false;
    
    private TaskButton lab = null;
    public ArrayList<TaskButton> list;
    protected int taskCount;
    

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     * @throws FileNotFoundException 
     * @throws IOException 
     */
    public VBoxNode(Node... children) throws FileNotFoundException 
    {
        super(children);
        setStyle("-fx-border-style: solid outside;" + 
                "-fx-border-width: 2;" +
                "-fx-border-insets: 1;" +
                "-fx-background-color: #FFFFFF;");
        list = new ArrayList<TaskButton>();
        taskCount = 0;
    
        this.setOnMouseClicked(e -> 
        {
        	TextField field = new TextField();
        	this.getChildren().add(field);
        	
        	 field.setOnKeyPressed(new EventHandler<KeyEvent>() 
        	 {
    	        @Override
    	        public void handle(KeyEvent key) 
    	        {
    	            KeyCode k = key.getCode();
    	            

    	            if ((k.equals(KeyCode.ENTER))) 
    	            {	
    	            	addTaskButton(field.getText());
    	            	getChildren().remove(field);
    	            }
   
    	            else if ((k.equals(KeyCode.ESCAPE))) 
    	            {
     	            	getChildren().remove(field);
     	            	update();
     	            }
    	        }
        	});	   	 
        });              
    }   
    
    public void addTaskButton(String task)
    {
    	lab = new TaskButton(task);
    	getChildren().add(lab);
    	list.add(lab);
    	taskCount++;
    	update();
    }
    
    public void addTaskButton(TaskButton button)
    {

    	getChildren().add(button);
    	list.add(button);
    	taskCount++;
    	update();
    }
    
    
    public void setVBoxComplete()
    {
    	//setStyle("-fx-background-color: #00FF00;");
    	setStyle("-fx-border-style: solid outside;" + 
                "-fx-border-width: 2;" +
                "-fx-border-insets: 1;" +
                "-fx-background-color: #00FF00;");


    }
    
    public void setVBoxOverdue()
    {	
    	if(!this.getDate().isAfter(LocalDate.now()) && !this.getDate().isEqual(LocalDate.now()) && taskCount > 0)
    		setStyle("-fx-border-style: solid outside;" + 
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 1;" +
                    "-fx-background-color: #FF0000;");
    	else
    		setStyle("-fx-border-style: solid outside;" + 
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 1;" +
                    "-fx-background-color: #FFFFFF;");
    }
    
    public void setVBoxDefault()
    {
    	if(this.getDate().isAfter(LocalDate.now()) || this.getDate().isEqual(LocalDate.now()))
    	{
	    	setStyle("-fx-border-style: solid outside;" + 
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 1;" +
	                "-fx-background-color: #FFFFFF;");
    	}
    }
    
    
    public void update()
    {
    	for(TaskButton tb : list)
    	{
    		if(!tb.isComplete())
    		{
    			setVBoxOverdue();
    			return;
    		}
    	}
    	
    	if(taskCount <= 0 && (this.getDate().isAfter(LocalDate.now()) || this.getDate().isEqual(LocalDate.now())))
    	{
    		setVBoxDefault();
    	}
    	else
    		setVBoxComplete();
    }
    
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }  
    
    public ArrayList<TaskButton> getButtons()
    {
    	return list;
    }
}
