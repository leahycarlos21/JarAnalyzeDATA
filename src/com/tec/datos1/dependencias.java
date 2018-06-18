package com.tec.datos1;

public class dependencias {
    private String groupId;
    private String artifactId;
    private String version;

    public void dependencias(String groupId,String artifactId,String version){
        this.artifactId=artifactId;
        this.groupId=groupId;
        this.version=version;

    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
