package com.stan.stancommons.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A SmsTemplate.
 */
@Entity
@Table(name = "sms_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SmsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "template_message")
    private String templateMessage;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "sms_notification_status")
    private Boolean smsNotificationStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SmsTemplate id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateMessage() {
        return this.templateMessage;
    }

    public SmsTemplate templateMessage(String templateMessage) {
        this.setTemplateMessage(templateMessage);
        return this;
    }

    public void setTemplateMessage(String templateMessage) {
        this.templateMessage = templateMessage;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public SmsTemplate templateName(String templateName) {
        this.setTemplateName(templateName);
        return this;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Boolean getSmsNotificationStatus() {
        return this.smsNotificationStatus;
    }

    public SmsTemplate smsNotificationStatus(Boolean smsNotificationStatus) {
        this.setSmsNotificationStatus(smsNotificationStatus);
        return this;
    }

    public void setSmsNotificationStatus(Boolean smsNotificationStatus) {
        this.smsNotificationStatus = smsNotificationStatus;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SmsTemplate)) {
            return false;
        }
        return id != null && id.equals(((SmsTemplate) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SmsTemplate{" +
            "id=" + getId() +
            ", templateMessage='" + getTemplateMessage() + "'" +
            ", templateName='" + getTemplateName() + "'" +
            ", smsNotificationStatus='" + getSmsNotificationStatus() + "'" +
            "}";
    }
}
