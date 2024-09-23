package fintech.com.swingproject.pages;

import javax.swing.*;

import fintech.com.swingproject.db.DBCon;
import fintech.com.swingproject.model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 회원가입 페이지 구현
public class JoinPage extends JPanel {

	public JoinPage(CardLayout cardLayout, JPanel mainPanel) {
		setLayout(null);

		JLabel nameLabel = new JLabel("이름:");
		JTextField nameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("패스워드:");
        JPasswordField passwordField = new JPasswordField(20);

		JLabel phoneLabel = new JLabel("전화번호:");
		JTextField phoneField = new JTextField(20);

		JLabel deptLabel = new JLabel("부서:");
		JComboBox<String> deptComboBox = new JComboBox<>();

		deptComboBox.addItem("관리");
		deptComboBox.addItem("개발");
		deptComboBox.addItem("영업");
		deptComboBox.addItem("인사");
		deptComboBox.addItem("생산");

		JButton submitButton = new JButton("가입");
		JButton backButton = new JButton("돌아가기");
		
		// 중앙에 맞추어 위치 설정
		nameLabel.setBounds(190, 120, 80, 25);
		nameField.setBounds(290, 120, 165, 25);

		passwordLabel.setBounds(190, 150, 80, 25);
		passwordField.setBounds(290, 150, 165, 25);

		phoneLabel.setBounds(190, 180, 80, 25);
		phoneField.setBounds(290, 180, 165, 25);

		deptLabel.setBounds(190, 210, 80, 25);
		deptComboBox.setBounds(290, 210, 165, 25);
        
		submitButton.setBounds(220, 260, 100, 30);
		backButton.setBounds(330, 260, 100, 30);

		add(nameLabel);
		add(nameField);
		add(passwordLabel);
		add(passwordField);
		add(phoneLabel);
		add(phoneField);
		add(deptLabel);
		add(deptComboBox);
		add(submitButton);
		add(backButton);

		// 회원가입 버튼 클릭 시 처리
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
				String phone = phoneField.getText();
				String department = (String) deptComboBox.getSelectedItem();
				
				// 디비 연결 후 디비 INSERT
				DBCon dbCon = new DBCon();
				dbCon.joinUser(new User(name, password,phone,department));
				JOptionPane.showMessageDialog(null, "회원가입 완료: " + name + "비번: " +password);
				cardLayout.show(mainPanel, "로그인");
			}
		});
	      // 회원가입 버튼 클릭 시 로그인 페이지로 전환
		backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "로그인");
            }
        });
	}
}