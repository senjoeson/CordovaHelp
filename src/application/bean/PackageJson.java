package application.bean;

import java.util.List;

/**
 * @author sunqiao
 * @date 2018/7/25 17:47
 */

public class PackageJson {

    /**
     * name : cordova-plugin-themeablebrowser
     * version : 0.2.17
     * description : Cordova ThemeableBrowser Plugin
     * cordova : {"id":"cordova-plugin-themeablebrowser","platforms":["android","amazon-fireos","ubuntu","ios","wp7","wp8","windows8","windows","firefoxos"]}
     * repository : {"type":"git","url":"https://github.com/initialxy/cordova-plugin-themeablebrowser"}
     * keywords : ["cordova","in","app","browser","themeablebrowser","ecosystem:cordova","cordova-android","cordova-ios"]
     * engines : [{"name":"cordova","version":">=3.1.0"}]
     * author : Apache Software Foundation
     * license : Apache 2.0
     */

    private String name;
    private String version;
    private String description;
    private CordovaBean cordova;
    private RepositoryBean repository;
    private String author;
    private String license;
    private List<String> keywords;
    private List<EnginesBean> engines;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CordovaBean getCordova() {
        return cordova;
    }

    public void setCordova(CordovaBean cordova) {
        this.cordova = cordova;
    }

    public RepositoryBean getRepository() {
        return repository;
    }

    public void setRepository(RepositoryBean repository) {
        this.repository = repository;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<EnginesBean> getEngines() {
        return engines;
    }

    public void setEngines(List<EnginesBean> engines) {
        this.engines = engines;
    }

    public static class CordovaBean {
        /**
         * id : cordova-plugin-themeablebrowser
         * platforms : ["android","amazon-fireos","ubuntu","ios","wp7","wp8","windows8","windows","firefoxos"]
         */

        private String id;
        private List<String> platforms;

        public CordovaBean(String id, List<String> platforms) {
            this.id = id;
            this.platforms = platforms;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getPlatforms() {
            return platforms;
        }

        public void setPlatforms(List<String> platforms) {
            this.platforms = platforms;
        }
    }

    public static class RepositoryBean {
        /**
         * type : git
         * url : https://github.com/initialxy/cordova-plugin-themeablebrowser
         */

        private String type;
        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class EnginesBean {
        /**
         * name : cordova
         * version : >=3.1.0
         */

        private String name;
        private String version;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
