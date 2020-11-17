package application;

import java.time.LocalDate;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class TaskButton extends Button {

	private boolean isComplete = false;
	private int cid;
	private LocalDate date;
	public TaskButton(String str, LocalDate date)
	{
		super(str);
		this.date = date;

    	
		
		setStyle("-fx-background-color: transparent;");
		
		setOnMouseClicked(e -> {
			VBoxNode vbox = (VBoxNode) this.getParent();
		    if(e.getButton() == MouseButton.SECONDARY) 
		    {
		    	vbox.taskCount--;
		    	vbox.getChildren().remove(this);
		    	vbox.list.remove(this);
		    	vbox.update();
		    	FullView.db.deleteCellData(cid);
		    	
		    }
		    
		    //strike through a label (alternating)
		    else if(e.getButton() == MouseButton.PRIMARY && !isComplete) 
		    {
		    	isComplete = true;
		    	strikethrough(true);
		    	vbox.update();
		    	FullView.db.setComplete(cid, true);
		    }	
		    
		    else if(e.getButton() == MouseButton.PRIMARY && isComplete) 
		    {
		    	isComplete = false;
		    	strikethrough(false);
		    	vbox.update();
		    	FullView.db.setComplete(cid, false);
		    }		    
		});
	}
	
	public void setCid(int cid)
	{
		this.cid = cid;
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
	
	public int getCid()
	{
		return cid;
	}
	
	
	public LocalDate getDate()
	{
		return date;
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
