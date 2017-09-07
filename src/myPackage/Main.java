/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

/**
 *
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        loginView view = new loginView();
        loginCtrl ctrl  = new loginCtrl(view);
        view.setVisible(true);
    }
}
