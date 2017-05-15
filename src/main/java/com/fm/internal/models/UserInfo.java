package com.fm.internal.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Lob
    @Column(columnDefinition = "mediumlob")
    private byte[] image;

    @ManyToOne
    @JoinColumn(nullable = false, name = "currency_character_code", foreignKey = @ForeignKey(name = "fk_currency_character_code"))
    private Currency currency;

    public UserInfo() {
    }

    public UserInfo(String firstName, String lastName, Currency currency, byte[] image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currency = currency;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
