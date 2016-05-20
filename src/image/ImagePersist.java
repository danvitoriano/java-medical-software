/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author dvitoriano
 */
public class ImagePersist {

    /**
     * record an user
     *
     * @param user
     * @throws java.io.FileNotFoundException
     */
    public void setData(Image user) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("images.txt", true);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(user.getId() + ";");
        bw.write(user.getIdRecord() + ";");
        bw.write(user.getUrl() + ";");
        bw.write("\n");
        bw.close();
    }

    /**
     * list all images
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    public ArrayList<Image> readData(String imgIdRecord) throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("images.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<Image> images = new ArrayList<>();
        String line = reader.readLine();
        
        while (line != null) {
            String[] params = line.split(";");
            String id = params[0];
            String idRecord = params[1];
            String url = params[2];
            
            if(imgIdRecord == null || idRecord.equals(imgIdRecord)){
                Image image = new Image();
                image.setId(id);
                image.setIdRecord(idRecord);
                image.setUrl(url);

                images.add(image);
            }
            
            line = reader.readLine();
        }
        return images;
    }
    
    /**
     * list all images
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    public ArrayList<Image> readData2(String imgId) throws FileNotFoundException, IOException, Exception {
        
        
        InputStream input = new FileInputStream("images.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<Image> images = new ArrayList<>();
        String line = reader.readLine();
        
        while (line != null) {
            String[] params = line.split(";");
            String id = params[0];
            String idRecord = params[1];
            String url = params[2];
            
            if(imgId == null || id.equals(imgId)){
                Image image = new Image();
                image.setId(id);
                image.setIdRecord(idRecord);
                image.setUrl(url);

                images.add(image);
            }
            
            line = reader.readLine();
        }
        return images;
    }
    
    
    

    /**
     * update an image
     *
     * @param image
     * @throws java.io.FileNotFoundException
     */
    public void updateData(Image image) throws FileNotFoundException, IOException, Exception {
        ArrayList<Image> users = readData(null);
        ArrayList<String> newLines = new ArrayList<>();
        Image linha;
        Integer buscar = Integer.parseInt(image.getId());
//        String searchCpf = image.obterIdentificador();
        for (int i = 0; i < users.size(); i++) {

            linha = users.get(i);

            if (buscar == Integer.parseInt(linha.getId())) {
                linha.setId(image.getId());
                linha.setIdRecord(image.getIdRecord());
                linha.setUrl(image.getUrl());
            }
            newLines.add(linha.getId() + ";"
                    + linha.getIdRecord() + ";"
                    + linha.getUrl() + ";\n");
        }
        try (FileOutputStream fileOut = new FileOutputStream("images.txt")) {
            for (String newLine : newLines) {
                fileOut.write(newLine.getBytes());
            }
            fileOut.close();
        }
    }
    
    public String obterIdentificador() throws Exception{
        ArrayList<Image> records = readData(null);
        int i = 1;
        for (Image record : records) {
            int id = Integer.parseInt(record.getId());
            if (id>i) {
                i = id + 1;
            }
        }
        return String.valueOf(i);
    }

}
