import javax.swing.*;
import java.awt.*;

public class Task2 extends Task {
    public Task2(JFrame jFrame, Container cp) {
        super(jFrame, cp);
    }

    @Override
    int getFrameWidth() {
        return 380;
    }

    @Override
    int getFrameHeight() {
        return 320;
    }

    @Override
    void addComponents() {
        cp.add(new JLabel("어떤 피자를 주문하시겠습니까?")).setBounds(90, 25, 180, 20);

        String[] cb1List = {"피자 선택", "포테이토", "불고기", "슈퍼슈프림", "치즈"};
        cp.add(new JComboBox(cb1List)).setBounds(70, 70, 100, 25);
        String[] cb2List = {"사이즈 선택", "L", "M"};
        cp.add(new JComboBox(cb2List)).setBounds(190, 70, 100, 25);

        String[] cbList = {"치즈 추가", "피클 추가", "콜라 추가"};
        JCheckBox[] cb = new JCheckBox[cbList.length];
        for (int i = 0; i < cbList.length; i++) {
            cb[i] = new JCheckBox(cbList[i]);
            cb[i].setBounds(i * 110 + 32, 120, 80, 25);
            cp.add(cb[i]);
        }

        JButton orderB = new JButton("주문");
        orderB.setBounds(140, 175, 80, 30);
        cp.add(orderB);
        orderB.addActionListener(e -> cp.setBackground(Color.PINK));
    }
}
