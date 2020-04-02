import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RenameDialog extends JDialog {
    private  JTextField pseudo;
    private JLabel lbPseudo;
    private JButton btnRename;
    private JButton btnCancel;
    private JPanel panel,p1,p2,p3;
    private TestEvenement testEvenement;
    public String newpseudo;

    public RenameDialog(Frame parent){

        super(parent,"Rename",true);
        super.setSize(200,200);
        super.setLocation(200,200);
        super.setResizable(false);
        super.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel(new BorderLayout());

        p1 = new JPanel(new GridLayout(2,1));
        lbPseudo = new JLabel("enter your new Pseudo :");


        pseudo = new JTextField(10);
        pseudo.setBounds(10,10,10,10);
        p1.add(lbPseudo);
        p3 = new JPanel();
        p3.add(pseudo);
        p3.setPreferredSize(new Dimension(100,30));
        p1.add(p3);


        p2 = new JPanel(new GridLayout(1,2));
        btnRename = new JButton("Rename");
        btnCancel = new JButton("Cancel");
        p2.add(btnRename);
        p2.add(btnCancel);

        panel.add(p1,BorderLayout.CENTER);
        panel.add(p2,BorderLayout.SOUTH);
        add(panel);


        //listners
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnRename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pseudo.getText().equals("")){
                    newpseudo = pseudo.getText();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(RenameDialog.this,
                            "Please enter your new Pseudo",
                            "Rename",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
