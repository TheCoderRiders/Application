package com.self.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "event_log", schema = "", catalog = "zingat")
public class EventLogEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String actionType;
    private Timestamp executionDate;
    private Timestamp completionDate;
    private String sessionId;
    private String ipAddress;
    private int isActive;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "action_type", nullable = true, insertable = true, updatable = true, length = 50)
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Basic
    @Column(name = "execution_date", nullable = false, insertable = true, updatable = true)
    public Timestamp getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Timestamp executionDate) {
        this.executionDate = executionDate;
    }

    @Basic
    @Column(name = "completion_date", nullable = true, insertable = true, updatable = true)
    public Timestamp getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Timestamp completionDate) {
        this.completionDate = completionDate;
    }

    @Basic
    @Column(name = "session_id", nullable = false, insertable = true, updatable = true, length = 1000)
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "ip_address", nullable = false, insertable = true, updatable = true, length = 15)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Basic
    @Column(name = "is_active", nullable = false, insertable = true, updatable = true)
    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventLogEntity that = (EventLogEntity) o;

        if (id != that.id) return false;
        if (isActive != that.isActive) return false;
        if (actionType != null ? !actionType.equals(that.actionType) : that.actionType != null) return false;
        if (completionDate != null ? !completionDate.equals(that.completionDate) : that.completionDate != null)
            return false;
        if (executionDate != null ? !executionDate.equals(that.executionDate) : that.executionDate != null)
            return false;
        if (ipAddress != null ? !ipAddress.equals(that.ipAddress) : that.ipAddress != null) return false;
        if (sessionId != null ? !sessionId.equals(that.sessionId) : that.sessionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        result = 31 * result + (executionDate != null ? executionDate.hashCode() : 0);
        result = 31 * result + (completionDate != null ? completionDate.hashCode() : 0);
        result = 31 * result + (sessionId != null ? sessionId.hashCode() : 0);
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + isActive;
        return result;
    }
}
