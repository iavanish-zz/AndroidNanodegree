package com.example.iavanish.popularmovies.util;

import java.io.IOException;

/**
 * Created by iavanish on 16/9/16.
 */
public class NetworkConnection {

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

}
