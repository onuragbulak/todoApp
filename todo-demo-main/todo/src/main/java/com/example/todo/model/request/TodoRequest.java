package com.example.todo.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TodoRequest {

    @NotBlank
    private String title;
    @NotNull
    private boolean done;
    @NotNull
    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
