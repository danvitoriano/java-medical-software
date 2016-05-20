/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.JOptionPane;

/**
 *
 * @author dvitoriano
 */
public class ImageRepo {
    private ArrayList<Image> listUser;
    private static ImageRepo instanceUserRep;
    public static javax.swing.filechooser.FileFilter ImageFileFilter;
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
    public ArrayList<Image> listAllUsers(String idRecord) throws Exception {
        this.listUser = persistUser.readData(idRecord);
        return this.listUser;
    }



    /**
     * add an image
     *
     * @param image
     * @throws java.lang.Exception
     */
    public void inserir(Image image) throws Exception {
        if (image == null) {
            throw new Exception("User don't exist");
        }
        if (image.getId() == null) {
            throw new Exception("Informe o Cpf");
        }
        if (image.getId().trim().equals("")) {
            throw new Exception("Informe o Cpf");
        }
        if (image.getIdRecord() == null) {
            throw new Exception("Informe o nome");
        }
        if (image.getIdRecord().trim().equals("")) {
            throw new Exception("Informe o nome");
        }
        if (image.getUrl() == null) {
            throw new Exception("Profile");
        }
        if (image.getUrl().trim().equals("")) {
            throw new Exception("c");
        }
        if (this.ifExists(image) >= 0) {
            throw new Exception("User já cadastrado");
        }
        persistUser.setData(image);
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
        if (user.getId() == null) {
            throw new Exception("Informe o Cpf");
        }
        if (user.getId().trim().equals("")) {
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
     * check if image exists
     *
     * @param image
     * @return
     */
    public int ifExists(Image image) {
        int ret = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (image.getId().trim().equals(this.listUser.get(i).getId().trim())) {
                ret = i;
                break;
            }
        }
        return ret;
    }
    
    public class ImageFileFilter implements FileFilter {
        private final String[] okFileExtensions = new String[] {"jpg"};

        public boolean accept(File file){
            for (String extension : okFileExtensions) {
                if (file.getName().toLowerCase().endsWith(extension)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public static void salvarImagem(File file, String dataHora) throws IOException{
        String caminhoImagem = null;
        caminhoImagem = caminhoImagem + File.separator + dataHora + file.getName();
        file.renameTo(new File(caminhoImagem));
    }
    
    public int searchCpf(Image user) {
        int retorno = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (user.getId().trim().equals(this.listUser.get(i).getId().trim())) {
                retorno = i;
                break;
            }
        }
//        JOptionPane.showMessageDialog(null, ret);
        return retorno;
    }


}
