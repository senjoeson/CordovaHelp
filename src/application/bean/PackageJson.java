package application.bean;

import java.util.List;

/**
 * @author sunqiao
 * @date 2018/7/25 17:47
 */

public class PackageJson {

    /**
     * name : KayakDevicePlugin
     * version : 1.0.0
     * description : 设备信息
     * cordova : {"id":"com.kayak.plugin.deviceplugin","platforms":[]}
     * repository : {"type":"git","url":"git@10.5.0.182:kayakplug/KayakDevicePlugin.git"}
     * keywords : ["ecosystem:cordova"]
     * author : xmzhang
     * license : ISC
     */

    private String name;
    private String version;
    private String description;
    private CordovaBean cordova;
    private RepositoryBean repository;
    private String author;
    private String license;
    private String keywords;

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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public static class CordovaBean {
        /**
         * id : com.kayak.plugin.deviceplugin
         * platforms : []
         */

        private String id;
        private List<?> platforms;

        public CordovaBean(String id, List<?> platforms) {
            this.id = id;
            this.platforms = platforms;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<?> getPlatforms() {
            return platforms;
        }

        public void setPlatforms(List<?> platforms) {
            this.platforms = platforms;
        }
    }

    public static class RepositoryBean {
        /**
         * type : git
         * url : git@10.5.0.182:kayakplug/KayakDevicePlugin.git
         */

        private String type;
        private String url;

        public RepositoryBean(String type, String url) {
            this.type = type;
            this.url = url;
        }

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
}
