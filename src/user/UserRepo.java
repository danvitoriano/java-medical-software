/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dvitoriano
 */
public class UserRepo {

    private ArrayList<User> lista;
    private static UserRepo instanciaRep;
    UserPersist persistenciaUser = new UserPersist();

    /**
     * get or create UserRepo
     *
     * @return
     */
    public static UserRepo obterInstancia() {
        if (instanciaRep == null) {
            instanciaRep = new UserRepo();
        }
        return instanciaRep;
    }

    /**
     * return all users
     *
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<User> listarTodos() throws Exception {
        this.lista = persistenciaUser.lerDados();
        return this.lista;
    }

    /**
     * return specific user
     *
     * @param user
     * @return
     * @throws java.lang.Exception
     */
    public  ArrayList<User> listarUser(User user) throws Exception {
        int retorno = -1;
        for (int i = 0; i < this.lista.size(); i++) {
            if (user.getCpf().trim().equals(this.lista.get(i).getCpf().trim())) {
                retorno = i;
                break;
            }
        }
        //this.lista = persistenciaUser.lerDados();
        JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
        return this.lista;
        //return retorno;
        
        //return this.lista;
        
        //return retorno;
        
          
    }

    /**
     * add an user
     *
     * @param user
     * @throws java.lang.Exception
     */
    public void inserir(User user) throws Exception {
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
            throw new Exception("c");
        }
        if (this.verificaExistencia(user) >= 0) {
            throw new Exception("User já cadastrado");
        }
        persistenciaUser.gravarDados(user);
    }

    /**
     * update an user
     *
     * @param user
     * @throws java.lang.Exception
     */
    public void atualizar(User user) throws Exception {
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
        if (this.verificaExistencia(user) == -1) {
            throw new Exception("User não cadastrado");
        }
        persistenciaUser.Atualizar(user);

        //this.lista.set(this.verificaExistencia(user), user);   
    }

    /**
     * check if user exists
     *
     * @param user
     * @return
     */
    public int verificaExistencia(User user) {
        int retorno = -1;
        for (int i = 0; i < this.lista.size(); i++) {
            if (user.getCpf().trim().equals(this.lista.get(i).getCpf().trim())) {
                retorno = i;
                break;
            }
        }
        return retorno;
    }
    
    public int searchCpf(User user) {
        int retorno = -1;
        for (int i = 0; i < this.lista.size(); i++) {
            if (user.getCpf().trim().equals(this.lista.get(i).getCpf().trim())) {
                retorno = i;
                break;
            }
        }
//        JOptionPane.showMessageDialog(null, retorno);
        return retorno;
    }

    
    public class Vetor {

        private final ArrayList<User> users;

        public Vetor() throws Exception {
            this.users = listarTodos();
        }

        /**
         *
         * @param key
         * @return
         */
//        public int buscaBinaria(int key, int inicio, int fim) {
////
////        if (inicio > fim)
////            return -1;
////        int meio = (inicio + fim) / 2;
////        if(key == users[meio])
////            return meio; // encontrou a chave
////        if(key < users[meio]) // calcula os indices para a proxima iteraçao
////            return buscaBinaria(key, inicio, meio - 1);
////        else
////            return buscaBinaria(key, meio + 1, fim );
////        
//
//        }

    }

}
