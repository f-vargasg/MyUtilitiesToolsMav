package com.fvgprinc.tools.utilities;

import com.fvgprinc.tools.string.MyCommonString;
import java.awt.Component;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
        Convensiones:
            pathFName.- Es un nombre de archivo con el path completo (fulll path)
            Ejemplos:

            Linux
            /home/users/fvargas/Final.txt

            Windows
            C:\Users\garfi\OneDrive\Documentos\Final.txt


            fName.- es unicamente el nombre de archivo (filename)
            Final.txt 
 */
public class FileUtilities {

    private String errorMsg;
    private String actualFileName;

    public FileUtilities() {
        errorMsg = new String();
    }

    /**
     * Lee el template file de disco.
     */
    public String readFileText(String filename) {
        int len;
        char chars[] = null;
        BufferedReader inStream;

        // abrir y leer archivo
        try {
            File inFile = new File(filename);
            inStream = new BufferedReader(new FileReader(inFile));
            chars = new char[(int) inFile.length()];
            len = inStream.read(chars);
            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            errorMsg = "Missing File: " + filename;
        } catch (Exception e) {
            System.out.println(e);
            errorMsg = "Exception File: " + filename;
        }
        String template = new String(chars);

        return template;
    }

    /**
     * getDocumentByteBuffer : Obtiene la información de un archivo determinado.
     *
     * @return archivo binario guardo en un arreglo de bytes.
     * @param infoDocumento Información Básica del documento.
     */
    public byte[] getDocumentByteBuffer(String infoFilePath) {
        ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
        try {

            File archivo = new File(infoFilePath);
            if (archivo.exists()) {
                // Invocar al servicio web que trae el documento del cliente
                // actual.
                actualFileName = archivo.getName();
                InputStream in = new FileInputStream(archivo);
                byte[] buffer = new byte[1024 * 8];// 1024 bytes
                int offset = 0;
                int numRead = 0;
                while ((numRead = in.read(buffer)) >= 0) {
                    bufferStream.write(buffer, 0, numRead);
                    offset += numRead;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return bufferStream.toByteArray();
    }

    /**
     * Determina si existe un archivo.
     */
    public boolean existFile(String filename) {
        boolean retorno = false;
        try {
            File inFile = new File(filename);
            retorno = inFile.exists();
        } catch (Exception e) {
            System.out.println(e);
        }
        return retorno;
    }

    public boolean writeFile(String buffer, String targetFile) {

        boolean success = true;
        // Escritura de la salida del parser

        if (buffer != null && buffer.length() > 1) {

            try {

                BufferedWriter fileBufferedWriter;
                File inputFile = new File(targetFile);
                fileBufferedWriter = new BufferedWriter(new FileWriter(
                        inputFile));
                fileBufferedWriter.write(buffer);
                fileBufferedWriter.close();
            } catch (Exception e) {
                success = false;
                System.out.println("Error a la hora de escribir archivo ..."
                        + targetFile);
            }

        } // if (buffer.length()>1){
        return success;
    }

    public boolean cleanFile(String actualFile) {
        boolean result = true;
        try {
            File dropFile = new File(actualFile);
            dropFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean makeTreeFiles(String path) {
        boolean retorno = false;
        try {
            File fileVerificator = new File(path);
            String parent = fileVerificator.getParent();
            File parentPath = new File(fileVerificator.getParent());

            if (!parentPath.exists()) {
                System.out.println("La ruta dada no existe, creando estructura para "
                        + fileVerificator.getParent());
                retorno = parentPath.mkdirs();
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return retorno;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getActualFileName() {
        return actualFileName;
    }

    public static String combine(String path1, String path2) {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);
        return file2.getPath();
    }

    public static String fnameWoExtension(String fname) {
        int dotIndex = fname.lastIndexOf(".");
        String res = (dotIndex == -1) ? fname : fname.substring(0, dotIndex);
        return res;
    }

    public static String fileExtension(String fname) {
        int dotIndex = fname.lastIndexOf(".");
        String res = (dotIndex == -1) ? MyCommonString.EMPTYSTR : fname.substring(dotIndex + 1);
        return res;
    }

    public static String getPathFromPathFname(String pathFname) {
        String res;
        int ultimoIndiceSeparador = pathFname.lastIndexOf(File.separator);
        res = pathFname.substring(0, ultimoIndiceSeparador);
        return res;
    }

    public static int showMySaveDialog(Component parent, JFileChooser fileChooser, String currentPath,
            FileNameExtensionFilter extensionFilter, String title) {
        int res;
        File curPath = new File(currentPath);
        fileChooser.setCurrentDirectory(curPath);
        fileChooser.setFileFilter(extensionFilter);
        fileChooser.setDialogTitle(title);
        res = fileChooser.showSaveDialog(parent);
        return res;
    }

    public static void showDestFileFolder(String message, File fileToSave) throws IOException {
        // Preguntar al usuario si desea abrir la carpeta
        int opcion = JOptionPane.showConfirmDialog(null, message, "Abrir carpeta",
                JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            File carpeta = fileToSave.getParentFile();
            Desktop.getDesktop().open(carpeta);
        }
    }

}
