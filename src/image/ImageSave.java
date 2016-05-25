/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * @author vitoriano
 */
public class ImageSave {
        public static String pathVerify() throws IOException {
            String folder = new File("").getAbsolutePath();
            if(!Paths.get(folder).toFile().exists()) {
                new File(folder).mkdir();
            }
            return folder;
        }
}
