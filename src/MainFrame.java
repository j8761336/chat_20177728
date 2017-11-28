import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//server 端的伺服程式
public class MainFrame extends JFrame{

    private TextArea ta=new TextArea();
    private TextField tf=new TextField();
    private JButton btn[]=new JButton[9];
    private JLabel lab1=new JLabel("Server ip : ");
    private JLabel lab2=new JLabel("10.51.3.116");
    private JLabel lab3=new JLabel("Port : ");
    private TextField tfPort =new TextField();
    private JButton btStart = new JButton("Start");
    private JButton btStop = new JButton("Stop");
    private JButton btExit = new JButton("Exit");
    private JButton btSend = new JButton("Send");
    private JPanel jp1 =new JPanel();
    private JPanel jp2 =new JPanel(new GridLayout(3,3,1,1));
    private JPanel jp3 =new JPanel(new GridLayout(7,1,5,5));
    private JPanel jp4 =new JPanel(new BorderLayout());
    private int width=500,height=500;
    private int Screenw=Toolkit.getDefaultToolkit().getScreenSize().width;
    private int Screenh=Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void main(String args[]){
        MainFrame mfm =new MainFrame();
        mfm.setVisible(true);
        try{
            Scanner scn =new Scanner(System.in);
            ServerSocket svs =new ServerSocket(2525);
            System.out.println("等待客戶端的請求中...");
            Socket s =svs.accept();
            System.out.println("客戶端已連上本機...");

            OutputStream out =s.getOutputStream();
            String str =scn.next();
            System.out.println("資料正在傳送中...");
            out.write(str.getBytes());
            out.close();
            s.close();
            System.out.println("資料傳送完畢...");

        }catch (Exception e){
            System.out.println("發生了"+e+"例外");
        }
    }
    public MainFrame(){
        initComp();
    }
    private void initComp(){
        this.setVisible(true);
        this.setBounds(Screenw/2-width/2,Screenh/2-height/2,width,height);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        add(jp1,BorderLayout.WEST);
        add(jp2,BorderLayout.CENTER);
        add(jp3,BorderLayout.EAST);
        add(jp4,BorderLayout.SOUTH);
        jp1.add(ta,BorderLayout.WEST);
        ta.setPreferredSize(new Dimension(100,400));
        for(int i=0;i<9;i++){
            btn[i] =new JButton();
            jp2.add(btn[i]);
        }
        jp3.add(lab1);
        jp3.add(lab2);
        jp3.add(lab3);
        jp3.add(tfPort);
        jp3.add(btStart);
        jp3.add(btStop);
        jp3.add(btExit);
        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btExit.setBackground(Color.red);
        jp4.add(tf,BorderLayout.CENTER);
        jp4.add(btSend,BorderLayout.EAST);
    }
}
