import javax.swing.*;

public class Task3 extends Task {
    public Task3(JFrame jFrame) {
        super(jFrame);
    }

    @Override
    int getFrameWidth() {
        return 505;
    }

    @Override
    int getFrameHeight() {
        return 600;
    }

    @Override
    void addComponents() {
        cp.add(new JLabel("가장 좋아하는 과일은?")).setBounds(170, 25, 130, 20);

        ImageIcon[] images = {
                new ImageIcon(getClass().getResource("images/apple.jpg")),
                new ImageIcon(getClass().getResource("images/pear.jpg")),
                new ImageIcon(getClass().getResource("images/cherry.jpg"))
        };
        JLabel imgLabel = new JLabel();
        imgLabel.setBounds(65, 120, 350, 350);
        cp.add(imgLabel);

        ButtonGroup rbGroup = new ButtonGroup();
        String[] rbList = {"사과", "배", "체리"};
        JRadioButton[] rb = new JRadioButton[rbList.length];
        for (int i = 0; i < rbList.length; i++) {
            rb[i] = new JRadioButton(rbList[i]);
            rb[i].setBounds(i * 100 + 115, 65, 100, 20);
            cp.add(rb[i]);
            rbGroup.add(rb[i]);
            rb[i].addActionListener(e -> {
                for (int i1 = 0; i1 < rbList.length; i1++) {
                    if (e.getActionCommand() == rbList[i1]) {
                        imgLabel.setIcon(images[i1]);
                    }
                }
            });
        }
    }
}
