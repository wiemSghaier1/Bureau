import java.awt.GridLayout;

import javax.swing.JButton;

public class MyGrid extends MyInternal {
	JButton b1,b2,b3,b4,b5;
	public MyGrid(String ch) {
		super(ch);
		b1 = new JButton("b1"); add(b1);
		b2 = new JButton("b2"); add(b2);
		b3 = new JButton("b3"); add(b3);
		b4 = new JButton("b4"); add(b4);
		b5 = new JButton("b5"); add(b5);
		this.setLayout(new GridLayout(2,3,10,10));
		
	}

}
