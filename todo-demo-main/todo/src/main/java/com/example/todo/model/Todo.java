package com.example.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "todo")
@SequenceGenerator(name = "todo_sequence", sequenceName = "todo_sequence", allocationSize = 1)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_sequence")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "done", nullable = false)
    private boolean done;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Column(name = "delete", nullable = false)
    private boolean deleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public static final class TodoBuilder {
        private Long id;
        private String title;
        private boolean done;
        private AppUser appUser;
        private boolean deleted = false;

        private TodoBuilder() {
        }

        public static TodoBuilder aTodo() {
            return new TodoBuilder();
        }

        public TodoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TodoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TodoBuilder done(boolean done) {
            this.done = done;
            return this;
        }

        public TodoBuilder appUser(AppUser appUser) {
            this.appUser = appUser;
            return this;
        }

        public TodoBuilder deleted(boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Todo build() {
            Todo todo = new Todo();
            todo.setId(id);
            todo.setTitle(title);
            todo.setDone(done);
            todo.setAppUser(appUser);
            todo.setDeleted(deleted);
            return todo;
        }
    }
}
