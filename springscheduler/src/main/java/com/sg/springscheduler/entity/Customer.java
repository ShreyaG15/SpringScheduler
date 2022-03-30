package com.sg.springscheduler.entity;

import com.sg.springscheduler.enumerator.Language;
import com.sg.springscheduler.enumerator.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.IDENTITY)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(length = 30)
    private String userName;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 10)
    private String phoneNumber;

    @Column(length = 50)
    private String email;

    @Column(length = 3)
    private Integer age;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(length = 2)
    @Enumerated(EnumType.STRING)
    private Language preferredLanguage;

    @Column(length = 50)
    private String externalId;

    @Column(length = 50)
    private String createdBy;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(length = 50)
    private String updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedOn;


    public Customer(String id,String userName, String firstName, String lastName, String phoneNumber, String email, Integer age, Status status, Language preferredLanguage) {
        this.id=id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
        this.status = status;
        this.preferredLanguage = preferredLanguage;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", status=" + status +
                ", preferredLanguage=" + preferredLanguage +
                ", externalId='" + externalId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
