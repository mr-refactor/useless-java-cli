package com.CLIPractice;

import java.util.UUID;

public class BasicObject {
// Class Variables

// Class Methods

// Instance Variables
    private String id;
// Constructors

// Readers

// Instance Methods

    public static String generateID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public boolean isNotNull() {
        return this.id != "0";
    }
}

/*
// Class Variables

// Class Methods

// Instance Variables

// Constructors

// Readers

// Instance Methods
 */
