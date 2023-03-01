
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

class note extends JFrame implements ActionListener
{
    JFrame f;
    // textArea declaration
    JTextArea t ;

    note()
    {
        //initialising the JFrame
        f = new JFrame("notepad");
        // initialise textArea
        t = new JTextArea();



        // create menubar
        JMenuBar menubar= new JMenuBar();

        // creating File menu
        // JMenuBar is collection of JMenu
        JMenu file = new JMenu("File");

        // In JMenu , JItems are there as colllection
        // creating options for File
        JMenuItem f1 = new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Save");
        JMenuItem f3 = new JMenuItem("Open");
        JMenuItem f4 = new JMenuItem("Print");




        // adding actionListener to each option
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        //adding options to File Menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        // creating edit Menu
        JMenu edit = new JMenu("Edit");

        // creating menuItems in Edit ,enu
        JMenuItem e1 = new JMenuItem("Cut");
        JMenuItem e2 = new JMenuItem("Copy");
        JMenuItem e3 = new JMenuItem("Paste");

        // adding actionListner to each menu
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);

        // adding menuItems to Edit menu
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);


        // creating menu of CLOSE
        //JMenu close = new JMenu("CLose");
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);

        // adding menu in Menubar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(close);

        f.setJMenuBar(menubar);
        f.add(t);

        f.setSize(1080,720);

        // why strike line : Ide tells to use some other method
        // like setVisible() is better method.
        //f.show();
        f.setVisible(true); //its same as f.show();
    }

    // below action performed when button pressed
    public void actionPerformed(ActionEvent e)
    {
        // extracting command into String
        String s = e.getActionCommand();

        switch(s)
        {
            case "New" : t.setText("");
                break;
            case "Save" :
                //creating the object of jfilechooser class with starting path of
                JFileChooser j = new JFileChooser("Documents: ");
//Desktop
                //spawn the dialog box
                //invoke saved dialog box
                int r = j.showSaveDialog(null);
                //r contains status of dialog box 1 if clicked on save
                if(r==0)
                {
                    //declare a file object
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    try {
                        FileWriter fw = new FileWriter(fi);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(t.getText());

                        bw.flush();
                        bw.close();
                    }
                    catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(f,"The user has cancelled operation");
                }
                break;

            case "Open" :
                //creating the object of jfilechooser class with starting path of
                JFileChooser ji = new JFileChooser("Documents: ");
//Desktop:/code
                //spawn the dialog box
                //invoke saved dialog box
                int ri = ji.showOpenDialog(f);
                //r contains status of dialog box 1 if clicked on save
                if(ri==0)
                {
                    //declare a file object and store file location
                    File fi = new File(ji.getSelectedFile().getAbsolutePath());

                    try {
                        FileReader fw = new FileReader(fi);
                        BufferedReader br = new BufferedReader(fw);
                        String s1="",s2="";
                        //first line stored in s1
                        s1 = br.readLine();
                        while((s2=br.readLine())!=null)
                        {
                            s1 = s1+"\n"+s2;
                        }
                        //all the content of the file copied to s1
                        t.setText(s1);

                    }
                    catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(f,"The user has cancelled operation");
                }
                break;

            case "Print" :
                try {
                    t.print();
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
                break;
            case "Cut" : t.cut();
                break;
            case "Copy" : t.copy();
                break;
            case "Paste" : t.paste();
                break;
            case "Close" : f.setVisible(false);
                this.dispose();
                break;
        }
    }

    public static void main(String[] args)
    {
        note n = new note();
    }
}