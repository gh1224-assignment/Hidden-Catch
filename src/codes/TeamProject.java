package codes;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TeamProject extends JFrame {
	Container cp = getContentPane();
	Tasks tasks = new Tasks(this, cp);
	
	String[] taskExp = setExplanations();
	
	public static void main(String[] args) {
		new TeamProject();
	}
	
	TeamProject() {
		setTitle("20171336_노도아_20190084_김가현_기말과제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		
		JMenuBar mb = new JMenuBar();
		String[] menuTitle = {"과제1", "과제2", "과제3", "과제4", "과제5", "기타"};
		JMenu [] menu = new JMenu [menuTitle.length];
		for(int i=0; i<menuTitle.length; i++) {
			menu[i] = new JMenu(menuTitle[i]);
			mb.add(menu[i]);
		}
		
		JMenuItem [][] menuItem = new JMenuItem [menuTitle.length][2];
		String[] menuItemTitle = {"과제설명", "과제실행", "화면 지우기", "종료"};
		for (int i=0;i<menuTitle.length;i++) {
			for (int j=0;j<2;j++) {
				if (i == menuTitle.length-1)
					menuItem[i][j] = new JMenuItem(menuItemTitle[j+2]);
				else
					menuItem[i][j] = new JMenuItem(menuItemTitle[j]);
				menu[i].add(menuItem[i][j]);
				
				menuItem[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removeScreen();
						
						Object obj = e.getSource();
						for (int i=0;i<menuTitle.length-1;i++)
							if (obj == menuItem[i][0]) {
								setSize(700, 400);
								cp.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
								cp.add(new JLabel(taskExp[i]));
							}
						if (obj == menuItem[0][1])
							tasks.task1();
						else if (obj == menuItem[1][1])
							tasks.task2();
						else if (obj == menuItem[2][1])
							tasks.task3();
						else if (obj == menuItem[3][1])
							tasks.task4();
						else if (obj == menuItem[4][1])
							tasks.task5();
						else if (obj == menuItem[5][1])
							System.exit(0);
						
						setVisible(true);
					}
				});
			}
		}
		
		setJMenuBar(mb);
		setVisible(true);
	}
	
	private String[] setExplanations()
	{
		String[] taskExpPath = {
				"Explanation/task1Exp.txt",
				"Explanation/task2Exp.txt",
				"Explanation/task3Exp.txt",
				"Explanation/task4Exp.txt",
				"Explanation/task5Exp.txt"
		};
		InputStream in = null;
		String[] taskExp = new String [taskExpPath.length];
		
		for (int i=0;i<5;i++) {
			try {
				in = getClass().getClassLoader().getResourceAsStream(taskExpPath[i]);
				byte[] data = new byte[in.available()];
				in.read(data);
				taskExp[i] = new String(data, "utf-8").replaceFirst("﻿", "");
			} catch (FileNotFoundException e) {
				System.out.println("[ERROR] 저장 경로를 찾을 수 없습니다. >> " + taskExpPath[i]);
			} catch (UnsupportedEncodingException e) {
				System.out.println("[ERROR] encoding 지정 에러");
			} catch (IOException e) {
				System.out.println("[ERROR] 파일 읽기에 실패했습니다. >> " + taskExpPath[i]);
			} catch (Exception e) {
				System.out.println("[ERROR] 알 수 없는 에러가 발생했습니다. >> " + taskExpPath[i]);
			} finally {
				if(in != null) {
					try {
						in.close();
						in = null;
					} catch (IOException e) {
						System.out.println("[ERROR] 파일 닫기 실패");
					}
				}
			}
		}
		
		return taskExp;
	}
	
	void removeScreen() {
		cp.setBackground(null);
		cp.removeAll();
		cp.repaint();
	}
}