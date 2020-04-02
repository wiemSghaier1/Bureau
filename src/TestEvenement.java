import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class TestEvenement extends MyInternal {

    ArrayList<Profile> data = new ArrayList<Profile>();
    JPanel p1;
    JLabel l1, l2, l3,l,ll,lll;
    JButton btnval;
    JTextField t1, t2, t3;
    DefaultListModel model;
    JList<Profile> list = new JList<Profile>();
    JSplitPane jsp;
    JTabbedPane jtp;
    JPopupMenu popupMenu;
    JMenuItem mi1,mi2,mi3;
    MyInternal myInternal;
    MyConnection c;


    public TestEvenement(String ch) {
        super(ch);
        super.setSize(800,600);
        super.setResizable(false);
        c = new MyConnection();
        c.connect();


        p1 = new JPanel(new FlowLayout());
        l1 = new JLabel("Nom");
        l2 = new JLabel("Prenom");
        l3 = new JLabel("Pseudo");
        l = new JLabel("*");
        ll = new JLabel("*");
        lll = new JLabel("*");
        l.setForeground(Color.red);
        ll.setForeground(Color.red);
        lll.setForeground(Color.red);
        t1 = new JTextField("taper nom",15);
        t1.setHorizontalAlignment(JTextField.CENTER);
        t2 = new JTextField("taper prenom",15);
        t2.setHorizontalAlignment(JTextField.CENTER);
        t3 = new JTextField("taper pseudo",15);
        t3.setHorizontalAlignment(JTextField.CENTER);
        btnval = new JButton("Valider");
        p1.add(l1);
        p1.add(t1);
        p1.add(l);
        p1.add(l2);
        p1.add(t2);
        p1.add(ll);
        p1.add(l3);
        p1.add(t3);
        p1.add(lll);
        p1.add(btnval);
        add(p1, BorderLayout.NORTH);

        model = new DefaultListModel();
        model.addElement("FI");


        list.setModel(model);
        list.setPreferredSize(new Dimension(100, 3));

        //listner to add tooltiptext on jlist
        list.getSelectionModel().addListSelectionListener(e -> {
            try {
                if (!list.isSelectionEmpty()) {
                    Profile p = list.getSelectedValue();
                    list.setToolTipText(p.getNom() + " " + p.getPrenom());
                }
            }catch (Exception exc){
                System.out.println("there is a problem here");
            }


        });


        //set the popupmenu
        popupMenu = new JPopupMenu();

        mi1 = new JMenuItem("Supprimer");
        mi2 = new JMenuItem("Supprimer tous");
        mi3 = new JMenuItem("Renommer");

        popupMenu.add(mi1);
        popupMenu.add(mi2);
        popupMenu.add(mi3);

        list.setComponentPopupMenu(popupMenu);

        //add Listner on popupmenu items
        mi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel model = (DefaultListModel) list.getModel();
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    c.remove(list.getSelectedValue());
                    model.removeElement(list.getSelectedValue());
                    data.remove(list.getSelectedValue());

                }
            }
        });
        mi2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel model = (DefaultListModel) list.getModel();
                c.removeall();
                jtp.removeAll();
                model.removeAllElements();
                data.clear();
                model.addElement("FI");
                jtp.addTab("FI",new JPanel());
            }
        });
        mi3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RenameDialog renameDialog = new RenameDialog(null);
                renameDialog.setVisible(true);
                list.getSelectedValue().setPseudo(renameDialog.newpseudo);
                list.getSelectedValue().getPseudo();

            }
        });

        //add listner par double click sur les elements de Jlist
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JList<Profile> list = (JList)e.getSource();

                if ((e.getClickCount() == 2) ) {

                    // Double-click detected
                    jtp.addTab(list.getSelectedValue().getPseudo(),
                            new PersonlisedTab(list.getSelectedValue().getNom(),list.getSelectedValue().getPrenom(),list.getSelectedValue().getPseudo()));
                    
                    int index = list.locationToIndex(e.getPoint());
                }


            }
        });

        //add the JsplitPane
        jsp = new JSplitPane();
        jsp.setLeftComponent(list);
        //add the JTappedPane
        jtp = new JTabbedPane();
        jtp.addTab("FI", new JPanel());

        jsp.setRightComponent(jtp);

        add(jsp, BorderLayout.CENTER);


        //utilisation de classe anonyme pour valider l'ajout d'une personne
        btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t1.getText().equals("taper nom") || t2.getText().equals("taper prenom") || t3.getText().equals("taper pseudo")){
                    Component c= (Component)e.getSource();
                    JOptionPane.showInternalMessageDialog(c,"enter vos information svp!");
                }
                if (!t1.getText().equals("") && !t2.getText().equals("") && !t3.getText().equals("") &&
                        !t1.getText().equals("taper nom") && !t2.getText().equals("taper prenom") &&
                        !t3.getText().equals("taper pseudo")) {
                  //  model.addElement(t3.getText());
                    Profile p = new Profile(t1.getText(), t2.getText(), t3.getText());
                    model.addElement(p);
                    c.ajout(t1.getText(),t2.getText(),t3.getText());
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

    class PersonlisedTab extends JPanel{

        JPanel pn,pc,ps,pc1,pc2,pp,pcenter;
        JLabel l1,lc,lc2,lc3;
        JComboBox<String> cb;
        JCheckBox n1,n2,n3,n4,n5,n6,n7,o1,o2,o3,o4;
        JButton bval;

        public PersonlisedTab(String nom,String prenom, String pseudo){

            pp = new JPanel(new BorderLayout()); //paneau principale

            //paneau nord
            pn = new JPanel();
            l1 = new JLabel("Bienvenue "+nom+" "+prenom, JLabel.CENTER);
            l1.setPreferredSize(new Dimension(400,80));
            pn.add(l1);
            pn.setBackground(Color.GREEN);
            pn.setBounds(0,0,1000,100);

            pcenter = new JPanel(new GridLayout(2,1)); //paneau centre

            //paneau centre 1
            pc = new JPanel(new GridLayout(3,1));
            lc = new JLabel("Difficulté : ");
            JPanel inter = new JPanel(new GridLayout(1,3));
            JLabel linter1 = new JLabel(" ");
            JLabel linter2 = new JLabel(" ");
            cb = new JComboBox<>();
            cb.addItem("Facile");
            cb.addItem("Intermédiaire");
            cb.addItem("Advanced");
            cb.setPreferredSize(new Dimension(30,30));
            inter.add(linter1);
            inter.add(cb);
            inter.add(linter2);
            pc1 = new JPanel(new FlowLayout());
            lc2 = new JLabel("choisir la/les catégorie(s):");
            n1 = new JCheckBox("1"); n1.setEnabled(false);
            n2 = new JCheckBox("2"); n2.setEnabled(false);
            n3 = new JCheckBox("3"); n3.setEnabled(false);
            n4 = new JCheckBox("4"); n4.setEnabled(false);
            n5 = new JCheckBox("5"); n5.setEnabled(false);
            n6 = new JCheckBox("6"); n6.setEnabled(false);
            n7 = new JCheckBox("7"); n7.setEnabled(false);
            pc1.add(lc2);
            pc1.add(n1);
            pc1.add(n2);
            pc1.add(n3);
            pc1.add(n4);
            pc1.add(n5);
            pc1.add(n6);
            pc1.add(n7);
            pc.add(lc);
            pc.add(inter);
            pc.add(pc1);
            pc.setBorder(BorderFactory.createLineBorder(Color.red));

            //paneau centre 2
            pc2 = new JPanel(new GridLayout(2,1));
            lc3 = new JLabel("Option: 2");
            JPanel pan = new JPanel(new FlowLayout());
            o1 = new JCheckBox("Emetteur son");
            o2 = new JCheckBox("Afficher score");
            o3 = new JCheckBox("Plein Ecran");
            o4 = new JCheckBox("Ombre");
            pan.add(o1);
            pan.add(o2);
            pan.add(o3);
            pan.add(o4);
            pc2.add(lc3);
            pc2.add(pan);
            pc2.setBorder(BorderFactory.createLineBorder(Color.red));

            //add des paneau centre intermédiaire dans le paneau centre principale
            pcenter.add(pc);
            pcenter.add(pc2);


            //paneau sud
            ps = new JPanel();
            bval = new JButton("Valider");
            ps.add(bval);

            //ajout des paneaux dans le paneau principale
            pp.add(pn,BorderLayout.NORTH);
            pp.add(pcenter, BorderLayout.CENTER);
            pp.add(ps, BorderLayout.SOUTH);
            add(pp);


            //add listner on the JcomboBox
            cb.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    String getItem = (String) cb.getSelectedItem();
                    if (getItem.equals("Facile")){
                        n1.setEnabled(true); n1.setSelected(false);
                        n2.setEnabled(true); n2.setSelected(false);
                        n3.setSelected(false); n3.setEnabled(false);
                        n4.setSelected(false); n4.setEnabled(false);
                        n5.setSelected(false); n5.setEnabled(false);
                        n6.setSelected(false); n6.setEnabled(false);
                        n7.setSelected(false); n7.setEnabled(false);
                    }else if (getItem.equals("Intermédiaire")){
                        n1.setSelected(true); n1.setEnabled(false);
                        n2.setSelected(true); n2.setEnabled(false);
                        n3.setSelected(false) ;n3.setEnabled(true);
                        n4.setSelected(false); n4.setEnabled(true);
                        n5.setSelected(false); n5.setEnabled(false);
                        n6.setSelected(false); n6.setEnabled(false);
                        n7.setSelected(false); n7.setEnabled(false);
                    }else if (getItem.equals("Advanced")){
                        n1.setSelected(true); n1.setEnabled(false);
                        n2.setSelected(true); n2.setEnabled(false);
                        n3.setSelected(true); n3.setEnabled(false);
                        n4.setSelected(true); n4.setEnabled(false);
                        n5.setSelected(false); n5.setEnabled(true);
                        n6.setSelected(false); n6.setEnabled(true);
                        n7.setSelected(false); n7.setEnabled(true);
                    }
                }
            });

            //add listner on valider button
            bval.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        HTMLPage htmlPage = new HTMLPage(nom,prenom,pseudo,(String)cb.getSelectedItem(),maxcateg(),o1.isSelected(),o2.isSelected(),o3.isSelected(),o4.isSelected());
                    } catch (IOException ex) {
                        System.out.println("erreur construction html page");
                        ex.printStackTrace();
                    }

                }
            });

        }

        //methode qui retourne le plus grande categorie cocher
        public int maxcateg(){
            if (n7.isSelected())
                return 7;
            else if (n6.isSelected())
                return 6;
            else if (n5.isSelected())
                return 5;
            else if (n4.isSelected())
                return 4;
            else if (n3.isSelected())
                return 3;
            else if (n2.isSelected())
                return 2;
            else if (n1.isSelected())
                return 1;
            return 0;
        }

    }
}
