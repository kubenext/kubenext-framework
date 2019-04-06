package com.github.kubenext.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * @author lishangjin
 */
@ConfigurationProperties(prefix = "common", ignoreUnknownFields = false)
public class CommonProperties {

    private final Cache cache = new Cache();

    private final CorsConfiguration cors = new CorsConfiguration();

    private final Http http = new Http();

    public Http getHttp() {
        return http;
    }

    public static class Http {
        public enum Version {V_1_1, V_2_0}
        private Version version = CommonDefaults.Http.version;

        public Version getVersion() {
            return version;
        }

        public void setVersion(Version version) {
            this.version = version;
        }
    }


    public CorsConfiguration getCors() {
        return cors;
    }

    public Cache getCache() {
        return cache;
    }

    public static class Cache {

        private final Hazelcast hazelcast = new Hazelcast();

        public Hazelcast getHazelcast() {
            return hazelcast;
        }

        public static class Hazelcast {

            private final ManagementCenter managementCenter = new ManagementCenter();
            private int timeToLiveSeconds = CommonDefaults.Cache.Hazelcast.timeToLiveSeconds;
            private int backupCount = CommonDefaults.Cache.Hazelcast.backupCount;
            private String instanceName = CommonDefaults.Cache.Hazelcast.instanceName;
            private int port = CommonDefaults.Cache.Hazelcast.port;
            private String defaultPackage = CommonDefaults.Cache.Hazelcast.defaultPackage;

            public String getDefaultPackage() {
                return defaultPackage;
            }

            public void setDefaultPackage(String defaultPackage) {
                this.defaultPackage = defaultPackage;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public String getInstanceName() {
                return instanceName;
            }

            public void setInstanceName(String instanceName) {
                this.instanceName = instanceName;
            }

            public ManagementCenter getManagementCenter() {
                return managementCenter;
            }

            public int getTimeToLiveSeconds() {
                return timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds) {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public int getBackupCount() {
                return backupCount;
            }

            public void setBackupCount(int backupCount) {
                this.backupCount = backupCount;
            }

            public static class ManagementCenter {

                private boolean enabled = CommonDefaults.Cache.Hazelcast.ManagementCenter.enabled;
                private int updateInterval = CommonDefaults.Cache.Hazelcast.ManagementCenter.updateInterval;
                private String url = CommonDefaults.Cache.Hazelcast.ManagementCenter.url;

                public boolean isEnabled() {
                    return enabled;
                }

                public void setEnabled(boolean enabled) {
                    this.enabled = enabled;
                }

                public int getUpdateInterval() {
                    return updateInterval;
                }

                public void setUpdateInterval(int updateInterval) {
                    this.updateInterval = updateInterval;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }


        }

    }

}
