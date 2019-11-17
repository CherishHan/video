package com.brayden.video.common;

public class VideoException extends RuntimeException {

    public VideoException() {
    }

    public VideoException(String message) {
        super(message);
    }

    public VideoException(Throwable throwable) {
        super(throwable);
    }

    public VideoException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
