/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import java.awt.Dimension;
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
     * @return an ImageRepo Instance
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
     * @param idRecord pass ID Record String
     * @return return a list of user
     * @throws java.lang.Exception User Data Error
     */
    public ArrayList<Image> listAllUsers(String idRecord) throws Exception {
        this.listUser = persistUser.readData(idRecord);
        return this.listUser;
    }



    /**
     * add an image
     *
     * @param image pass image to insert
     * @throws java.lang.Exception if Error to insert Image
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
        
        persistUser.setData(image);
    }

    /**
     * update an user
     *
     * @param user update User
     * @throws java.lang.Exception if error to update User
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
     * Check If Image Exists
     *
     * @param image pass the image to verify
     * @return return image id
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

        /**
        * Accept File
        *
        * @param file verify file to save
        * @return if file has saved
        */
        @Override
        public boolean accept(File file){
            for (String extension : okFileExtensions) {
                if (file.getName().toLowerCase().endsWith(extension)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    /**
    * Save Image
    *
    * @param file pass file to save
    * @param time pass time to save
    * @throws java.io.IOException Save Image Error
    */
    public static void saveImage(File file, String time) throws IOException{
        //String caminhoImagem = dataHora + file.getName();
        String imagePath = null;
        try {
            imagePath = ImageSave.pathVerify();
        } catch (IOException ex) {
            Logger.getLogger(ImageRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagePath = time + file.getName();
        file.renameTo(new File(imagePath));
    }
    
    /**
    * CPF Search
    *
     * @param user pass user to search
     * @return user id
    */
    public int searchCpf(Image user) {
        int userID = -1;
        for (int i = 0; i < this.listUser.size(); i++) {
            if (user.getId().trim().equals(this.listUser.get(i).getId().trim())) {
                userID = i;
                break;
            }
        }
//        JOptionPane.showMessageDialog(null, ret);
        return userID;
    }
    
    /**
    * Get Image Dimension
    *
    * @param imgWidth image Width
    * @param imgHeight image Height
    * @param maxWidth image Max Width
    * @param maxHeight image MAx Height
    * @return new image dimension
    */
    public static Dimension getDimension(int imgWidth, int imgHeight, int maxWidth, int maxHeight) {

        int newWidth = imgWidth;
        int newHeight = imgHeight;

        //Verify if original width is larger than maxWidth
        if (imgWidth > maxWidth) {
            //Define max width
            newWidth = maxWidth;
            //redefine max height proportionaly
            newHeight = (newWidth * imgHeight) / imgWidth;
        }

        //Verify if original height is larger than maxHeight
        if (newHeight > maxHeight) {
            //Define max height
            newHeight = maxHeight;
            //redefine max width proportionaly
            newWidth = (newHeight * imgWidth) / imgHeight;
        }
        //retunr the new image dimension
        return new Dimension(newWidth, newHeight);
    }
    /**
     * List All user
     *
     * @param id pass User ID String
     * @param idRecord pass IdRecord String
     * @return  return a list of image from this user
     * @throws java.lang.Exception read data exception
    */ 
    public ArrayList<Image> listAllUsers2(String id, String idRecord) throws Exception {
        this.listUser = persistUser.readData2(id,idRecord);
        return this.listUser;
    }

}
