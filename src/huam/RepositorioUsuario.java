/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huam;

import java.util.ArrayList;

/**
 *
 * @author dvitoriano
 */
public class RepositorioUsuario {
    private ArrayList<Usuario> lista;
    private static RepositorioUsuario instanciaRep;
PersistenciaUsuario persistenciaUsuario = new PersistenciaUsuario();
    
    public static RepositorioUsuario obterInstancia(){
        if(instanciaRep == null){
            instanciaRep = new RepositorioUsuario();
        }
        return instanciaRep;
    }
    
    public ArrayList<Usuario> listarTodos() throws Exception{
        this.lista = persistenciaUsuario.lerDados();
        return this.lista;
    }
    
    public void inserir(Usuario usuario) throws Exception{
        if (usuario == null){
            throw new Exception("Usuario não existe");
        }
        if (usuario.getCPF() == null){
            throw new Exception("Informe o CPF");
        }
        if (usuario.getCPF().trim().equals("")){
            throw new Exception("Informe o CPF");
        }
        if (usuario.getNomeUsuario() == null){
            throw new Exception("Informe o nome");
        }
        if (usuario.getNomeUsuario().trim().equals("")){
            throw new Exception("Informe o nome");
        }
        if (this.verificaExistencia(usuario) >= 0){
            throw new Exception("Usuario já cadastrado");
        }
        persistenciaUsuario.gravarDados(usuario);
    }
    
    public void remover(Usuario usuario) throws Exception{
        if (usuario == null){
            throw new Exception("Usuario não existe");
        }
        if (usuario.getCPF() == null){
            throw new Exception("Informe o CPF");
        }
        if (usuario.getCPF().trim().equals("")){
            throw new Exception("Informe o CPF");
        }
        if (this.verificaExistencia(usuario) == -1){
            throw new Exception("Usuario não encontrado");
        }
        persistenciaUsuario.Deletar(usuario);
         //this.lista.remove(this.verificaExistencia(usuario));
    }
    
    public void atualizar(Usuario usuario) throws Exception{
        if (usuario == null){
            throw new Exception("Usuario não existe");
        }
        if (usuario.getCPF() == null){
            throw new Exception("Informe o CPF");
        }
        if (usuario.getCPF().trim().equals("")){
            throw new Exception("Informe o CPF");
        }
        if (usuario.getNomeUsuario() == null){
            throw new Exception("Informe o nome");
        }
        if (usuario.getNomeUsuario().trim().equals("")){
            throw new Exception("Informe o nome");
        }
        if (this.verificaExistencia(usuario) == -1){
            throw new Exception("Usuario não cadastrado");
        }
        persistenciaUsuario.Atualizar(usuario);
        
        //this.lista.set(this.verificaExistencia(usuario), usuario);   
   }
    
    public int verificaExistencia(Usuario usuario){
        int retorno = -1;
        for (int i = 0; i < this.lista.size(); i++){
            if (usuario.getCPF().trim().equals(this.lista.get(i).getCPF().trim())){
                retorno = i;
                break;
            }
        }
        return retorno;
    }
    
    
}
