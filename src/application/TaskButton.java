package application;

import java.text.AttributedString;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import java.awt.Font;
import java.awt.font.TextAttribute;
import javafx.scene.layout.VBox;

public class TaskButton extends Button {

	private boolean isComplete = false;
	public TaskButton(String str)
	{
		super(str);
		
		
		setStyle("-fx-background-color: transparent;");
		
		setOnMouseClicked(e -> {
			VBoxNode vbox = (VBoxNode) this.getParent();
		    if(e.getButton() == MouseButton.SECONDARY) 
		    {
		    	vbox.taskCount--;
		    	vbox.getChildren().remove(this);
		    	vbox.list.remove(this);
		    	vbox.update();
		    }
		    
		    //strike through a label (alternating)
		    else if(e.getButton() == MouseButton.PRIMARY && !isComplete) 
		    {
		    	isComplete = true;
		    	strikethrough(true);
		    	vbox.update();
		    }	
		    
		    else if(e.getButton() == MouseButton.PRIMARY && isComplete) 
		    {
		    	isComplete = false;
		    	strikethrough(false);
		    	vbox.update();
		    }		    
		});
	}
	
	public boolean isComplete()
	{
		return isComplete;
	}
	
	public void setIsComplete(boolean b)
	{
		isComplete = b;
		strikethrough(b);

	}
	

	/**
	 * strikes through or un-strikes a button label
	 * @param b
	 */
	private void strikethrough(boolean b)
	{
		if(b)
		{
			getStylesheets().addAll(getClass().getResource("strikethrough.css").toExternalForm());
	    	getStylesheets().removeAll(getClass().getResource("strikethroughoff.css").toExternalForm());
		}
		else
		{
	    	getStylesheets().addAll(getClass().getResource("strikethroughoff.css").toExternalForm());
	    	getStylesheets().removeAll(getClass().getResource("strikethrough.css").toExternalForm());
		}	
	}
}
