package fintech.com.swingproject.pages;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fintech.com.swingproject.db.DBCon;

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
        		String username = textId.getText().trim();
        		String password = new String(textPw.getPassword()).trim();
        		
        		if(DBCon.validateUser(username, password)) {
        			//로그인 성공 시 EmployeeListPage로 전환
        			System.out.println("작동");
        			cardLayout.show(mainPanel, "조회");
        			JOptionPane.showMessageDialog(LoginPage.this, "로그인 성공!");
        		}else {
        			JOptionPane.showMessageDialog(LoginPage.this, "아이디 또는 비밀번호가 일치하지 않습니다.");
        		}
        	}
        });
        
        // Enter 키로 로그인 버튼 클릭 이벤트 발생
        textId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               textPw.requestFocus(); // 엔터치면 다음 입력창으로 넘어
            }
        });
        
        textPw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnlogin.doClick(); // 엔터치면 로그인;
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