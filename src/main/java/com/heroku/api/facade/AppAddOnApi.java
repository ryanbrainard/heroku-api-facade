package com.heroku.api.facade;

import com.heroku.api.connection.Connection;

/**
 * @author rbrainard
 */
public class AppAddOnApi {

    private final Connection<?> connection;
    private final String appName;
    private final String name;

    public AppAddOnApi(Connection<?> connection, String appName, String name) {
        this.connection = connection;
        this.appName = appName;
        this.name = name;
    }


}
