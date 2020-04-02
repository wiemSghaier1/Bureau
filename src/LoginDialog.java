import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginDialog extends JDialog{

    private  JPasswordField jPasswordField;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JPanel panel,p1,p2,p3;
    Boolean authentified;

    public LoginDialog(Frame parent){

        super(parent,"Login",true);
        super.setSize(300,300);
        super.setLocation(200,200);
        super.setResizable(false);
        super.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        authentified = false;

        panel = new JPanel(new BorderLayout());

        p1 = new JPanel(new GridLayout(2,1));
        lbPassword = new JLabel("enter your password :");


        jPasswordField = new JPasswordField(20);
        jPasswordField.setBounds(10,10,10,10);
        p1.add(lbPassword);
        p3 = new JPanel();
        p3.add(jPasswordField);
        p3.setPreferredSize(new Dimension(100,30));
        p1.add(p3);


        p2 = new JPanel(new GridLayout(1,2));
        btnLogin = new JButton("Login");
        btnCancel = new JButton("Cancel");
        p2.add(btnLogin);
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
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] password = jPasswordField.getPassword();
                char[] correctpass = new char[] {'i','s','s','a','t','s','o'};
                if (Arrays.equals(password,correctpass)){
                    authentified = true;
                    dispose();

                }else {
                    authentified = false;
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Invalid password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset password
                    jPasswordField.setText("");
                }
            }
        });

    }

}
