/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.util.ArrayList;
//import javax.swing.JOptionPane;

/**
 *
 * @author dvitoriano
 */
public class UserRepo {

    private ArrayList<User> listUser;
    private static UserRepo instanceUserRep;
    UserPersist persistUser = new UserPersist();

    /**
     * get or create UserRepo
     *
     * @return
     */
    public static UserRepo getInstance() {
        if (instanceUserRep == null) {
            instanceUserRep = new UserRepo();
        }
        return instanceUserRep;
    }

    /**
     * return all users
     *
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<User> listAllUsers() throws Exception {
        this.listUser = persistUser.readData();
        return this.listUser;
    }

    /**
     * return specific user
     *
     * @param cpf
     * @return
     * @throws java.lang.Exception
     */
    public User listUser(String cpf) throws Exception {
        ArrayList<User> listUsers;
        listUsers = listAllUsers();
        for (User user : listUsers) {
            if (user.getCpf().equals(cpf)) {
                return user;
            }
        } //        for (int i = 0; i < this.listUser.size(); i++) {
//            if (user.getCpf().trim().equals(this.listUser.get(i).getCpf().trim())) {
//                ret = i;
//                break;
//            }
//        }

//        int ret = -1;
//        for (int i = 0; i < this.listUser.size(); i++) {
//            if (user.getCpf().trim().equals(this.listUser.get(i).getCpf().trim())) {
//                ret = i;
//                break;
//            }
//        }
//        //this.listUser = persistUser.readData();
//        JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
//        return this.listUser;
        //return ret;
        //return this.listUser;
        //return ret;
        return null;

    }

    /**
     * add an user
     *
     * @param user
     * @throws java.lang.Exception
     */
    public void inserir(User user) throws Exception {
        if (user == null) {
            throw new Exception("User don't exist");
        }
        if (user.getCpf() == null) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getCpf().trim().equals("")) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getName() == null) {
            throw new Exception("Informe o nome");
        }
        if (user.getName().trim().equals("")) {
            throw new Exception("Informe o nome");
        }
        if (user.getPwd() == null) {
            throw new Exception("Informe a senha");
        }
        if (user.getPwd().trim().equals("")) {
            throw new Exception("Informe a senha");
        }
        if (user.getActive() == null) {
            throw new Exception("Ativo?");
        }
        if (user.getActive().trim().equals("")) {
            throw new Exception("Entre com 0 ou 1");
        }
        if (user.getProfile() == null) {
            throw new Exception("Profile");
        }
        if (user.getProfile().trim().equals("")) {
            throw new Exception("c");
        }
        if (this.ifExists(user) >= 0) {
            throw new Exception("User já cadastrado");
        }
        persistUser.setData(user);
    }

    /**
     * update an user
     *
     * @param user
     * @throws java.lang.Exception
     */
    public void update(User user) throws Exception {
        if (user == null) {
            throw new Exception("User não existe");
        }
        if (user.getCpf() == null) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getCpf().trim().equals("")) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getName() == null) {
            throw new Exception("Informe o nome");
        }
        if (user.getName().trim().equals("")) {
            throw new Exception("Informe o nome");
        }
        if (user.getPwd() == null) {
            throw new Exception("Informe a senha");
        }
        if (user.getPwd().trim().equals("")) {
            throw new Exception("Informe a senha");
        }
        if (user.getActive() == null) {
            throw new Exception("Ativo?");
        }
        if (user.getActive().trim().equals("")) {
            throw new Exception("Entre com 0 ou 1");
        }
        if (user.getProfile() == null) {
            throw new Exception("Profile");
        }
        if (user.getProfile().trim().equals("")) {
            throw new Exception("informe o tipo");
        }
        if (this.ifExists(user) == -1) {
            throw new Exception("User não cadastrado");
        }
        persistUser.updateData(user);

        this.listUser.set(this.ifExists(user), user);
    }

    /**
     * check if user exists
     *
     * @param user
     * @return
     */
    public int ifExists(User user) {
        int ret = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (user.getCpf().trim().equals(this.listUser.get(i).getCpf().trim())) {
                ret = i;
                break;
            }
        }
        return ret;
    }

    public int searchCpf(User user) {
        int retorno = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (user.getCpf().trim().equals(this.listUser.get(i).getCpf().trim())) {
                retorno = i;
                break;
            }
        }
//        JOptionPane.showMessageDialog(null, ret);
        return retorno;
    }

}
