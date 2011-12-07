package com.heroku.api.facade;

import com.heroku.api.Heroku;
import com.heroku.api.connection.Connection;
import com.heroku.api.model.App;
import com.heroku.api.request.app.AppCreate;
import com.heroku.api.request.app.AppList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rbrainard
 */
public class AppsApi {

    private final Connection<?> connection;

    public AppsApi(Connection<?> connection) {
        this.connection = connection;
    }

    public AppApi create(String appName) {
        return create(Heroku.Stack.Cedar, appName);
    }

    public AppApi create(Heroku.Stack stack, String appName) {
        connection.execute(new AppCreate(stack).withName(appName));
        return new AppApi(connection, appName);
    }

    public Map<String, AppApi> list() {
        final List<App> appList = connection.execute(new AppList());
        Map<String, AppApi> addOnApiMap = new HashMap<String, AppApi>(appList.size());
        for (App app : appList) {
            addOnApiMap.put(app.getName(), new AppApi(connection, app.getName()));
        }
        return Collections.unmodifiableMap(addOnApiMap);
    }
}
