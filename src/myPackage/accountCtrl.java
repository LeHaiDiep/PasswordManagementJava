/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class accountCtrl {
    private Vector<Account> vacc;
    private IO io;
    private accountView acc_view;
    private Form_Add_Edit_Account form_view;
    private ButtonEditor buttonEditor;
    private loginView login_view;

    public accountCtrl(Vector<Account> vacc, accountView acc_view, loginView login_view) {
        this.vacc = vacc;
        this.acc_view = acc_view;
        this.form_view = new Form_Add_Edit_Account();
        this.login_view = login_view;
        this.io = new IO("myData.io");
        
        acc_view.add_btn_accountActionListenner(new btn_add_accountActionListenner());
        form_view.btnAccountListenner(new btnAccountActionListenner());
        acc_view.addLogoutActionListenner(new logoutActionListenner());
        
        // gan du lieu cho bang danh sach account
        this.acc_view.bind_data_table(vacc);
        acc_view.setEvent("Xóa",new btnDeleteActionListenner());
        acc_view.setEvent("Chỉnh Sửa", new btnEditActionListenner());
        acc_view.addKeyListenner(new addSearchKeyListenner());
    }
    
    class btnDeleteActionListenner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn  = (JButton)e.getSource();
            int id = Integer.valueOf(btn.getName());
            deleteAccount(id);
        }
    }
    
    class addSearchKeyListenner implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                String key = acc_view.getKeySearch().toLowerCase();
                Vector<Account> vsearch = new Vector<>();
                if(!key.equalsIgnoreCase("")){
                    for (Account account : vacc) {
                        if(account.getWebsite().toLowerCase().contains(key)){
                            vsearch.addElement(account);
                        }
                    }
                    acc_view.bind_data_table(vsearch);
                }else{
                    acc_view.bind_data_table(vacc);
                }
                acc_view.setEvent("Xóa",new btnDeleteActionListenner());
                acc_view.setEvent("Chỉnh Sửa", new btnEditActionListenner());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
    }
    
    class btnEditActionListenner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn  = (JButton)e.getSource();
            int id = Integer.valueOf(btn.getName());
            
            Account a = new Account();
            for (Account account : vacc) {
                if(account.getId() == id){
                    a = account;
                    break;
                }
            }
            form_view.setVisible(true);
            form_view.setData(a);
            form_view.reset_info("Giao Diện Chỉnh Sửa Tài Khoản", "Chỉnh Sửa");
        }
    }
    
    class btn_add_accountActionListenner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            form_view.setVisible(true);
        }
    }
    
    class btnAccountActionListenner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Account account = form_view.getAccountInfo();
            form_view.setVisible(false);
            if(account.getId() != -1){
                editAccount(account);
                acc_view.showMsg("Chỉnh Sửa Tài Khoản Thành Công");
            }else{
                addAccount(account);
                acc_view.showMsg("Thêm Mới Tài Khoản Thành Công");
            }
            updateData();
        }        
    }
    
    class logoutActionListenner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            acc_view.setVisible(false);
            login_view.setVisible(true);
        }
    }
    
    public void editAccount(Account account){
        for(Account acc : vacc){
            if(acc.getId() == account.getId()){
                vacc.set(vacc.indexOf(acc), account);
            }
        }
        io.ghi_ra_file(vacc);
        form_view.reset_info("Giao Diện Thêm Mới Tài Khoản", "Thêm Mới");
    }
    
    public void addAccount(Account account){
       
        if(vacc.size() == 0){
            account.setId(vacc.size());
        }else{
            int id = vacc.lastElement().getId();
            account.setId(id);
        }
        
        vacc.add(account);
    }
    
    
    public void deleteAccount(int id){
        int index = 0;
        for(Account acc : vacc){
            if(acc.getId() == id){
                index  = vacc.indexOf(acc);
                break;
            }
        }
        
        if(index != 0){
            if(acc_view.showConfirm("Bạn có muốn xóa tài khoản này ?") == JOptionPane.YES_OPTION){
                vacc.removeElementAt(index);
                acc_view.showMsg("Xóa tài khoản thành công!!!!!!!!");
                updateData();
            }
        }
    }
    
    public void updateData(){
        form_view.reset_data();
        io.ghi_ra_file(vacc);
        acc_view.bind_data_table(vacc);
        acc_view.setEvent("Xóa",new btnDeleteActionListenner());
        acc_view.setEvent("Chỉnh Sửa", new btnEditActionListenner());
    }
}
