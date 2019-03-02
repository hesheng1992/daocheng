package com.a1magway.bgg.okhttp;


import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created on 2017/_8/16 0016.
 */

class ProgressRequestBody extends RequestBody {
    //The actual package request body
    private final RequestBody requestBody;
    //Progress callback interface
    private final ProgressListener progressListener;
    //packaged BufferedSink
    private BufferedSink bufferedSink;

    /**
     * Constructor, assignment
     *
     * @param requestBody      The body to be wrapped
     * @param progressListener Callback interface
     */
    ProgressRequestBody(RequestBody requestBody, ProgressListener progressListener) {
        this.requestBody = requestBody;
        this.progressListener = progressListener;
    }

    /**
     * Rewrite the call to the actual response body contentType
     *
     * @return MediaType
     */
    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    /**
     * Rewrite the call to the actual response body contentLength
     *
     * @return contentLength
     */
    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    /**
     * overwrite
     *
     * @param sink BufferedSink
     */
    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            //wrap
            bufferedSink = Okio.buffer(sink(sink));
        }
        //write
        requestBody.writeTo(bufferedSink);
        //You must call flush，Otherwise, the last part of the data might not be written
        bufferedSink.flush();

    }

    /**
     * Write to the callback progress interface
     *
     * @param sink Sink
     * @return Sink
     */
    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            //Currently writes the number of bytes
            long bytesWritten = 0L;
            //Total byte length，Avoid multiple invocation methods contentLength()
            long contentLength = 0L;

            @Override
            public void write(@NonNull Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    //Get the value of the contentLength and no longer call
                    contentLength = contentLength();
                }
                //Increases the number of bytes currently written
                bytesWritten += byteCount;
                //The callback
                progressListener.update(bytesWritten, contentLength, bytesWritten == contentLength);
            }
        };
    }
}
