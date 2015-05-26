package src;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

/**
 *
 * @author Ajan
 * 
 * In order to develop with this source code, please get your jxbrowser license from http://www.teamdev.com/jxbrowser 
 * Screen shot of the form you need to answer http://gyazo.com/4a33eac9a05d90cfde729d4d3a5289a8
 * 
 * 
 * Once you get license.jar, right click the project libraries -> Add JAR/Folder -> Search for where you downloaded the license to, and add it. 
 * 
 */
@SuppressWarnings("serial")
public class GoogleMusicPlayer extends javax.swing.JFrame implements NativeKeyListener, WindowListener {

    Browser browser = new Browser();//Component that will display sites
    BrowserView browserView = new BrowserView(browser); //Component that hold the browser component and displays it
    GlobalScreen gs;    //Native Window Hooker

    public GoogleMusicPlayer() {
        initComponents();

        this.add(browserView, BorderLayout.CENTER);     //Centers the browser to the middle of the JFrame
        this.addWindowListener(this);                   
        browser.loadURL("http://google.com/music");     //Browser component will load play music

    }
    //Dispatch Events to simulate key presses without the window being active
    @SuppressWarnings("deprecation")
    private void Play() {

        browserView.dispatchEvent(new KeyEvent(browserView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE)); //Initiates a first stage of a keypress
        browserView.dispatchEvent(new KeyEvent(browserView, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, ' '));
        browserView.dispatchEvent(new KeyEvent(browserView, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE)); //Initiates the final stage of a keypress

    }

    private void Previous() {

        browserView.dispatchEvent(new KeyEvent(browserView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT)); //Initiates a first stage of a keypress
        browserView.dispatchEvent(new KeyEvent(browserView, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT)); //Initiates the final stage of a keypress

    }

    private void Next() {
        browserView.dispatchEvent(new KeyEvent(browserView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT)); //Initiates a first stage of a keypress		 
        browserView.dispatchEvent(new KeyEvent(browserView, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT)); //Initiates the final stage of a keypress
    }
    //
    //Native Key Events
    
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_MEDIA_PLAY) {				//When media key is pressed, play-pause will be actioned
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Play();
                }
            });
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_MEDIA_PREVIOUS) {			// When previous media key is pressed, the previous track will be played
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Previous();
                }
            });
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_MEDIA_NEXT) {				// When next media key is pressed, the next track will be played
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Next();
                }
            });
        }
    }
    
    //
    
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Native Hook Window Events

    @Override
    public void windowOpened(WindowEvent we) {
        //Initialze native hook        
        try {
            gs.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();

            System.exit(1);
        }

        gs.addNativeKeyListener(this);
    }
    
    @Override
    public void windowClosing(WindowEvent we) {
        
    }
    
    @Override
    public void windowClosed(WindowEvent we) {

        try {
            //Clean up the native hook.
            gs.unregisterNativeHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(GoogleMusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.runFinalization();
        System.exit(0);

    }
    
    @Override
    public void windowIconified(WindowEvent we) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        
        
    }

    @Override
    public void windowActivated(WindowEvent we) {

    }

    @Override
    public void windowDeactivated(WindowEvent we) {

    }
    //

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Google Music Player");
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified

        //Not yet intiated. Tray support taken away due to bugs
            
    }//GEN-LAST:event_formWindowIconified

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GoogleMusicPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GoogleMusicPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GoogleMusicPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GoogleMusicPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //New instance of class
                GoogleMusicPlayer player = new GoogleMusicPlayer();
                
                //Icon build
                String imagepath = "/imgsrc/icon2.png";
                InputStream imgStream = GoogleMusicPlayer.class.getResourceAsStream(imagepath);
                BufferedImage myImg = null;
                try {
                    myImg = ImageIO.read(imgStream);
                } catch (IOException ex) {
                    Logger.getLogger(GoogleMusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
                player.setIconImage(myImg);
                //

                player.setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
