package edu.chalmers.platypus.model;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class TestPic extends JFrame implements Observer {

	private JPanel contentPane;
	private ImageIcon imc;
	private JLabel lblPic;
	private IFilter fi;
	private BufferedImage bufferedImage;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPic frame = new TestPic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestPic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblPic = new JLabel("");
		ImageIcon im = new ImageIcon("D:\\milano\\DCIM\\Camera\\hej.jpg");
		
		//lblPic.setIcon(im);
		lblPic.setBounds(10, 0, 713, 696);
		bufferedImage = new BufferedImage(im.getIconHeight(), im.getIconWidth(), BufferedImage.TYPE_3BYTE_BGR);
		bufferedImage.getGraphics().drawImage(im.getImage(), 0 , 0, null);
		panel.add(lblPic);
		Observable o;
		
		fi = new FilterClass();
		if(fi instanceof Observable){
			System.out.println("true");
			o = (Observable) fi;
			o.addObserver(this);
		}
		
		imc = new ImageIcon(fi.applyFilter(bufferedImage));
		lblPic.setIcon(imc);
		JFrame jf = new JFrame();
		jf.getContentPane().add(fi.getPanel());
		jf.setVisible(true);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(41, 296, 607, 364);

		
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("dsdasdasdfghgfhh");
		ImageIcon imc = new ImageIcon(fi.applyFilter(bufferedImage));
		lblPic.setIcon(imc);
		panel.revalidate();
		
		
	}

}
