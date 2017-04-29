package com.example.data.net.facebook.dtos;

public class FacebookPaging {
    private FacebookCursor cursors;

    public FacebookPaging(FacebookCursor cursors) {
        this.cursors = cursors;
    }

    public FacebookCursor getCursors() {
        return cursors;
    }
}
