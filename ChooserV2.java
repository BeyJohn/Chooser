import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChooserV2 extends Application
{
	
	private static TextField field;
	private static TextArea area;
	private static TreeSet<String> names1 = new TreeSet<String>(), names2 = new TreeSet<String>();
	private static boolean stillNaming = true, first = true;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	public static void addName1(String h)
	{
		names1.add(h);
	}
	public static void addName2(String h)
	{
		names2.add(h);
	}
	public static void grabName()
	{
		if(first)
		{
			ArrayList<String> names = new ArrayList<String>();
			names.addAll(names1);
			Random r = new Random();
			int g = r.nextInt(names.size());
			String name = names.get(g);
			area.setText(name);
			names1.remove(name);
			names2.add(name);
			if(names1.size() == 0)
				first = false;
		}
		else
		{
			ArrayList<String> names = new ArrayList<String>();
			names.addAll(names2);
			Random r = new Random();
			int g = r.nextInt(names.size());
			String name = names.get(g);
			area.setText(name);
			names2.remove(name);
			names1.add(name);
			if(names2.size() == 0)
				first = true;
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		BorderPane p = new BorderPane();
		field = new TextField();
		field.setOnAction(e->
		{
			if(stillNaming)
			{
				if(!field.getText().equals("END"))
				{
					addName1(field.getText());
				}
				else
				{
					stillNaming = false;
					area.setText("Press enter for a name.");
				}
				field.setText("");
			}
			else
			{
				grabName();
			}
		});
		area = new TextArea();
		area.setText("Enter names to be chosen from.\nType END to start choosing.");
		p.setPadding(new Insets(20,20,20,20));
		p.setTop(field);
		p.setBottom(area);
		Scene s = new Scene(p,600,350);
		stage.setTitle("ChooserV2");
		stage.setScene(s);
		stage.show();
	}
}
