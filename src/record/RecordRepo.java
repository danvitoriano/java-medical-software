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
     * Recordrepo Instance
     *
     * @return return RecordRepo Instance
     */
    public static RecordRepo getInstance() {
        if (instanceUserRep == null) {
            instanceUserRep = new RecordRepo();
        }
        return instanceUserRep;
    }

    /**
     * Return List of Record
     *
     * @param cpf pass CPF
     * @return the list of Record
     * @throws java.lang.Exception Record Exception
     */
    public ArrayList<Record> listAllUsers(String cpf) throws Exception {
        this.listUser = persistUser.readData(cpf);
        return this.listUser;
    }

    /**
     * Add User
     *
     * @param user pass user to be saved
     * @throws java.lang.Exception User Exception
     */
    public void insert(Record user) throws Exception {
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
        if (user.getRecord() == null) {
            throw new Exception("Profile");
        }
        if (user.getRecord().trim().equals("")) {
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
     * Update User
     *
     * @param user pass user to be updated
     * @throws java.lang.Exception User Exception
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
        if (user.getRecord() == null) {
            throw new Exception("Profile");
        }
        if (user.getRecord().trim().equals("")) {
            throw new Exception("informe o tipo");
        }
        if (this.ifExists(user) == -1) {
            throw new Exception("User não cadastrado");
        }
        persistUser.updateData(user);

        this.listUser.set(this.ifExists(user), user);   
    }

    /**
     * Check if Record Id exist
     *
     * @param id pass record id
     * @return return if record exist (boolean)
     */
    public int ifExists(Record id) {
        int recordId = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (id.getId().trim().equals(this.listUser.get(i).getId().trim())) {
                recordId = i;
                break;
            }
        }
        return recordId;
    }
    
    /**
     * Check if User CPF exist in the User List
     *
     * @param user pass user obect
     * @return return if user exist (boolean)
     */
    public int searchCpf(Record user) {
        int rowRecord = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (user.getCpf().trim().equals(this.listUser.get(i).getCpf().trim())) {
                rowRecord = i;
                break;
            }
        }
//        JOptionPane.showMessageDialog(null, ret);
        return rowRecord;
    }

}
