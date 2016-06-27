package com.self.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "user_authentication", schema = "", catalog = "zingat")
public class UserAuthenticationEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String password;
    private String clientName;
    private String emailId;
    private String roleName;
    private Integer activeFlag;
    private Integer deleteFlag;
    private Date passwordExpiryDate;
    private byte passwordResetFlag;
    private byte maxCountLock;
    private short lockExpirationTimeInMin;
    private Timestamp lastLoginAttemptDatetime;
    private short passwordExpireDays;
    private byte failedCount;
    private String userState;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "client_name", nullable = true, insertable = true, updatable = true, length = 250)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Basic
    @Column(name = "email_id", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Basic
    @Column(name = "role_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "active_flag", nullable = true, insertable = true, updatable = true)
    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Basic
    @Column(name = "delete_flag", nullable = true, insertable = true, updatable = true)
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Basic
    @Column(name = "password_expiry_date", nullable = true, insertable = true, updatable = true)
    public Date getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    public void setPasswordExpiryDate(Date passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }

    @Basic
    @Column(name = "password_reset_flag", nullable = false, insertable = true, updatable = true)
    public byte getPasswordResetFlag() {
        return passwordResetFlag;
    }

    public void setPasswordResetFlag(byte passwordResetFlag) {
        this.passwordResetFlag = passwordResetFlag;
    }

    @Basic
    @Column(name = "max_count_lock", nullable = false, insertable = true, updatable = true)
    public byte getMaxCountLock() {
        return maxCountLock;
    }

    public void setMaxCountLock(byte maxCountLock) {
        this.maxCountLock = maxCountLock;
    }

    @Basic
    @Column(name = "lock_expiration_time_in_min", nullable = false, insertable = true, updatable = true)
    public short getLockExpirationTimeInMin() {
        return lockExpirationTimeInMin;
    }

    public void setLockExpirationTimeInMin(short lockExpirationTimeInMin) {
        this.lockExpirationTimeInMin = lockExpirationTimeInMin;
    }

    @Basic
    @Column(name = "last_login_attempt_datetime", nullable = true, insertable = true, updatable = true)
    public Timestamp getLastLoginAttemptDatetime() {
        return lastLoginAttemptDatetime;
    }

    public void setLastLoginAttemptDatetime(Timestamp lastLoginAttemptDatetime) {
        this.lastLoginAttemptDatetime = lastLoginAttemptDatetime;
    }

    @Basic
    @Column(name = "password_expire_days", nullable = false, insertable = true, updatable = true)
    public short getPasswordExpireDays() {
        return passwordExpireDays;
    }

    public void setPasswordExpireDays(short passwordExpireDays) {
        this.passwordExpireDays = passwordExpireDays;
    }

    @Basic
    @Column(name = "failed_count", nullable = false, insertable = true, updatable = true)
    public byte getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(byte failedCount) {
        this.failedCount = failedCount;
    }

    @Basic
    @Column(name = "user_state", nullable = true, insertable = true, updatable = true, length = 25)
    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthenticationEntity that = (UserAuthenticationEntity) o;

        if (failedCount != that.failedCount) return false;
        if (id != that.id) return false;
        if (lockExpirationTimeInMin != that.lockExpirationTimeInMin) return false;
        if (maxCountLock != that.maxCountLock) return false;
        if (passwordExpireDays != that.passwordExpireDays) return false;
        if (passwordResetFlag != that.passwordResetFlag) return false;
        if (activeFlag != null ? !activeFlag.equals(that.activeFlag) : that.activeFlag != null) return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (deleteFlag != null ? !deleteFlag.equals(that.deleteFlag) : that.deleteFlag != null) return false;
        if (emailId != null ? !emailId.equals(that.emailId) : that.emailId != null) return false;
        if (lastLoginAttemptDatetime != null ? !lastLoginAttemptDatetime.equals(that.lastLoginAttemptDatetime) : that.lastLoginAttemptDatetime != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (passwordExpiryDate != null ? !passwordExpiryDate.equals(that.passwordExpiryDate) : that.passwordExpiryDate != null)
            return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (userState != null ? !userState.equals(that.userState) : that.userState != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (emailId != null ? emailId.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (activeFlag != null ? activeFlag.hashCode() : 0);
        result = 31 * result + (deleteFlag != null ? deleteFlag.hashCode() : 0);
        result = 31 * result + (passwordExpiryDate != null ? passwordExpiryDate.hashCode() : 0);
        result = 31 * result + (int) passwordResetFlag;
        result = 31 * result + (int) maxCountLock;
        result = 31 * result + (int) lockExpirationTimeInMin;
        result = 31 * result + (lastLoginAttemptDatetime != null ? lastLoginAttemptDatetime.hashCode() : 0);
        result = 31 * result + (int) passwordExpireDays;
        result = 31 * result + (int) failedCount;
        result = 31 * result + (userState != null ? userState.hashCode() : 0);
        return result;
    }
}
