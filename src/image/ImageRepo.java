/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import java.util.ArrayList;
//import javax.swing.JOptionPane;

/**
 *
 * @author dvitoriano
 */
public class ImageRepo {

    private ArrayList<Image> listUser;
    private static ImageRepo instanceUserRep;
    ImagePersist persistUser = new ImagePersist();

    /**
     * get or create ImageRepo
     *
     * @return
     */
    public static ImageRepo getInstance() {
        if (instanceUserRep == null) {
            instanceUserRep = new ImageRepo();
        }
        return instanceUserRep;
    }

    /**
     * return all users
     *
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Image> listAllUsers() throws Exception {
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
    public  Image listUser(String cpf) throws Exception {
        ArrayList<Image> listUsers;
        listUsers = listAllUsers();
        for (Image user : listUsers) {
            if (user.getIdImage().equals(cpf)){
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
    public void inserir(Image user) throws Exception {
        if (user == null) {
            throw new Exception("User don't exist");
        }
        if (user.getIdImage() == null) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getIdImage().trim().equals("")) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getIdRecord() == null) {
            throw new Exception("Informe o nome");
        }
        if (user.getIdRecord().trim().equals("")) {
            throw new Exception("Informe o nome");
        }
        if (user.getUrl() == null) {
            throw new Exception("Profile");
        }
        if (user.getUrl().trim().equals("")) {
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
    public void update(Image user) throws Exception {
        if (user == null) {
            throw new Exception("User não existe");
        }
        if (user.getIdImage() == null) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getIdImage().trim().equals("")) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getIdRecord() == null) {
            throw new Exception("Informe o nome");
        }
        if (user.getIdRecord().trim().equals("")) {
            throw new Exception("Informe o nome");
        }
        if (user.getUrl() == null) {
            throw new Exception("Profile");
        }
        if (user.getUrl().trim().equals("")) {
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
    public int ifExists(Image user) {
        int ret = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (user.getIdImage().trim().equals(this.listUser.get(i).getIdImage().trim())) {
                ret = i;
                break;
            }
        }
        return ret;
    }
    
    public int searchCpf(Image user) {
        int retorno = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (user.getIdImage().trim().equals(this.listUser.get(i).getIdImage().trim())) {
                retorno = i;
                break;
            }
        }
//        JOptionPane.showMessageDialog(null, ret);
        return retorno;
    }


}
