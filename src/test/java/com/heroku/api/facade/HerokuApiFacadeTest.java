package com.heroku.api.facade;

import com.heroku.api.connection.HttpClientConnection;
import com.heroku.api.request.login.BasicAuthLogin;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Not really tests, but just examples of using the facade
 *
 * @author rbrainard
 */
public class HerokuApiFacadeTest {

    private Api api;

    @Before
    public void setUp() {
        final String username = System.getProperty("heroku.username");
        final String password = System.getProperty("heroku.password");
        final HttpClientConnection connection = new HttpClientConnection(new BasicAuthLogin(username, password));
        api = Api.connect(connection);
    }

    @Test
    public void testAppLifecycle() throws Exception {
        final AppApi myNewApp = api.apps().create("my-super-cool-app-2");
        myNewApp.addOns().add("memcache:5mb");
        myNewApp.addOns().list();
        myNewApp.destroy();
    }

    @Test
    public void testAppAttachAndDestroy() throws Exception {
        final Map<String,AppApi> allMyApps = api.apps().list();
        if (allMyApps.containsKey("does-it-exist")) {
            allMyApps.get("does-it-exist").destroy();
        }
    }
}
