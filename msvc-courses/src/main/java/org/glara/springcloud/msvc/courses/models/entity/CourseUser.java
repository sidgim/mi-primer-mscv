package org.glara.springcloud.msvc.courses.models.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "courses_users")
public class CourseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_id", unique = true)
    private UUID userId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof CourseUser)) {
            return false;
        }

        CourseUser courseUser = (CourseUser) obj;
        return this.userId != null  && this.userId.equals(courseUser.userId);
    }
}
