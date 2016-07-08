package com.self.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@javax.persistence.Table(name = "client_master", schema = "", catalog = "hcc_reportmaster")
public class ClientMasterEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private int clientId;

    @Id
    @javax.persistence.Column(name = "client_id", nullable = false, insertable = true, updatable = true)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    private String clientName;

    @Basic
    @javax.persistence.Column(name = "client_name", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String clientShortName;

    @Basic
    @javax.persistence.Column(name = "client_short_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getClientShortName() {
        return clientShortName;
    }

    public void setClientShortName(String clientShortName) {
        this.clientShortName = clientShortName;
    }

    private String clientAddress1;

    @Basic
    @javax.persistence.Column(name = "client_address_1", nullable = true, insertable = true, updatable = true, length = 50)
    public String getClientAddress1() {
        return clientAddress1;
    }

    public void setClientAddress1(String clientAddress1) {
        this.clientAddress1 = clientAddress1;
    }

    private String clientAddress2;

    @Basic
    @javax.persistence.Column(name = "client_address_2", nullable = true, insertable = true, updatable = true, length = 50)
    public String getClientAddress2() {
        return clientAddress2;
    }

    public void setClientAddress2(String clientAddress2) {
        this.clientAddress2 = clientAddress2;
    }

    private String clientCity;

    @Basic
    @javax.persistence.Column(name = "client_city", nullable = true, insertable = true, updatable = true, length = 50)
    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    private String clientState;

    @Basic
    @javax.persistence.Column(name = "client_state", nullable = true, insertable = true, updatable = true, length = 50)
    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    private String clientZipcode;

    @Basic
    @javax.persistence.Column(name = "client_zipcode", nullable = true, insertable = true, updatable = true, length = 15)
    public String getClientZipcode() {
        return clientZipcode;
    }

    public void setClientZipcode(String clientZipcode) {
        this.clientZipcode = clientZipcode;
    }

    private String clientCountry;

    @Basic
    @javax.persistence.Column(name = "client_country", nullable = true, insertable = true, updatable = true, length = 30)
    public String getClientCountry() {
        return clientCountry;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    private String clientTimezone;

    @Basic
    @javax.persistence.Column(name = "client_timezone", nullable = true, insertable = true, updatable = true, length = 30)
    public String getClientTimezone() {
        return clientTimezone;
    }

    public void setClientTimezone(String clientTimezone) {
        this.clientTimezone = clientTimezone;
    }

    private String clientPrimaryPhoneNumber;

    @Basic
    @javax.persistence.Column(name = "client_primary_phone_number", nullable = true, insertable = true, updatable = true, length = 20)
    public String getClientPrimaryPhoneNumber() {
        return clientPrimaryPhoneNumber;
    }

    public void setClientPrimaryPhoneNumber(String clientPrimaryPhoneNumber) {
        this.clientPrimaryPhoneNumber = clientPrimaryPhoneNumber;
    }

    private String clientSecondaryPhoneNumber;

    @Basic
    @javax.persistence.Column(name = "client_secondary_phone_number", nullable = true, insertable = true, updatable = true, length = 20)
    public String getClientSecondaryPhoneNumber() {
        return clientSecondaryPhoneNumber;
    }

    public void setClientSecondaryPhoneNumber(String clientSecondaryPhoneNumber) {
        this.clientSecondaryPhoneNumber = clientSecondaryPhoneNumber;
    }

    private String clientEmailId;

    @Basic
    @javax.persistence.Column(name = "client_email_id", nullable = true, insertable = true, updatable = true, length = 50)
    public String getClientEmailId() {
        return clientEmailId;
    }

    public void setClientEmailId(String clientEmailId) {
        this.clientEmailId = clientEmailId;
    }

    private String clientAlternateEmailId;

    @Basic
    @javax.persistence.Column(name = "client_alternate_email_id", nullable = true, insertable = true, updatable = true, length = 50)
    public String getClientAlternateEmailId() {
        return clientAlternateEmailId;
    }

    public void setClientAlternateEmailId(String clientAlternateEmailId) {
        this.clientAlternateEmailId = clientAlternateEmailId;
    }

    private String clientFaxNumber;

    @Basic
    @javax.persistence.Column(name = "client_fax_number", nullable = true, insertable = true, updatable = true, length = 20)
    public String getClientFaxNumber() {
        return clientFaxNumber;
    }

    public void setClientFaxNumber(String clientFaxNumber) {
        this.clientFaxNumber = clientFaxNumber;
    }

    private String clientPagerNumber;

    @Basic
    @javax.persistence.Column(name = "client_pager_number", nullable = true, insertable = true, updatable = true, length = 20)
    public String getClientPagerNumber() {
        return clientPagerNumber;
    }

    public void setClientPagerNumber(String clientPagerNumber) {
        this.clientPagerNumber = clientPagerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientMasterEntity that = (ClientMasterEntity) o;

        if (clientId != that.clientId) return false;
        if (clientAddress1 != null ? !clientAddress1.equals(that.clientAddress1) : that.clientAddress1 != null)
            return false;
        if (clientAddress2 != null ? !clientAddress2.equals(that.clientAddress2) : that.clientAddress2 != null)
            return false;
        if (clientAlternateEmailId != null ? !clientAlternateEmailId.equals(that.clientAlternateEmailId) : that.clientAlternateEmailId != null)
            return false;
        if (clientCity != null ? !clientCity.equals(that.clientCity) : that.clientCity != null) return false;
        if (clientCountry != null ? !clientCountry.equals(that.clientCountry) : that.clientCountry != null)
            return false;
        if (clientEmailId != null ? !clientEmailId.equals(that.clientEmailId) : that.clientEmailId != null)
            return false;
        if (clientFaxNumber != null ? !clientFaxNumber.equals(that.clientFaxNumber) : that.clientFaxNumber != null)
            return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (clientPagerNumber != null ? !clientPagerNumber.equals(that.clientPagerNumber) : that.clientPagerNumber != null)
            return false;
        if (clientPrimaryPhoneNumber != null ? !clientPrimaryPhoneNumber.equals(that.clientPrimaryPhoneNumber) : that.clientPrimaryPhoneNumber != null)
            return false;
        if (clientSecondaryPhoneNumber != null ? !clientSecondaryPhoneNumber.equals(that.clientSecondaryPhoneNumber) : that.clientSecondaryPhoneNumber != null)
            return false;
        if (clientShortName != null ? !clientShortName.equals(that.clientShortName) : that.clientShortName != null)
            return false;
        if (clientState != null ? !clientState.equals(that.clientState) : that.clientState != null) return false;
        if (clientTimezone != null ? !clientTimezone.equals(that.clientTimezone) : that.clientTimezone != null)
            return false;
        if (clientZipcode != null ? !clientZipcode.equals(that.clientZipcode) : that.clientZipcode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientId;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (clientShortName != null ? clientShortName.hashCode() : 0);
        result = 31 * result + (clientAddress1 != null ? clientAddress1.hashCode() : 0);
        result = 31 * result + (clientAddress2 != null ? clientAddress2.hashCode() : 0);
        result = 31 * result + (clientCity != null ? clientCity.hashCode() : 0);
        result = 31 * result + (clientState != null ? clientState.hashCode() : 0);
        result = 31 * result + (clientZipcode != null ? clientZipcode.hashCode() : 0);
        result = 31 * result + (clientCountry != null ? clientCountry.hashCode() : 0);
        result = 31 * result + (clientTimezone != null ? clientTimezone.hashCode() : 0);
        result = 31 * result + (clientPrimaryPhoneNumber != null ? clientPrimaryPhoneNumber.hashCode() : 0);
        result = 31 * result + (clientSecondaryPhoneNumber != null ? clientSecondaryPhoneNumber.hashCode() : 0);
        result = 31 * result + (clientEmailId != null ? clientEmailId.hashCode() : 0);
        result = 31 * result + (clientAlternateEmailId != null ? clientAlternateEmailId.hashCode() : 0);
        result = 31 * result + (clientFaxNumber != null ? clientFaxNumber.hashCode() : 0);
        result = 31 * result + (clientPagerNumber != null ? clientPagerNumber.hashCode() : 0);
        return result;
    }
}
