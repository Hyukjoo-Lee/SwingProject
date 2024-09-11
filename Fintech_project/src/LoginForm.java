import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JLabel labelId, labelPw;
    private JButton btnlogin, btnjoin;
    private JTextField textId;
    private JPasswordField textPw;

    public LoginForm() {
        
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(null); 

       
        JPanel panel = new JPanel();
        panel.setLayout(null); // 자유 배치를 위해 null 레이아웃 사용
        panel.setBounds(0, 0, 700, 400);//0,0=> 제일 상단에 맞춘다 
        add(panel);

       
        ImageIcon icon = new ImageIcon("./img/background.png");
        int width = 400;
        int height = 400;
        Image img = icon.getImage(); 
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

       
        JLabel labelicon = new JLabel(scaledIcon);
        labelicon.setBounds(0, 0, width, height); 
        panel.add(labelicon); 

        int formXStart = 350; 
        
        
        labelId = new JLabel("ID");
        labelId.setBounds(formXStart+80, 150, 80, 25);
        panel.add(labelId);

        textId = new JTextField(10);
        textId.setBounds(formXStart + 150, 150, 150, 25);
        panel.add(textId);

       
        labelPw = new JLabel("Password");
        labelPw.setBounds(formXStart+80, 190, 80, 25);
        panel.add(labelPw);

        textPw = new JPasswordField(10);
        textPw.setBounds(formXStart + 150, 190, 150, 25);
        panel.add(textPw);

       
        btnlogin = new JButton("로그인");
        btnlogin.setBounds(formXStart + 80, 230, 100, 30);
        panel.add(btnlogin);

       
        btnjoin = new JButton("회원가입");
        btnjoin.setBounds(formXStart + 190, 230, 100, 30);
        panel.add(btnjoin);
        
        
        btnjoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();

               
                new JoinForm();
            }
        });

        
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
