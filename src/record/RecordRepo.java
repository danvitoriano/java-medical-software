/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package record;

import java.util.ArrayList;
//import javax.swing.JOptionPane;

/**
 *
 * @author dvitoriano
 */
public class RecordRepo {

    private ArrayList<Record> listUser;
    private static RecordRepo instanceUserRep;
    RecordPersist persistUser = new RecordPersist();

    /**
     * get or create RecordRepo
     *
     * @return
     */
    public static RecordRepo getInstance() {
        if (instanceUserRep == null) {
            instanceUserRep = new RecordRepo();
        }
        return instanceUserRep;
    }

    /**
     * return all users
     *
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Record> listAllUsers(String id) throws Exception {
        this.listUser = persistUser.readData(id);
        return this.listUser;
    }


    /**
     * add an user
     *
     * @param user
     * @throws java.lang.Exception
     */
    public void inserir(Record user) throws Exception {
        if (user == null) {
            throw new Exception("User don't exist");
        }
        if (user.getCpf() == null) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getCpf().trim().equals("")) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getId() == null) {
            throw new Exception("Informe o nome");
        }
        if (user.getId().trim().equals("")) {
            throw new Exception("Informe o nome");
        }
        if (user.getDt() == null) {
            throw new Exception("Ativo?");
        }
        if (user.getDt().trim().equals("")) {
            throw new Exception("Entre com 0 ou 1");
        }
        if (user.getAnamnese() == null) {
            throw new Exception("Profile");
        }
        if (user.getAnamnese().trim().equals("")) {
            throw new Exception("c");
        }
        if (user.getIdUser() == null) {
            throw new Exception("IdUser");
        }
        if (user.getIdUser().trim().equals("")) {
            throw new Exception("IdUser");
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
    public void update(Record user) throws Exception {
        if (user == null) {
            throw new Exception("User não existe");
        }
        if (user.getCpf() == null) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getCpf().trim().equals("")) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getId() == null) {
            throw new Exception("Informe o nome");
        }
        if (user.getId().trim().equals("")) {
            throw new Exception("Informe o nome");
        }
        if (user.getDt() == null) {
            throw new Exception("Ativo?");
        }
        if (user.getDt().trim().equals("")) {
            throw new Exception("Entre com 0 ou 1");
        }
        if (user.getAnamnese() == null) {
            throw new Exception("Profile");
        }
        if (user.getAnamnese().trim().equals("")) {
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
    public int ifExists(Record user) {
        int ret = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (user.getCpf().trim().equals(this.listUser.get(i).getCpf().trim())) {
                ret = i;
                break;
            }
        }
        return ret;
    }
    
    public int searchCpf(Record user) {
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
