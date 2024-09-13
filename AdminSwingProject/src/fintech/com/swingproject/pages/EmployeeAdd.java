package fintech.com.swingproject.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EmployeeAdd extends JPanel {
	private JTextField nameField;
	private JTextField departmentField;
	private JTextField positionField;
	private JTextField idField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField statusField;

	public EmployeeAdd(CardLayout cardLayout, JPanel mainJPanel) {
		setLayout(new BorderLayout());
		initializeComponents();
		setVisible(true);
	}

	private void initializeComponents() {
		add(createInputPanel(), BorderLayout.NORTH);
		//add(createTablePanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
	}

	// 입력 필드를 포함하는 패널 생성
	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel(new GridLayout(7,2));

		// 레이블과 입력 필드를 패널에 추가
		nameField = new JTextField();
		departmentField = new JTextField();
		positionField = new JTextField();
		idField = new JTextField();
		emailField = new JTextField();
		phoneField = new JTextField();
		statusField = new JTextField();
		
		// 레이블과 입력 필드를 패널에 추가
		inputPanel.add(new JLabel("이름 : "));
		inputPanel.add(nameField);
		inputPanel.add(new JLabel("소속 : "));
		inputPanel.add(departmentField);
		inputPanel.add(new JLabel("직급 : "));
		inputPanel.add(positionField);
		inputPanel.add(new JLabel("아이디 : "));
		inputPanel.add(idField);
		inputPanel.add(new JLabel("이메일 : "));
//		inputPanel.add(new JLabel("이메일 : "));
		inputPanel.add(emailField);
		inputPanel.add(new JLabel("전화번호 : "));
		inputPanel.add(phoneField);
		inputPanel.add(new JLabel("상태 : "));
		inputPanel.add(statusField);

		return inputPanel;
	}
	
	/*
	// 테이블을 포함하는 패널 생성
	private JScrollPane createTablePanel() {
		String[] columnNames = {"이름", "소속", "직급", "아이디", "이메일", "전화번호", "상태"};
		model = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(model);
		return new JScrollPane(table);
	}
	*/

	// 버튼을 포함하는 패널 생성
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		JButton saveButton = new JButton("저장");	

		// 버튼 클릭 시 동작 정의
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 입력 필드에서 데이터 가져오기
				String name = nameField.getText();
				String department = departmentField.getText();
				String position = positionField.getText();
				String id = idField.getText();
				String email = emailField.getText();
				String phone = phoneField.getText();
				String status = statusField.getText();
				
				// 직원 정보를 EmployeeListPage에 추가
				EmployeeListPage.addEmployee(name, department, position, id, email, phone, status);
				clearInputFields();
			}
		});
		
		buttonPanel.add(saveButton);
		return buttonPanel;
	}

	// 입력 필드 초기화 메서드
	private void clearInputFields() {
		nameField.setText("");
		departmentField.setText("");
		positionField.setText("");
		idField.setText("");
		emailField.setText("");
		phoneField.setText("");
		statusField.setText("");
	}
}