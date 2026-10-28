package org.example.interfaces;

public interface Manageable {
    String getRegistrationNumber();

    String getDestination();

    void setDestination(String destination);

    String getOwner();

    void setOwner(String owner);

    double getTareWeight();
}
