package com.tec.datos1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JarAnalyze {
    private String descrip;
    private String groupId;
    private String artifactID;
    private String version;
    private dependencias[] dependencias;

    public JarAnalyze() {
    }

    public JarAnalyze( String descrip, String groupId, String artifactID, String version, com.tec.datos1.dependencias[] dependencias) {
        this.descrip = descrip;
        this.groupId = groupId;
        this.artifactID = artifactID;
        this.version = version;
        this.dependencias = dependencias;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactID() {
        return artifactID;
    }

    public void setArtifactID(String artifactID) {
        this.artifactID = artifactID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public com.tec.datos1.dependencias[] getDependencias() {
        return dependencias;
    }

    public void setDependencias(com.tec.datos1.dependencias[] dependencias) {
        this.dependencias = dependencias;
    }

    public  void analizadorHTML(String data) {
        {

            /**Nombre del descripcion JAR*/
            Pattern pDescript = Pattern.compile("<description>(.*?)</description>", Pattern.MULTILINE);
            Matcher mDescrip = pDescript.matcher(data);
            while (mDescrip.find()){
                //System.out.println(mDescrip.group(1));
                this.descrip=(mDescrip.group(1));
            }
            /**Nombre del groupID JAR*/
            Pattern pGroupJAR = Pattern.compile("groupId=(.*)artifactId", Pattern.MULTILINE);
            Matcher mJARGROUP = pGroupJAR.matcher(data);
            while (mJARGROUP.find()){
                //System.out.println(mJARGROUP.group(1));
                this.groupId=mJARGROUP.group(1);
            }
            /**Nombre del artifactID JAR*/
            Pattern pArtifactJAR = Pattern.compile("artifactId=(.*)", Pattern.MULTILINE);
            Matcher mJARArtifact = pArtifactJAR.matcher(data);
            while (mJARArtifact.find()){
               // System.out.println(mJARArtifact.group(1));
                this.artifactID=mJARArtifact.group(1);
            }

            /**Nombre del version JAR*/
            Pattern pVersionJAR = Pattern.compile("version=(.*)groupId", Pattern.MULTILINE);
            Matcher mJARVersion = pVersionJAR.matcher(data);
            while (mJARVersion.find()){
             //   System.out.println(mJARVersion.group(1));
                this.version = mJARVersion.group(1);
            }

            /**--------------------DEPENDENCIAS---------------------*/

            Pattern groupPattern = Pattern.compile("<dependency> <groupId>(.*?)</groupId> ", Pattern.DOTALL);

            Matcher mGroup = groupPattern.matcher(data);
            int cantidadDependencias = 0;

            /**ciclo para ver la cantidad de dependencias que se van a insertar en un array de dependencias*/
            while (mGroup.find()) {
                cantidadDependencias++;
            }
            /**Es el array que se utilizara*/
            dependencias[] dependenciasJar = new dependencias[cantidadDependencias];
            int pos = 0;

            /**Inserta el groupId que va encontrando al array */
            Matcher mmGroup = groupPattern.matcher(data);
            while (mmGroup.find()) {
                if (pos < cantidadDependencias) {
                    /**Se crea la dependencia y */
                    dependenciasJar[pos] = new dependencias();
                    dependenciasJar[pos].setGroupId(mmGroup.group(1));
                }
                pos++;
            }

            /**Parsea el html por artifact que se van insertando al array de dependencias segun el orden*/
            Pattern artifactPattern = Pattern.compile("<artifactId>(.*?)</artifactId>");
            Matcher martifact = artifactPattern.matcher(data);
            pos = 0;
            while (martifact.find()) {
                if (pos < cantidadDependencias) {
                    dependenciasJar[pos].setArtifactId(martifact.group(1));
                }
                pos++;
            }
            /**Parsea el html por version que se van insertando al array de dependencias segun el orden*/
            Pattern versionPattern = Pattern.compile("<version>(.*?)</version>");
            Matcher mversion = versionPattern.matcher(data);
            pos = 0;
            while (mversion.find()) {
                if (pos < cantidadDependencias) {
                    dependenciasJar[pos].setVersion(mversion.group(1));
                }
                pos++;
            }
            this.dependencias=dependenciasJar;

        }
    }


}
