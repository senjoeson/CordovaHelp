package com.senjoeson.cordovahelp.bean;

public class ProjectInfo {

    private String projectPath;    //项目路径
    private String modulePath;     //插件路径
    private String projectRootPath;

    private String projectPackageName;
    private String modulePackageName;
    private String projectVersion;
    private String moduleVersion;
    private String projecName;
    private String moduleName;

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public String getProjectRootPath() {
        return projectRootPath;
    }

    public void setProjectRootPath(String projectRootPath) {
        this.projectRootPath = projectRootPath;
    }

    public String getProjectPackageName() {
        return projectPackageName;
    }

    public void setProjectPackageName(String projectPackageName) {
        this.projectPackageName = projectPackageName;
    }

    public String getModulePackageName() {
        return modulePackageName;
    }

    public void setModulePackageName(String modulePackageName) {
        this.modulePackageName = modulePackageName;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    public void setModuleVersion(String moduleVersion) {
        this.moduleVersion = moduleVersion;
    }

    public String getProjecName() {
        return projecName;
    }

    public void setProjecName(String projecName) {
        this.projecName = projecName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
