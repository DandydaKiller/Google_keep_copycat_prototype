import java.awt.FlowLayout;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.io.*;
import java.net.*;

public class GUI extends JFrame implements ActionListener{

private JFrame j;

//button atau panel umum yang mungkin di butuhkan pada semua menu
JButton back = new JButton("BACK");
Icon iconAdd = new ImageIcon("add.png");
JButton addNote = new JButton(new ImageIcon("d:add.png"));
Icon iconSave = new ImageIcon("save.jpg");
JButton savedNote = new JButton(new ImageIcon("d:save.png"));
JPanel mainPanel;
JPanel addNotePanels = new JPanel();
JPanel savedNotePanels = new JPanel();; 

//public static SocketClient sc;
//----------------<Batas menu umum>-----------------
public void mainMenu() throws IOException{
	j = new JFrame("Google Keep KW V1.10");
	mainPanel = new JPanel();
	JLabel namaMenu = new JLabel("Main Menu");
	addNote.addActionListener(this);
	savedNote.addActionListener(this);
	
	mainPanel.add(namaMenu);
	mainPanel.add(addNote);
	mainPanel.add(savedNote);
	
	addNote.setBounds(50,40,300,25);
	j.setLayout(null);
	j.setContentPane(mainPanel);
	j.setVisible(true);
	j.setSize(800,500);
    //j.pack();
	mainPanel.setLayout(null);
	j.setLayout(new FlowLayout(FlowLayout.CENTER));
	j.setLocationRelativeTo(null);
	j.setResizable(true);
}

public void addNote(JPanel an) {
	JLabel namaIP = new JLabel("IP Server : ");
	JLabel namaIPClient = new JLabel("IP Client : ");

	JLabel namaJudul = new JLabel("Judul ");
	JLabel namaIsi = new JLabel("isi Note ");
	final JTextField judul = new JTextField(12); 
	final JTextArea isi = new JTextArea(); 
	judul.setFont(new java.awt.Font("Times New Roman",1,12));
	JButton add = new JButton("Add");
	
	SocketClient sc = new SocketClient();
	String ip2 = "";
	String ip2client = "";
	try {
		ip2 = sc.getIP().getInetAddress().toString();
		ip2client = sc.getIP().getLocalAddress().toString();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//System.out.println(ip2);
	JLabel ip = new JLabel(ip2);
	JLabel ipclient = new JLabel(ip2client);
	an.add(namaIP);
	an.add(ip);
	an.add(judul);
	an.add(isi);
	back.addActionListener(this);
	add.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			SocketClient sc = new SocketClient();
			sc.AddNote(judul.getText(), isi.getText());
			judul.setText("");
			isi.setText("");
			JOptionPane jp = new JOptionPane();
			jp.showMessageDialog(null, "Note Berhasil Di Tambahkan"," ", JOptionPane.INFORMATION_MESSAGE);
		}
	});;
	//add.set
	an.add(back);
	an.add(namaJudul);
	an.add(namaIsi);
	an.add(namaIPClient);
	an.add(ipclient);
	an.add(add);
	
	namaIPClient.setBounds(50,20,300,25);
	ipclient.setBounds(110,20,300,25);
	namaIP.setBounds(50,40,300,25);
	ip.setBounds(110,40,300,25);
	namaJudul.setBounds(50,70,300,25);
	namaIsi.setBounds(50,110,300,25);
	judul.setBounds(160,70,400,25);
	isi.setBounds(160,110,400,200);
	back.setBounds(400,380,150,25);
	add.setBounds(100,380,150,25);
	an.setLayout(null);
	
}

public void savedNote(JPanel sn){
	SocketClient sc = new SocketClient();
	JTable t;
	JButton edit = new JButton("Edit");
	JButton delete = new JButton("Delete");
	JLabel labeltable = new JLabel("Data Catatan");
	//sc.getNote();
	//System.out.println(sc.getNote());
	System.out.println("----------------------------------Masuk print semua data----------------------------");
	System.out.println(sc.SavedNote());

	String[]baris = sc.SavedNote().split("<baris>");
	String[][] tabel = new String[baris.length][4];
	for (int i = 0; i < baris.length; i++) {
		String[] kolom = baris[i].split("<kolom>");
		tabel[i][0] = kolom[0];
		tabel[i][1] = kolom[1];
		tabel[i][2] = kolom[2];
		tabel[i][3] = kolom[3];

		//System.out.println("Test date: "+tabel[i][2]);
	}
	System.out.println("----------------------------------Masuk print masing - masing data----------------------------");
	Object[] judulKolom = { "Id","judul", "isi", "tanggal dibuat" };
	DefaultTableModel note_created = new DefaultTableModel(new Object[][] {}, judulKolom);

	for (int i = 0; i < baris.length; i++) {
		System.out.println("judul : "+tabel[i][0]);
		System.out.println("Isi : "+tabel[i][1]);
		System.out.println("Tanggal : "+tabel[i][2]);
		System.out.println("ID note : "+tabel[i][3]);


		//Object enumSet;
		Object[] data1={""+tabel[i][3],tabel[i][0],tabel[i][1],tabel[i][2]};
		note_created.addRow(data1);
	}
	//note_created = new DefaultTableModel();
	t = new JTable();
	JScrollPane scrollPane = new JScrollPane(t);
	t.setModel(note_created);
	t.add(labeltable);
	t.revalidate();
	scrollPane.revalidate();
	scrollPane.setViewportView(t);
	
	back.addActionListener(this);
	//sn.add(note_created);
	//sn.add(t);
	sn.add(labeltable);
	sn.add(scrollPane);
	sn.add(back);
	//labeltable.setBounds(110,20,300,25);
	labeltable.setBounds(50,40,300,25);
	scrollPane.setBounds(100,110,500,200);
	back.setBounds(400,380,150,25);
	sn.setLayout(null);
	
}

@Override
public void actionPerformed(ActionEvent ae) {
	// TODO Auto-generated method stub
	JButton button = (JButton) ae.getSource();
	if (button == back) {
		j.remove(addNotePanels);
		j.remove(savedNotePanels);
		j.setContentPane(mainPanel);
	}else if(button == addNote ){
		j.remove(mainPanel);
		addNote(addNotePanels);
		j.setContentPane(addNotePanels);
	}else if(button == savedNote){
		j.remove(mainPanel);
		savedNote(savedNotePanels);
		j.setContentPane(savedNotePanels);
	}
	j.validate();
	j.repaint();
}

}
