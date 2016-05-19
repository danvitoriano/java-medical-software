/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;

import java.util.ArrayList;
//import javax.swing.JOptionPane;

/**
 *
 * @author dvitoriano
 */
public class PatientRepo {

    private ArrayList<Patient> listUser;
    private static PatientRepo instanceUserRep;
    PatientPersist persistUser = new PatientPersist();

    /**
     * get or create PatientRepo
     *
     * @return
     */
    public static PatientRepo getInstance() {
        if (instanceUserRep == null) {
            instanceUserRep = new PatientRepo();
        }
        return instanceUserRep;
    }

    /**
     * return all users
     *
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Patient> listAllUsers() throws Exception {
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
    public  Patient listUser(String cpf) throws Exception {
        ArrayList<Patient> listUsers;
        listUsers = listAllUsers();
        for (Patient user : listUsers) {
            if (user.getCpf().equals(cpf)){
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
    public void inserir(Patient user) throws Exception {
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
        if (user.getActive() == null) {
            throw new Exception("Ativo?");
        }
        if (user.getActive().trim().equals("")) {
            throw new Exception("Entre com 0 ou 1");
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
    public void update(Patient user) throws Exception {
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
        if (user.getActive() == null) {
            throw new Exception("Ativo?");
        }
        if (user.getActive().trim().equals("")) {
            throw new Exception("Entre com 0 ou 1");
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
    public int ifExists(Patient user) {
        int ret = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (user.getCpf().trim().equals(this.listUser.get(i).getCpf().trim())) {
                ret = i;
                break;
            }
        }
        return ret;
    }
    
    public int searchCpf(Patient user) {
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
