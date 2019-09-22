//（4）使用工具Eclipse，创建一个Java GUI程序，在主窗口中显示“Hello World”，用户可以用鼠标拖动“Hello World”在主窗口中移动。

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIHelloWorld extends JFrame {
    JPanel jPanel;
    JLabel jLabel;
    String text;

    public GUIHelloWorld(){
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jPanel = new JPanel();
        add(jPanel);

        text = "Hello, World";
        jLabel = new JLabel(text);
        jPanel.setLayout(null);
        Font font = new Font("courier", Font.BOLD, 30);
        FontMetrics fm = jLabel.getFontMetrics(font);
        jLabel.setSize(fm.stringWidth(text), fm.getHeight());
        jLabel.setFont(font);
        jPanel.add(jLabel);
        MyListener myListener = new MyListener();
        jLabel.addMouseListener(myListener);
        jLabel.addMouseMotionListener(myListener);
        setVisible(true);
    }

    class MyListener extends MouseAdapter{
        int startX;
        int startY;
        int endX;
        int endY;
        int labelX;
        int labelY;

        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getXOnScreen();
            startY = e.getYOnScreen();
            labelX = e.getComponent().getX();
            labelY = e.getComponent().getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            endX = e.getXOnScreen();
            endY = e.getYOnScreen();
            e.getComponent().setLocation(labelX + endX - startX, labelY + endY - startY);
        }

		@Override
        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public static void main(String[] args) {
        GUIHelloWorld g = new GUIHelloWorld();
    }

}
