package fintech.com.swingproject.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fintech.com.swingproject.db.DBCon;
import fintech.com.swingproject.model.User;

/**
 * 디비 연동을 위한 테스팅 페이지
 */
public class SignUpPage extends JPanel {
	private JTextField userNameField;
	private JPasswordField userPasswordField;

	public SignUpPage(CardLayout cardLayout, JPanel mainPanel) {

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// 사용자 계정, 패스워드 레이블과 필드 생성
		JLabel userNameLabel = new JLabel("아이디: ");
		userNameField = new JTextField();
		userNameField.setPreferredSize(new Dimension(150, 25)); // 필드 크기 지정

		JLabel userPasswordLabel = new JLabel("비밀번호: ");
		userPasswordField = new JPasswordField();
		userPasswordField.setPreferredSize(new Dimension(150, 25));

		// 회원가입 버튼
		JButton signUpButton = new JButton("회원가입");

		// 페이지 이동 버튼
		JButton movePageButton = new JButton("페이지 2로 이동");
		movePageButton.addActionListener(e -> cardLayout.show(mainPanel, "페이지 2"));

		// 컴포넌트들을 중앙에 배치
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		add(userNameLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		add(userNameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(userPasswordLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		add(userPasswordField, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		add(signUpButton, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		add(movePageButton, gbc);

		// 회원가입 버튼 Action Listener 추가
		signUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 유저네임, 패스워드 필드에서 받아옴
				String username = userNameField.getText();
				char[] passwordChars = userPasswordField.getPassword();
				String password = new String(passwordChars);
				
				// 디비 연결 후 디비 INSERT
				DBCon dbCon = new DBCon();
				dbCon.insertUser(new User(username, password));
			}
		});
	}
}
