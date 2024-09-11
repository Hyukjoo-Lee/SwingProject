import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinForm extends JFrame {

    public JoinForm() {
       
        setTitle("회원가입");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 배치
        setLayout(null);

       
        JLabel nameLabel = new JLabel("이름:");
        JTextField nameField = new JTextField(20);

        JLabel emailLabel = new JLabel("이메일:");
        JTextField emailField = new JTextField(20);

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

       
        nameLabel.setBounds(50, 30, 80, 25);
        nameField.setBounds(150, 30, 165, 25);

        emailLabel.setBounds(50, 70, 80, 25);
        emailField.setBounds(150, 70, 165, 25);

        phoneLabel.setBounds(50, 110, 80, 25);
        phoneField.setBounds(150, 110, 165, 25);

        deptLabel.setBounds(50, 150, 80, 25);
        deptComboBox.setBounds(150, 150, 165, 25);

       
        int buttonWidth = 80;
        int buttonHeight = 25;
        int frameWidth = 400;
        int frameHeight = 300;
        
        int buttonX = (frameWidth - buttonWidth) / 2;
        int buttonY = 190; 

        submitButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

       
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(phoneLabel);
        add(phoneField);
        add(deptLabel);
        add(deptComboBox);
        add(submitButton);

     
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String department = (String) deptComboBox.getSelectedItem();

                JOptionPane.showMessageDialog(null, "회원가입 완료: " + name);

                dispose(); 
                new LoginForm(); 
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new JoinForm();
    }
}
