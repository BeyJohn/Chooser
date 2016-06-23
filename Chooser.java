import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Chooser extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4565117502114562091L;
	private static JPanel contentPane;
	private static JTextPane textPane;
	private static JTextField textField;
	private static TreeSet<String> names1 = new TreeSet<String>(), names2 = new TreeSet<String>();
	private static boolean stillNaming = true, first = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Chooser frame = new Chooser();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
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
			textPane.setText(name);
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
			textPane.setText(name);
			names2.remove(name);
			names1.add(name);
			if(names2.size() == 0)
				first = true;
		}
	}
	/**
	 * Create the frame.
	 */
	public Chooser()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		textPane = new JTextPane();
		contentPane.add(textPane, BorderLayout.CENTER);
		textPane.setText("Enter someone's name.\nEND to stop");
		textField.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(stillNaming)
				{
					if(!textField.getText().equals("END"))
					{
						addName1(textField.getText());
					}
					else
					{
						stillNaming = false;
						textPane.setText("Press enter for a name.");
					}
					textField.setText("");
					textField.grabFocus();
				}
				else
				{
					grabName();
				}
			}
		});
	}
}
