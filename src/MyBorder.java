import java.awt.BorderLayout;

import javax.swing.JButton;

public class MyBorder extends MyInternal{
	
	JButton b1,b2,b3,b4,b5;
	
	public MyBorder(String ch) {
		super(ch);
		b1 = new JButton("b1");
		b2 = new JButton("b2");
		b3 = new JButton("b3");
		b4 = new JButton("b4");
		b5 = new JButton("b5");
		this.setLayout(new BorderLayout());
		this.add(b1,BorderLayout.NORTH);
		this.add(b2,BorderLayout.SOUTH);
		this.add(b3,BorderLayout.WEST);
		this.add(b4,BorderLayout.EAST);
		this.add(b5,BorderLayout.CENTER);
		
	}

}
