package beans;

/**
 * @author Super tortas
 */
public class Publisher {
    private String pName, pAddress, pPhone, pEmail;
    /**
     * Returns the publisher's name
     * @return String, name of publisher
     */
    public String getpName() {
        return pName;
    }
    /**
     * Returns the address of the publisher
     * @return String, address of the publisher
     */
    public String getpAddress() {
        return pAddress;
    }
    /**
     * Returns the phone number associated with the publisher
     * @return String, the phone number of the publisher
     */
    public String getpPhone() {
        return pPhone;
    }
    /**
     * Returns the email that belongs to the publisher
     * @return String, email of the publisher
     */
    public String getpEmail() {
        return pEmail;
    }
    /**
     * Sets the name of the publisher
     * @param pName, String of the publisher's name
     */
    public void setpName(String pName) {
        this.pName = pName;
    }
    /**
     * Sets the address of the publisher
     * @param pAddress, String of the publisher's address
     */
    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }
    /**
     * Sets the phone number of the publisher
     * @param pPhone, String of the phone number
     */
    public void setpPhone(String pPhone) {
        this.pPhone = pPhone;
    }
    /**
     * Sets the email of the publisher
     * @param pEmail, String of the email
     */
    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }
}