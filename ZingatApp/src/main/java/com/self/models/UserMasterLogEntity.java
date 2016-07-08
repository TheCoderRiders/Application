package com.self.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@javax.persistence.Table(name = "user_master_log", schema = "", catalog = "hcc_reportmaster")
public class UserMasterLogEntity implements Serializable {
    private int userId;

    @Id
    @javax.persistence.Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String username;

    @Basic
    @javax.persistence.Column(name = "username", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String firstName;

    @Basic
    @javax.persistence.Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 65)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String middleName;

    @Basic
    @javax.persistence.Column(name = "middle_name", nullable = true, insertable = true, updatable = true, length = 65)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    private String lastName;

    @Basic
    @javax.persistence.Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 65)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String gender;

    @Basic
    @javax.persistence.Column(name = "gender", nullable = true, insertable = true, updatable = true, length = 10)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private Date dateOfBirth;

    @Basic
    @javax.persistence.Column(name = "date_of_birth", nullable = true, insertable = true, updatable = true)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private String credential;

    @Basic
    @javax.persistence.Column(name = "credential", nullable = true, insertable = true, updatable = true, length = 10)
    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    private String emailId;

    @Basic
    @javax.persistence.Column(name = "email_id", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    private String alternateEmailId;

    @Basic
    @javax.persistence.Column(name = "alternate_email_id", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAlternateEmailId() {
        return alternateEmailId;
    }

    public void setAlternateEmailId(String alternateEmailId) {
        this.alternateEmailId = alternateEmailId;
    }

    private String address1;

    @Basic
    @javax.persistence.Column(name = "address_1", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    private String address2;

    @Basic
    @javax.persistence.Column(name = "address_2", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    private String city;

    @Basic
    @javax.persistence.Column(name = "city", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String state;

    @Basic
    @javax.persistence.Column(name = "state", nullable = true, insertable = true, updatable = true, length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String zipcode;

    @Basic
    @javax.persistence.Column(name = "zipcode", nullable = true, insertable = true, updatable = true, length = 15)
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    private String country;

    @Basic
    @javax.persistence.Column(name = "country", nullable = true, insertable = true, updatable = true, length = 30)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String timezone;

    @Basic
    @javax.persistence.Column(name = "timezone", nullable = true, insertable = true, updatable = true, length = 30)
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    private String primaryPhoneNumber;

    @Basic
    @javax.persistence.Column(name = "primary_phone_number", nullable = true, insertable = true, updatable = true, length = 20)
    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    private String secondaryPhoneNumber;

    @Basic
    @javax.persistence.Column(name = "secondary_phone_number", nullable = true, insertable = true, updatable = true, length = 20)
    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    private String faxNumber;

    @Basic
    @javax.persistence.Column(name = "fax_number", nullable = true, insertable = true, updatable = true, length = 20)
    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    private String pagerNumber;

    @Basic
    @javax.persistence.Column(name = "pager_number", nullable = true, insertable = true, updatable = true, length = 20)
    public String getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber(String pagerNumber) {
        this.pagerNumber = pagerNumber;
    }

    private String clientName;

    @Basic
    @javax.persistence.Column(name = "client_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String roleName;

    @Basic
    @javax.persistence.Column(name = "role_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private Integer activeFlag;

    @Basic
    @javax.persistence.Column(name = "active_flag", nullable = true, insertable = true, updatable = true)
    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    private Integer deleteFlag;

    @Basic
    @javax.persistence.Column(name = "delete_flag", nullable = true, insertable = true, updatable = true)
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    private Timestamp updatedDatetime;

    @Id
    @javax.persistence.Column(name = "updated_datetime", nullable = false, insertable = true, updatable = true)
    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    private String userLogDescription;

    @Basic
    @javax.persistence.Column(name = "user_log_description", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getUserLogDescription() {
        return userLogDescription;
    }

    public void setUserLogDescription(String userLogDescription) {
        this.userLogDescription = userLogDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMasterLogEntity that = (UserMasterLogEntity) o;

        if (userId != that.userId) return false;
        if (activeFlag != null ? !activeFlag.equals(that.activeFlag) : that.activeFlag != null) return false;
        if (address1 != null ? !address1.equals(that.address1) : that.address1 != null) return false;
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
        if (alternateEmailId != null ? !alternateEmailId.equals(that.alternateEmailId) : that.alternateEmailId != null)
            return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (credential != null ? !credential.equals(that.credential) : that.credential != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (deleteFlag != null ? !deleteFlag.equals(that.deleteFlag) : that.deleteFlag != null) return false;
        if (emailId != null ? !emailId.equals(that.emailId) : that.emailId != null) return false;
        if (faxNumber != null ? !faxNumber.equals(that.faxNumber) : that.faxNumber != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (pagerNumber != null ? !pagerNumber.equals(that.pagerNumber) : that.pagerNumber != null) return false;
        if (primaryPhoneNumber != null ? !primaryPhoneNumber.equals(that.primaryPhoneNumber) : that.primaryPhoneNumber != null)
            return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (secondaryPhoneNumber != null ? !secondaryPhoneNumber.equals(that.secondaryPhoneNumber) : that.secondaryPhoneNumber != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (timezone != null ? !timezone.equals(that.timezone) : that.timezone != null) return false;
        if (updatedDatetime != null ? !updatedDatetime.equals(that.updatedDatetime) : that.updatedDatetime != null)
            return false;
        if (userLogDescription != null ? !userLogDescription.equals(that.userLogDescription) : that.userLogDescription != null)
            return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (zipcode != null ? !zipcode.equals(that.zipcode) : that.zipcode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (credential != null ? credential.hashCode() : 0);
        result = 31 * result + (emailId != null ? emailId.hashCode() : 0);
        result = 31 * result + (alternateEmailId != null ? alternateEmailId.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        result = 31 * result + (primaryPhoneNumber != null ? primaryPhoneNumber.hashCode() : 0);
        result = 31 * result + (secondaryPhoneNumber != null ? secondaryPhoneNumber.hashCode() : 0);
        result = 31 * result + (faxNumber != null ? faxNumber.hashCode() : 0);
        result = 31 * result + (pagerNumber != null ? pagerNumber.hashCode() : 0);
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (activeFlag != null ? activeFlag.hashCode() : 0);
        result = 31 * result + (deleteFlag != null ? deleteFlag.hashCode() : 0);
        result = 31 * result + (updatedDatetime != null ? updatedDatetime.hashCode() : 0);
        result = 31 * result + (userLogDescription != null ? userLogDescription.hashCode() : 0);
        return result;
    }
}
