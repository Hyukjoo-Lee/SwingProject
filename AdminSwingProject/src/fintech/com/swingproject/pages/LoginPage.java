package fintech.com.swingproject.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 로그인 페이지 구현
public class LoginPage extends JPanel {
    private JLabel labelId, labelPw;
    private JButton btnlogin, btnjoin;
    private JTextField textId;
    private JPasswordField textPw;

    public LoginPage(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, 700, 400); 
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
        labelId.setBounds(formXStart + 80, 150, 80, 25);
        panel.add(labelId);

        textId = new JTextField(10);
        textId.setBounds(formXStart + 150, 150, 150, 25);
        panel.add(textId);

        labelPw = new JLabel("Password");
        labelPw.setBounds(formXStart + 80, 190, 80, 25);
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

        // 로그인 버튼 클릭 시 조회 페이지로 전환 
        btnlogin.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		// 유저이름, 패스워드 매칭 필요
        		System.out.println("작동");
        		cardLayout.show(mainPanel, "조회");
        	}
        });
        
        // 회원가입 버튼 클릭 시 회원가입 페이지로 전환
        btnjoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("작동");
                cardLayout.show(mainPanel, "회원가입");
            }
        });

    }
}