/*
 * 
 */
package tcc.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Gerenciador dos anexos armazenados no NAS
 */
@Component
public class AnexoFileSystemManager {

    private String rootPath;
    
    @Autowired
    public AnexoFileSystemManager(@Value("${filepath}") String rootPath) {
    }
    
    public void setRootPath(String rootPath){
        this.rootPath = rootPath;
        if (!this.rootPath.substring(this.rootPath.length() - 1).equals(File.separator)) {
            this.rootPath += File.separator;
        }
    }
    
    public InputStream getAnexo(Long idAnexo) throws FileNotFoundException {
        FileInputStream inputStream = null;
        try {
            String finalFilePath = getFilePath(rootPath, idAnexo, false);
            File anexo = new File(finalFilePath);
            if (!anexo.exists()) {
                throw new FileNotFoundException("Arquivo " + finalFilePath + "não existe.");
            }
            inputStream = new FileInputStream(finalFilePath);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return inputStream;
    }
    
    public void sendAnexo(Long idAnexo, InputStream uploadedInputStream) throws IOException {
        FileOutputStream outputStream = null;
        try {
            String finalFilePath = getFilePath(rootPath, idAnexo, true);
            outputStream = new FileOutputStream(finalFilePath);
            IOUtils.copy(uploadedInputStream, outputStream);
            outputStream.flush();
        } catch (IOException ioe) {
            throw new IOException(ioe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(uploadedInputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }
    
    public void excluirAnexo(Long idAnexo) {
        String finalFilePath = getFilePath(rootPath, idAnexo, false);
        File file = new File(finalFilePath);
        if (file.exists()) {
            if (!file.delete()) {
                throw new AbstractMethodError("Não foi possível excluir arquivo " + finalFilePath);
            }
        }
    }
    
    private String getFilePath(String rootPath, Long id, boolean createParentDirectories) {
        StringBuilder retorno = new StringBuilder(rootPath);
        String strId = String.format("%03d", id);
        for (int i = 0; i < 3; i++) {
            retorno.append(strId.charAt(i)).append("/");
        }
        if (createParentDirectories) {
            createParentDirectories(retorno.toString());
        }
        retorno.append(strId);
        return retorno.toString();
    }
    
    private void createParentDirectories(String filePath) {
        final File file = new File(filePath);
        boolean result = file.mkdirs();
    }
    
    
}
