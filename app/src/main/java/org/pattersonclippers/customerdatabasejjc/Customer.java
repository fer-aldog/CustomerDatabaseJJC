package org.pattersonclippers.customerdatabasejjc;

public class Customer {

    String name, phone, email;
    boolean emailSettings;
    int zipcode;

    public Customer() {
        name = "John Doe";
        email = "johndoe@gmail.com";
        phone = "555-0100";
        zipcode = 99999;
        emailSettings = false;
    }

    public Customer(String newName, String newEmail, String newPhone, int newZipcode, boolean wantGetEmails) {
        name = newName;
        email = newEmail;
        phone = newPhone;
        zipcode = newZipcode;
        emailSettings = wantGetEmails;
    }

    //getters
    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    public int getZipcode() { return zipcode; }

    public boolean getEmailSettings() { return emailSettings; }



    //setters
    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setZipcode(int zipcode) { this.zipcode = zipcode; }

    public void setEmailSettings(boolean getEmails) { emailSettings = getEmails; }
}
