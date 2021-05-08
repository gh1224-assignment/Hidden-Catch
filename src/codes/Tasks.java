package codes;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Tasks {
	JFrame jFrame;
	Container cp;
	HiddenCatch hiddenCatch;
	
	public Tasks(JFrame jFrame, Container cp)
	{
		this.jFrame = jFrame;
		this.cp = cp;
		hiddenCatch = new HiddenCatch(jFrame, cp);
	}
	
	public void task1()
	{
		jFrame.setSize(375, 500);
		cp.setLayout(null);
		
		JTextField tfCal = new JTextField(20);
		tfCal.setFont(new Font("",0,25));
		tfCal.setBounds(30, 50, 295, 60);
		cp.add(tfCal);
		
		String[] bList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "="};
		JButton [] numB = new JButton [bList.length];
		for (int i=0;i<numB.length;i++) {
			numB[i] = new JButton(bList[i]);
			numB[i].setFont(new Font("",0,20));
			numB[i].setBounds((int)(i%3)*95+50, (int)(i/3)*65+150, 60, 40);
			cp.add(numB[i]);
		}
	}
	
	public void task2() {
		jFrame.setSize(380, 320);
		cp.setLayout(null);
		
		cp.add(new JLabel("어떤 피자를 주문하시겠습니까?")).setBounds(90, 25, 180, 20);
		
		String[] cb1List = {"피자 선택", "포테이토", "불고기", "슈퍼슈프림", "치즈"};
		cp.add(new JComboBox(cb1List)).setBounds(70, 70, 100, 25);
		String[] cb2List = {"사이즈 선택", "L", "M"};
		cp.add(new JComboBox(cb2List)).setBounds(190, 70, 100, 25);
		
		String[] cbList = {"치즈 추가", "피클 추가", "콜라 추가"};
		JCheckBox [] cb = new JCheckBox [cbList.length];
		for (int i=0;i<cbList.length;i++) {
			cb[i] = new JCheckBox(cbList[i]);
			cb[i].setBounds(i*110+32, 120, 80, 25);
			cp.add(cb[i]);
		}
		
		JButton orderB = new JButton("주문");
		orderB.setBounds(140, 175, 80, 30);
		cp.add(orderB);
		orderB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cp.setBackground(Color.PINK);
			}
		});
	}
	
	public void task3() {
		jFrame.setSize(505, 600);
		cp.setLayout(null);
		
		cp.add(new JLabel("가장 좋아하는 과일은?")).setBounds(170, 25, 130, 20);
		
		ImageIcon [] images = {
				new ImageIcon(getClass().getResource("images/apple.jpg")),
				new ImageIcon(getClass().getResource("images/pear.jpg")),
				new ImageIcon(getClass().getResource("images/cherry.jpg"))
		};
		JLabel imgLabel = new JLabel();
		imgLabel.setBounds(65, 120, 350, 350);
		cp.add(imgLabel);
		
		ButtonGroup rbGroup = new ButtonGroup();
		String[] rbList = {"사과", "배", "체리"};
		JRadioButton [] rb = new JRadioButton[rbList.length];
		for (int i=0;i<rbList.length;i++) {
			rb[i] = new JRadioButton(rbList[i]);
			rb[i].setBounds(i*100+115, 65, 100, 20);
			cp.add(rb[i]);
			rbGroup.add(rb[i]);
			rb[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i=0;i<rbList.length;i++) {
						if (e.getActionCommand() == rbList[i]) {
							imgLabel.setIcon(images[i]);
						}
					}
				}
			});
		}
	}
	
	public void task4() {
		jFrame.setSize(385, 285);
		cp.setLayout(null);
		
		cp.add(new JLabel("섭씨온도")).setBounds(35, 65, 80, 20);
		cp.add(new JLabel("화씨온도")).setBounds(35, 115, 80, 20);
		
		JTextField tfC = new JTextField();
		tfC.setBounds(125, 65, 100, 20);
		cp.add(tfC);
		JTextField tfF = new JTextField();
		tfF.setBounds(125, 115, 100, 20);
		cp.add(tfF);
		
		JButton convertB = new JButton("바꾸기");
		convertB.setBounds(255, 75, 75, 50);
		cp.add(convertB);
		convertB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfC.getText().isEmpty())
					tfF.setText("");
				else {
					String amount = tfC.getText();
					double amountVal = Double.valueOf(amount);
					tfF.setText(String.format("%.2f", amountVal*9/5+32));
				}
			}
		});
	}
	
	public void task5() {
		hiddenCatch.play();
	}
}
