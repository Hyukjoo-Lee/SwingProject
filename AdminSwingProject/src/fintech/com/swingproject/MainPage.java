package fintech.com.swingproject;

import javax.swing.*;

import fintech.com.swingproject.db.DBCon;
import fintech.com.swingproject.pages.EmployeeAdd;
import fintech.com.swingproject.pages.EmployeeListPage;
import fintech.com.swingproject.pages.JoinPage;
import fintech.com.swingproject.pages.LoginPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 초기세팅: 로그인 + 회원가입 + 조회페이지(CRUD)
 */
public class MainPage {
	public static void main(String[] args) {

		// 디비 연결
		DBCon dbObj = new DBCon();
		// 유저 테이블 생성
		dbObj.createUserTable();

		JFrame frame = new JFrame("어드민 시스템");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 460);

		CardLayout cardLayout = new CardLayout();
		JPanel mainPanel = new JPanel(cardLayout);

		LoginPage loginPage = new LoginPage(cardLayout, mainPanel);
		JoinPage joinPage = new JoinPage(cardLayout, mainPanel);
		EmployeeListPage listPage = new EmployeeListPage(cardLayout, mainPanel);
		EmployeeAdd addPage = new EmployeeAdd(cardLayout, mainPanel);

//		mainPanel.add(signUpPage, "회원가입");
		mainPanel.add(loginPage, "로그인");
		mainPanel.add(joinPage, "회원가입");
		mainPanel.add(listPage, "조회");
		mainPanel.add(addPage, "추가");

		JMenuBar menuBar = new JMenuBar();

		JMenu menu1 = new JMenu("로그인");
		JMenuItem menuItem2 = new JMenuItem("로그인 이동");
		menuItem2.addActionListener(e -> cardLayout.show(mainPanel, "로그인"));
		menu1.add(menuItem2);

		JMenu menu2 = new JMenu("회원가입");
		JMenuItem menuItem1 = new JMenuItem("회원가입 이동");
		menuItem1.addActionListener(e -> cardLayout.show(mainPanel, "회원가입"));
		menu2.add(menuItem1);

		JMenu menu3 = new JMenu("조회");
		JMenuItem menuItem3 = new JMenuItem("조회 이동");
		menuItem3.addActionListener(e -> cardLayout.show(mainPanel, "조회"));
		menu3.add(menuItem3);

		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);

		frame.setJMenuBar(menuBar);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setVisible(true);

	}
}
