package com.heroku.api.facade;

import com.heroku.api.connection.Connection;
import com.heroku.api.request.app.AppDestroy;
import com.heroku.api.request.app.AppInfo;

/**
 * @author rbrainard
 */
public class AppApi {

    private final Connection<?> connection;
    private final String appName;

    public AppApi(Connection<?> connection, String appName) {
        this.connection = connection;
        this.appName = appName;
    }

    public void destroy() {
        connection.execute(new AppDestroy(appName));
    }

    public com.heroku.api.model.App info() {
        return connection.execute(new AppInfo(appName));
    }

    public AppAddOnsApi addOns() {
        return new AppAddOnsApi(connection, appName);
    }
}
