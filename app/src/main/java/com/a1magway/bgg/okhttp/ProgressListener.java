package com.a1magway.bgg.okhttp;

/**
 * Created on 2017/_8/16 0016.
 */

//Progress callback interface
public interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
