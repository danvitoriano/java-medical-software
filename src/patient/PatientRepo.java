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

    private ArrayList<Patient> listPatient;
    private static PatientRepo instanceUserRep;
    PatientPersist persistUser = new PatientPersist();

    /**
     * get or create PatientRepo
     *
     * @return Patient Instance
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
     * @return list of Patient user
     * @throws java.lang.Exception Patient Exception
     */
    public ArrayList<Patient> listAllUsers() throws Exception {
        this.listPatient = persistUser.readData();
        return this.listPatient;
    }

    /**
     * return specific user
     *
     * @param cpf pass cpf String
     * @return Patient user
     * @throws java.lang.Exception Patient Exception
     */
    public  Patient listUser(String cpf) throws Exception {
        ArrayList<Patient> listUsers;
        listUsers = listAllUsers();
        for (Patient user : listUsers) {
            if (user.getCpf().equals(cpf)){
                return user;
            }
        } //        for (int i = 0; i < this.listPatient.size(); i++) {
//            if (user.getCpf().trim().equals(this.listPatient.get(i).getCpf().trim())) {
//                ret = i;
//                break;
//            }
//        }
        
//        int ret = -1;
//        for (int i = 0; i < this.listPatient.size(); i++) {
//            if (user.getCpf().trim().equals(this.listPatient.get(i).getCpf().trim())) {
//                ret = i;
//                break;
//            }
//        }
//        //this.listPatient = persistUser.readData();
//        JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
//        return this.listPatient;
        //return ret;
        
        //return this.listPatient;
        
        //return ret;
        return null;
          
    }

    /**
     * add an user
     *
     * @param user pass user to be inserted
     * @throws java.lang.Exception user Exception
     */
    public void insert(Patient user) throws Exception {
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
     * @param user user to be updated
     * @throws java.lang.Exception user Exception
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

        this.listPatient.set(this.ifExists(user), user);   
    }

    /**
     * Check If User Exists by CPF
     *
     * @param user pass user to check if exist
     * @return patient row position
     */
    public int ifExists(Patient user) {
        int rowPatient = -1;
        for (int i = 0; i < this.listPatient.size(); i++) {
            if (user.getCpf().trim().equals(this.listPatient.get(i).getCpf().trim())) {
                rowPatient = i;
                break;
            }
        }
        return rowPatient;
    }
    
    /**
     * Search User by CPF
     *
     * @param user pass user to check if exist in the List
     * @return Patient row position
     */
    public int searchCpf(Patient user) {
        int rowPatient = -1;
        for (int i = 0; i < this.listPatient.size(); i++) {
            if (user.getCpf().trim().equals(this.listPatient.get(i).getCpf().trim())) {
                rowPatient = i;
                break;
            }
        }
        return rowPatient;
    }
    
    /**
     * Search User by CPF
     *
     * @param cpf pass
     * @return list of Patient
     * @throws java.lang.Exception Patient Exception
     */
    public ArrayList<Patient> listAllUsers(String cpf) throws Exception {
        this.listPatient = persistUser.readData(cpf);
        return this.listPatient;
    }

}
