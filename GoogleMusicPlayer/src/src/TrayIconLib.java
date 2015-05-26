/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;



/**
 *
 * @author Ajan
 * 
 * This feature cannot be added yet, due to a constraint - where the dispatch event cannot occur without the frame being visible. 
 * 
 */
public class TrayIconLib{


    public TrayIconLib(JFrame jf) {        
        ShowTrayIcon(jf);                             
    }

    public static void ShowTrayIcon(JFrame jf) {
        if (!SystemTray.isSupported()) {

        }

        final PopupMenu popup = new PopupMenu();
        TrayIcon trayIcon = new TrayIcon(CreateIcon("/imgsrc/icon2.png", "Tray Icon"));
        final SystemTray tray = SystemTray.getSystemTray();

        //Tooltip
        trayIcon.setToolTip("Version 1");        
        //Add components/menu items
        MenuItem OpenItem = new MenuItem("Open");
        MenuItem ExitItem = new MenuItem("Exit");
        MenuItem DoubleClick = new MenuItem("Double Click");

        //Populate the pop up menu
        popup.add(OpenItem);
        popup.addSeparator();
        popup.add(ExitItem);
        trayIcon.setPopupMenu(popup);

        OpenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jf.setVisible(true);               
                tray.remove(trayIcon);   
            }
        });

        ExitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });

        trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    jf.setVisible(true);                    
                    tray.remove(trayIcon);                   
                }
            }

        });
        try {
            tray.add(trayIcon);
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    
    protected static Image CreateIcon(String path, String desc) {
        URL ImageURL = TrayIconLib.class.getResource(path);
        return (new ImageIcon(ImageURL, desc)).getImage();
    }
}
