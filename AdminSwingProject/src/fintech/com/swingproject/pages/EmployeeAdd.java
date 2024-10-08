package fintech.com.swingproject.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EmployeeAdd extends JPanel {
	private JTextField nameField;
	private JTextField passwordField;
	private JTextField departmentField;
	private JTextField positionField;
	private JTextField emailField;
	private JTextField phoneField;
	private JCheckBox statusCheckBox;

	public EmployeeAdd(CardLayout cardLayout, JPanel mainJPanel) {
		setLayout(new BorderLayout());
		initializeComponents(cardLayout, mainJPanel);
		setVisible(true);
	}

    private void initializeComponents(CardLayout cardLayout, JPanel mainJPanel) {
        add(createInputPanel(), BorderLayout.NORTH);
        add(createButtonPanel(cardLayout, mainJPanel), BorderLayout.SOUTH);
    }
    
	// 입력 필드를 포함하는 패널 생성
	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel(new GridLayout(7,2));

		// 레이블과 입력 필드를 패널에 추가
		nameField = new JTextField();
		passwordField = new JTextField();
		departmentField = new JTextField();
		positionField = new JTextField();
		emailField = new JTextField();
		phoneField = new JTextField();
		statusCheckBox = new JCheckBox();
		
		// 레이블과 입력 필드를 패널에 추가
		inputPanel.add(new JLabel("이름 : "));
		inputPanel.add(nameField);
		inputPanel.add(new JLabel("비밀번호 : "));
		inputPanel.add(passwordField);
		inputPanel.add(new JLabel("소속 : "));
		inputPanel.add(departmentField);
		inputPanel.add(new JLabel("직급 : "));
		inputPanel.add(positionField);
		inputPanel.add(new JLabel("이메일 : "));
		inputPanel.add(emailField);
		inputPanel.add(new JLabel("전화번호 : "));
		inputPanel.add(phoneField);
		inputPanel.add(new JLabel("상태 : "));
		inputPanel.add(statusCheckBox);

		return inputPanel;
	}

	// 버튼을 포함하는 패널 생성
	 private JPanel createButtonPanel(CardLayout cardLayout, JPanel mainJPanel) {
		JPanel buttonPanel = new JPanel();
		JButton saveButton = new JButton("저장");	
		JButton exitButton = new JButton("나가기");
		
		// 버튼 클릭 시 동작 정의
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 입력 필드에서 데이터 가져오기
				String name = nameField.getText();
				String password = passwordField.getText();
				String department = departmentField.getText();
				String position = positionField.getText();
				String email = emailField.getText();
				String phone = phoneField.getText();
				boolean status = statusCheckBox.isSelected();
				
				// 직원 정보를 EmployeeListPage에 추가
				EmployeeListPage.addEmployee(name, password, department, position, email, phone, status);
				clearInputFields();
			}
		});
		
        // 나가기 버튼 클릭 시 동작 정의
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainJPanel, "조회"); 
            }
        });
		
		buttonPanel.add(saveButton);
		buttonPanel.add(exitButton);
		return buttonPanel;
	}

	// 입력 필드 초기화 메서드
	private void clearInputFields() {
		nameField.setText("");
		departmentField.setText("");
		positionField.setText("");
		passwordField.setText("");
		emailField.setText("");
		phoneField.setText("");
		statusCheckBox.setSelected(false);
	}
}