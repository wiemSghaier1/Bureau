import javax.swing.JButton;

public class MyNullLayout extends MyInternal{
	JButton b1,b2,b3,b4,b5;
	public MyNullLayout(String ch) {
		super(ch);
		setLayout(null);
		b1 = new JButton("b1");
		b1.setBounds(10,10,100,100);
		add(b1);
		b2 = new JButton("b2");
		b2.setBounds(20, 20, 100, 100);
		add(b2);
		b3 = new JButton("b3");
		b3.setBounds(30, 30, 100, 100);
		add(b3);
		b4 = new JButton("b4");
		b4.setBounds(40, 40, 100, 100);
		add(b4);
		b5 = new JButton("b5");
		b5.setBounds(50, 50, 100, 100);
		add(b5);
	}

}
