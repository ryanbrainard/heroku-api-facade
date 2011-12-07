package com.heroku.api.facade;

import com.heroku.api.connection.Connection;

/**
 * @author rbrainard
 */
public class Api {

    private final Connection<?> connection;

    public static Api connect(Connection<?> connection) {
        return new Api(connection);
    }

    private Api(Connection<?> connection) {
        this.connection = connection;
    }

    public AppsApi apps() {
        return new AppsApi(connection);
    }
}
