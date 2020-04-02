import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Myjeu extends MyInternal implements ActionListener {
    JLabel lb, lb1, lb2;
    JPanel pc, pw, pw1, pc1, pc2, pc3;
    JButton btnnew , btntest, btnquit;
    JTextField t1;

    public Myjeu(String ch){
        super(ch);
        lb =  new JLabel("Bienvenue");
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setVerticalAlignment(SwingConstants.CENTER);
        add(lb, BorderLayout.NORTH);

        pw = new JPanel(new FlowLayout());
        pw1 = new JPanel(new GridLayout(3,1,1,1));
        btnnew = new JButton("new");
        btntest = new JButton("test");
        btnquit = new JButton("Quitter");
        pw1.add(btnnew);
        pw1.add(btntest);
        pw1.add(btnquit);
        pw.add(pw1);
        add(pw, BorderLayout.WEST);

        pc = new JPanel(new GridLayout(2,1,1,1));
        lb1 = new JLabel("saisir une valeur :");
        pc1 = new JPanel();
        pc2 = new JPanel();
        pc3 = new JPanel();
        t1 = new JTextField();
        t1.setPreferredSize(new Dimension(150,30));
        lb2 = new JLabel("votre valeur est:");
        pc1.add(lb1);
        pc2.add(t1);
        pc3.add(lb2);
        pc.add(pc1);
        pc.add(pc2);
        pc.add(pc3);
        add(pc,BorderLayout.CENTER);

        //ecouteur
        btnquit.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnquit)
            System.exit(0);
    }
}
