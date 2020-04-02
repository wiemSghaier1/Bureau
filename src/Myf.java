import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Myf extends JFrame implements ActionListener {


	JMenuBar bar;
	JMenu tp0, tp1, tp2, tp3;
	JMenuItem ex1, ex2, border, flow, jeux, grid, NULL,formulaire,testEvenement;
	JLabel lannee;
	JDesktopPane desk;
	
	public Myf(String ch) {
		super(ch);
		//setTitle(ch);
        setSize(850,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
		bar = new JMenuBar();
		tp0 = new JMenu("tp0");
		tp1 = new JMenu("tp1");
		tp2 =  new JMenu("tp2");
		tp3 = new JMenu("tp3");
		ex1 = new JMenuItem("ex1");
		ex2 = new JMenuItem("ex2");
		formulaire = new JMenuItem("Formulaire");
		testEvenement =  new JMenuItem("Test Evenement");
		border = new JMenuItem("border");
		flow = new JMenuItem("flow");
		jeux = new JMenuItem("jeux");
		grid = new JMenuItem("grid");
		NULL = new JMenuItem("NULL");
		tp0.add(ex1);
		tp0.add(ex2);
		tp1.add(border);
		tp1.add(flow);
		tp1.add(jeux);
		tp1.add(grid);
		tp1.add(NULL);
		tp2.add(formulaire);
		tp3.add(testEvenement);
		bar.add(tp0);
		bar.add(tp1);
		bar.add(tp2);
		bar.add(tp3);
		this.setJMenuBar(bar);
		
		lannee = new JLabel("annee universitaire 2019/2020");
		lannee.setHorizontalAlignment(JLabel.RIGHT);
		this.add(lannee,BorderLayout.SOUTH);
		
		desk = new JDesktopPane();
		this.add(desk,BorderLayout.CENTER);
		
		//ecouteur
		ex1.addActionListener(this);
		ex2.addActionListener(this);
		border.addActionListener(this);
		flow.addActionListener(this);
		grid.addActionListener(this);
		NULL.addActionListener(this);
		jeux.addActionListener(this);
		formulaire.addActionListener(this);
		testEvenement.addActionListener(this);

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 System.out.println("Action");
		 JMenuItem s = (JMenuItem)e.getSource();
		 switch (s.getText()) {
			 case "ex1":
				 MyInternal i = new MyInternal("ex1");
				 desk.add(i);
				 i.toFront();
				 break;
			 case "ex2":
				 MyInternal i1 = new MyInternal("ex2");
				 desk.add(i1);
				 i1.toFront();
				 break;

			 case "border":
				 MyInternal i2 = new MyInternal("border");
				 desk.add(i2);
				 i2.toFront();
				 MyBorder mb = new MyBorder("myborder");
				 i2.add(mb);
				 break;

			 case "flow":
				 MyInternal i3 = new MyInternal("flow");
				 desk.add(i3);
				 i3.toFront();
				 MyFlow mf = new MyFlow("My Flow");
				 i3.add(mf);
				 break;

			 case "grid":
				 MyInternal i4 = new MyInternal("grid");
				 desk.add(i4);
				 i4.toFront();
				 MyGrid mg = new MyGrid("My Grid");
				 i4.add(mg);
				 break;
			 case "jeux":
				 MyInternal i5 = new MyInternal("jeux");
				 desk.add(i5);
				 i5.toFront();
				 Myjeu j = new Myjeu("Jeu Devinette");
				 i5.add(j);
				 break;
			 case "NULL":
				 MyNullLayout mnl = new MyNullLayout("null layout");
				 desk.add(mnl);
				 break;
			 case "Formulaire":
				 Formulaire form = new Formulaire("Formulaire");
				 desk.add(form);
				 break;
             case "Test Evenement":
             	 LoginDialog loginDialog = new LoginDialog(null);
             	 loginDialog.setVisible(true);
             	 if (loginDialog.authentified){
					 TestEvenement testEvent = new TestEvenement("Test Evenement");
					 desk.add(testEvent);
				 }
                 break;
			 default:
				 break;
		 }
	}
	

}
