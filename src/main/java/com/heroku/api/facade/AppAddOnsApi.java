package com.heroku.api.facade;

import com.heroku.api.connection.Connection;
import com.heroku.api.model.Addon;
import com.heroku.api.model.AddonChange;
import com.heroku.api.request.addon.AddonInstall;
import com.heroku.api.request.addon.AppAddonsList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rbrainard
 */
public class AppAddOnsApi {

    private final Connection<?> connection;
    private final String appName;

    public AppAddOnsApi(Connection<?> connection, String appName) {
        this.connection = connection;
        this.appName = appName;
    }

    public Map<String, AppAddOnApi> list() {
        final List<Addon> addonList = connection.execute(new AppAddonsList(appName));
        Map<String, AppAddOnApi> addOnApiMap = new HashMap<String, AppAddOnApi>(addonList.size());
        for (Addon addon : addonList) {
            addOnApiMap.put(addon.getName(), new AppAddOnApi(connection, appName, addon.getName()));
        }
        return Collections.unmodifiableMap(addOnApiMap);
    }

    public AddonChange add(String addOnName) {
        return connection.execute(new AddonInstall(appName, addOnName));
    }
}
