/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class loginCtrl {
    private IO io;
    private loginView view;
    private Vector<Account> vacc;

    public loginCtrl(loginView view) {
        io = new IO("myData.io");
        this.view = view;
        if(!io.check_exist()){
            view.showMsg("Không Thấy File Data !!!!!!!");
            view.setVisible(false);
            return;
        }
        
        vacc = io.doc_tu_file();
        view.addLoginActionListenner(new LoginActionListenner());
        view.addLoginKeyListenner(new keyLoginListenner());
    }
    
    class LoginActionListenner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { 
            login();
        }
    }
    
    
    class  keyLoginListenner implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                login();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
        
    }
    
    public void login(){
        if(!io.check_exist()){
            view.showMsg("File Dữ Liệu Không Tồn Tại");
            return;
        }

        view.reset_err();
        HashMap<String, String> hm = view.getLoginInfo();
        String username = hm.get("username");
        String password = hm.get("password");
        for (Account account : vacc) {
            if(account.getUsername().equalsIgnoreCase(username)){                    
                if(account.getPassword().equalsIgnoreCase(password)){
                    view.reset();
                    view.setVisible(false);

                    accountView acc_view = new accountView();
                    accountCtrl ctrl = new accountCtrl(vacc,acc_view,view);
                    acc_view.setVisible(true);
                    return;
                }else{
                    view.setError("lab_err_pass", "Mật Khẩu không hợp lệ");
                    return;
                }
            }
        }
        view.setError("lab_err_username", "Tên Đăng Nhập Không Tồn Tại");
    }
}
