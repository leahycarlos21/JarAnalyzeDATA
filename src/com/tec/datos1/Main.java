package com.tec.datos1;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main {
    private static String pathDirectory;

    public static void main(String[] args) {

        pathChooser1 pathChooser1 = new pathChooser1();
        pathDirectory = pathChooser1.obtenerPath1();
        /**Jar entrante */
        try (ZipFile zipFile = new ZipFile(pathDirectory)) {//facebook-messenger-1.0.0.jar
            /**String donde se almacena la */
            String allString = "";
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();

                InputStream inputStream = zipFile.getInputStream(zipEntry);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

                String string = null;
                while ((string = bufferedReader.readLine()) != null) {
                    allString += "\n" + string;
                }
            }
            /**
             * separa el string hasta que lea el '<project'
             */
            String[] parts = allString.split("<project");
            /**parts[1] tiene la los datos después del project, osea, el resto del XML*/
            String html = parts[1];
            /**Se le agrega en <project para que sea el XML completo*/
            html = "<project" + html;
            System.out.println(html);
            html = html.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\\s{2,}", " ").trim();
            /**Muestra  el html*/
            System.out.println(html);
            System.out.println("-------------------------");

            /**Se inserta el html y el JarAnalyze inserta los datos al atributo de jarAnalyze*/
            JarAnalyze jarAnalyze = new JarAnalyze();
            jarAnalyze.analizadorHTML(html);

            System.out.println("\n------DATOS DEL JAR----");
            System.out.println("GroupID del JAR   "+jarAnalyze.getGroupId());
            System.out.println("ArtifactID del JAR   "+jarAnalyze.getArtifactID());
            System.out.println("Version del JAR   "+jarAnalyze.getVersion());
            System.out.println("Description del JAR   "+jarAnalyze.getDescrip());


            System.out.println("\n------DEPENDENCIAS----");

            for (int i=0;i<jarAnalyze.getDependencias().length;i++){
                System.out.println("\nGROUPID ---"+jarAnalyze.getDependencias()[i].getGroupId());
                System.out.println("ARTIFACTID ---"+jarAnalyze.getDependencias()[i].getArtifactId());
                System.out.println("VERSIONID ---"+jarAnalyze.getDependencias()[i].getVersion());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}









