package codes;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sql.rowset.spi.TransactionalWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HiddenCatch {
	JFrame jFrame;
	Container cp;
	MyMouseAdapter mAdapter = new MyMouseAdapter();
	
	JLabel intro = new JLabel(new ImageIcon(getClass().getResource("HiddenCatch/intro.gif")));
	JButton start = new JButton(new ImageIcon(getClass().getResource("HiddenCatch/start.png")));
	ImageIcon [] clrPath = {
			new ImageIcon(getClass().getResource("HiddenCatch/easy.png")),
			new ImageIcon(getClass().getResource("HiddenCatch/normal.png")),
			new ImageIcon(getClass().getResource("HiddenCatch/hard.png"))
	};
	JLabel [] clr = new JLabel[clrPath.length];
	JLabel [] circle = new JLabel [5];
	JLabel [] circle2 = new JLabel [5];
	JLabel [] heart = new JLabel [5];
	JLabel timebar = new JLabel(new ImageIcon(getClass().getResource("HiddenCatch/timebar.gif")));
	ImageIcon [] imgPath = {
			new ImageIcon(getClass().getResource("HiddenCatch/HiddenCatch01.jpg")),
			new ImageIcon(getClass().getResource("HiddenCatch/HiddenCatch02.jpg")),
			new ImageIcon(getClass().getResource("HiddenCatch/HiddenCatch03.jpg"))
	};
	JLabel [] img = new JLabel[imgPath.length];
	JButton next = new JButton(new ImageIcon(getClass().getResource("HiddenCatch/next.png")));
	JLabel stop = new JLabel(new ImageIcon(getClass().getResource("HiddenCatch/stop.png")));
	JButton retry = new JButton(new ImageIcon(getClass().getResource("HiddenCatch/retry.png")));
	JLabel outro = new JLabel(new ImageIcon(getClass().getResource("HiddenCatch/outro.gif")));
	int xy[][][] = {
			{{653, 688, 76, 113}, {436, 468, 364, 383}, {749, 797, 258, 307}, {680, 713, 336, 369}, {491, 578, 452, 513}},
			{{632, 655, 83, 109}, {546, 562, 140, 156}, {532, 552, 378, 398}, {561, 579, 274, 294}, {600, 616, 183, 204}},
			{{409, 445, 148, 175}, {423, 443, 261, 289}, {480, 544, 457, 493}, {721, 745, 271, 297}, {654, 706, 36, 73}}
	};
	int imgSX = 800;
	int imgSY = 530;
	int imgX = 280;
	int imgY = 70;
	int correctxy[] = {0, 0, 0, 0, 0};
	int stage = 0;
	int correctNum = 0;
	int wrongNum = 0;
	
	public HiddenCatch(JFrame jFrame, Container cp)
	{
		this.jFrame = jFrame;
		this.cp = cp;
		
		intro.setBounds(280, 50, 800, 405);
		
		start.setBounds(505, 500, 350, 83);
		transparentButton(start);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intro.setVisible(false);
				start.setVisible(false);
				img[stage].setVisible(true);
				timebar.setVisible(true);
				for (int i=0;i<5;i++)
					heart[i].setVisible(true);
				cp.addMouseListener(mAdapter);
			}
		});
		
		for (int i=0;i<3;i++) {
			clr[i] = new JLabel();
			clr[i].setIcon(clrPath[i]);
			clr[i].setBounds(520, 246, 320, 158);
		}
		
		for (int i=0;i<5;i++) {
			circle[i] = new JLabel();
			circle[i].setIcon(new ImageIcon(getClass().getResource("HiddenCatch/circle.png")));
			circle[i].setSize(50, 50);
			
			circle2[i] = new JLabel();
			circle2[i].setIcon(new ImageIcon(getClass().getResource("HiddenCatch/circle.png")));
			circle2[i].setSize(50, 50);
			
			heart[i] = new JLabel();
			heart[i].setIcon(new ImageIcon(getClass().getResource("HiddenCatch/heart.png")));
			heart[i].setBounds(i*30+920, 40, 30, 28);
		}
		
		timebar.setBounds(280, 20, 300, 47);
		
		for (int i=0;i<3;i++) {
			img[i] = new JLabel();
			img[i].setIcon(imgPath[i]);
			img[i].setBounds(imgX, imgY, imgSX, imgSY);
		}
		
		next.setBounds(1110, 233, 110, 184);
		transparentButton(next);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearNReset();
				if (stage+1 == imgPath.length) {
					for (int i=0;i<5;i++)
						heart[i].setVisible(false);
					outro.setVisible(true);
					return;
				}
				stage++;
				img[stage].setVisible(true);
				timebar.setVisible(true);
				cp.addMouseListener(mAdapter);
			}
		});
		
		stop.setBounds(280, 35, 800, 397);
		
		retry.setBounds(620, 430, 120, 137);
		transparentButton(retry);
		retry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearNReset();
				stage = 0;
				wrongNum = 0;
				intro.setVisible(true);
				start.setVisible(true);
			}
		});
		
		outro.setBounds(355, 5, 650, 671);
	}
	
	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			for (int i=0;i<5;i++) {
				if (xy[stage][i][0]+imgX <= x && x <= xy[stage][i][1]+imgX
						&& xy[stage][i][2]+imgY <= y && y<= xy[stage][i][3]+imgY) {
					if (correctxy[i] == 1) {
						wrong();
						return;
					}
					
					circle[correctNum].setLocation(x-25, y-25);
					circle[correctNum].setVisible(true);
					circle2[correctNum].setLocation(x-imgSX/2-25, y-25);
					circle2[correctNum].setVisible(true);
					correctxy[i]= 1;
					correctNum++;
					if (correctNum == 5) {
						timebar.setVisible(false);
						clr[stage].setVisible(true);
						next.setVisible(true);
						cp.removeMouseListener(mAdapter);
					}
					return;
				}
			}
			if (imgX+imgSX/2 < x && x < imgX+imgSX && imgY < y && y < imgY+imgSY)
				wrong();
		}
	}
	
	private void clearNReset() {
		correctNum = 0;
		intro.setVisible(false);
		start.setVisible(false);
		for (int i=0;i<3;i++)
			clr[i].setVisible(false);
		for (int i=0;i<5;i++) {
			correctxy[i] = 0;
			circle[i].setVisible(false);
			circle2[i].setVisible(false);
		}
		timebar.setVisible(false);
		img[stage].setVisible(false);
		next.setVisible(false);
		stop.setVisible(false);
		retry.setVisible(false);
		outro.setVisible(false);
	}
	
	private void wrong() {
		wrongNum++;
		if (wrongNum == 5) {
			clearNReset();
			stop.setVisible(true);
			retry.setVisible(true);
			cp.removeMouseListener(mAdapter);
		}
		heart[5-wrongNum].setVisible(false);
	}
	
	public void play()
	{
		jFrame.setSize(1360, 730);
		cp.setLayout(null);
		cp.removeMouseListener(mAdapter);
		
		cp.add(intro);
		cp.add(start);
		for (int i=0;i<3;i++)
			cp.add(clr[i]);
		for (int i=0;i<5;i++) {
			cp.add(circle[i]);
			cp.add(circle2[i]);
			cp.add(heart[i]);
			heart[i].setVisible(false);
		}
		cp.add(timebar);
		for (int i=0;i<3;i++) {
			cp.add(img[i]);
			img[i].setVisible(false);
		}
		cp.add(next);
		cp.add(stop);
		cp.add(retry);
		cp.add(outro);
		
		stage = 0;
		wrongNum = 0;
		clearNReset();
		intro.setVisible(true);
		start.setVisible(true);
	}
	
	private static void transparentButton(JButton btn)
	{
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
	}
}

