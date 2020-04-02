import javax.swing.JInternalFrame;

public class MyInternal extends JInternalFrame {
	
	public MyInternal(String ch) {
		this.setVisible(true);
		this.setSize(750,600);
		this.setLocation(10,10);
		this.setIconifiable(true);
		this.setResizable(true);
		this.setClosable(true);
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setTitle(ch);
	}

}
