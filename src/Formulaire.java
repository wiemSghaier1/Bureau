import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Formulaire extends MyInternal {

    ArrayList<Profile> data = new ArrayList<Profile>();
    JPanel p1;
    JLabel l1, l2, l3;
    JButton btnval;
    JTextField t1, t2, t3;
    DefaultListModel model;
    JList list;
    JSplitPane jsp;
    JTabbedPane jtp;

    public Formulaire(String ch) {

        super(ch);
        p1 = new JPanel(new GridLayout(1, 7, 1, 1));
        l1 = new JLabel("Nom");
        l2 = new JLabel("Prenom");
        l3 = new JLabel("Pseudo");
        t1 = new JTextField("taper nom",10);
        t2 = new JTextField("taper prenom",10);
        t3 = new JTextField("taper pseudo",10);
        btnval = new JButton("Valider");
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(btnval);
        add(p1, BorderLayout.NORTH);

        model = new DefaultListModel();
        model.addElement("E1");

        list = new JList();
        list.setModel(model);
        list.setPreferredSize(new Dimension(100, 3));

        jsp = new JSplitPane();
        jsp.setLeftComponent(list);

        jtp = new JTabbedPane();
        jtp.addTab("titre", new JPanel());
        jtp.addTab("titre2", new JPanel());

        jsp.setRightComponent(jtp);

        add(jsp, BorderLayout.CENTER);

        //ecouteur d'evenement
        //  btnval.addActionListener(this);
        //utilisation de classe anonyme
        btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t1.getText().equals("taper nom") || t2.getText().equals("taper prenom") || t3.getText().equals("taper pseudo")){
                    Component c= (Component)e.getSource();
                    JOptionPane.showInternalMessageDialog(c,"enter vos information svp!");
                }
                if (!t1.getText().equals("") && !t2.getText().equals("") && !t3.getText().equals("") && !t1.getText().equals("taper nom") && !t2.getText().equals("taper prenom") && !t3.getText().equals("taper pseudo")) {
                    model.addElement(t3.getText());
                    Profile p = new Profile(t1.getText(), t2.getText(), t3.getText());
                    data.add(p);
                }
                if (e.getSource()==btnval){
                    t1.setText("taper nom");
                    t2.setText("taper prenom");
                    t3.setText("taper pseudo");
                }
            }
        });

        //avec classe interne 
        l1.addMouseListener(new EcouteurLabel());
        l2.addMouseListener(new EcouteurLabel());
        l3.addMouseListener(new EcouteurLabel());

        t1.addFocusListener(new FocusLabel());
        t2.addFocusListener(new FocusLabel());
        t3.addFocusListener(new FocusLabel());
        btnval.addFocusListener(new FocusLabel());

    }


  /*  @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnval) {
            if (!t1.getText().equals("") && !t2.getText().equals("") && !t3.getText().equals("")) {
                model.addElement(t3.getText());
                Profile p = new Profile(t1.getText(), t2.getText(), t3.getText());
                data.add(p);
            }
        }*/


    //classe interne
    class EcouteurLabel implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource()== l1){
                l1.setForeground(Color.BLUE);
            }else if(e.getSource()==l2) {
                l2.setForeground(Color.red);
            }else if (e.getSource()== l3){
                l3.setForeground(Color.GREEN);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource()== l1){
                l1.setForeground(Color.WHITE);
            }else if(e.getSource()==l2) {
                l2.setForeground(Color.gray);
            }else if (e.getSource()== l3){
                l3.setForeground(Color.magenta);
            }
        }
    }


    class FocusLabel implements FocusListener {


        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource()==t1)
                t1.setText("");
            if (e.getSource()==t2)
                t2.setText("");
            if (e.getSource()==t3)
                t3.setText("");
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (t1.getText().equals(""))
                t1.setText("taper nom");
            if (t2.getText().equals(""))
                t2.setText("taper prenom");
            if (t3.getText().equals(""))
                t3.setText("taper pseudo");

        }
    }
}
