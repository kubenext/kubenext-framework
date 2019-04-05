package com.github.kubenext.properties;

/**
 * @author lishangjin
 */
public interface CommonDefaults {

    interface Cache {

        interface Hazelcast {
            int timeToLiveSeconds = 3600;
            int backupCount = 1;
            String instanceName = "";
            int port = 5701;
            String defaultPackage = "";

            interface ManagementCenter {
                boolean enabled = false;
                int updateInterval = 3;
                String url = "";
            }
        }

    }

}
