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

    private final Async async = new Async();

    private final Swagger swagger = new Swagger();

    private final Logging logging = new Logging();

    private final Metrics metrics = new Metrics();

    public Metrics getMetrics() {
        return metrics;
    }

    public static class Metrics {

        private final Logs logs = new Logs();

        public Logs getLogs() {
            return logs;
        }

        public static class Logs {

            private boolean enabled = CommonDefaults.Metrics.Logs.enabled;

            private long reportFrequency = CommonDefaults.Metrics.Logs.reportFrequency;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public long getReportFrequency() {
                return reportFrequency;
            }

            public void setReportFrequency(long reportFrequency) {
                this.reportFrequency = reportFrequency;
            }
        }
    }

    public Logging getLogging() {
        return logging;
    }

    public static class Logging {

        private final Logstash logstash = new Logstash();

        public Logstash getLogstash() {
            return logstash;
        }

        public static class Logstash {
            private boolean enabled = CommonDefaults.Logging.Logstash.enabled;
            private String host = "localhost";
            private int port = CommonDefaults.Logging.Logstash.port;
            private int queueSize = CommonDefaults.Logging.Logstash.queueSize;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public int getQueueSize() {
                return queueSize;
            }

            public void setQueueSize(int queueSize) {
                this.queueSize = queueSize;
            }
        }

    }

    public Swagger getSwagger() {
        return swagger;
    }

    public static class Swagger {
        private String title = CommonDefaults.Swagger.title;
        private String description = CommonDefaults.Swagger.description;
        private String version = CommonDefaults.Swagger.version;
        private String termsOfServiceUrl = CommonDefaults.Swagger.termsOfServiceUrl;
        private String contactName = CommonDefaults.Swagger.contactName;
        private String contactUrl = CommonDefaults.Swagger.contactUrl;
        private String contactEmail = CommonDefaults.Swagger.contactEmail;
        private String license = CommonDefaults.Swagger.license;
        private String licenseUrl = CommonDefaults.Swagger.licenseUrl;
        private String defaultIncludePattern = CommonDefaults.Swagger.defaultIncludePattern;
        private String host = CommonDefaults.Swagger.host;
        private String[] protocols = CommonDefaults.Swagger.protocols;
        private boolean useDefaultResponseMessages = CommonDefaults.Swagger.useDefaultResponseMessages;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public String getDefaultIncludePattern() {
            return defaultIncludePattern;
        }

        public void setDefaultIncludePattern(String defaultIncludePattern) {
            this.defaultIncludePattern = defaultIncludePattern;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String[] getProtocols() {
            return protocols;
        }

        public void setProtocols(String[] protocols) {
            this.protocols = protocols;
        }

        public boolean isUseDefaultResponseMessages() {
            return useDefaultResponseMessages;
        }

        public void setUseDefaultResponseMessages(boolean useDefaultResponseMessages) {
            this.useDefaultResponseMessages = useDefaultResponseMessages;
        }
    }


    public Async getAsync() {
        return async;
    }

    public static class Async {
        private int corePoolSize = CommonDefaults.Async.corePoolSize;
        private int maxPoolSize = CommonDefaults.Async.maxPoolSize;
        private int queueCapacity = CommonDefaults.Async.queueCapacity;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }

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
