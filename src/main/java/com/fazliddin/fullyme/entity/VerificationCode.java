package com.fazliddin.fullyme.entity;

import com.fazliddin.fullyme.entity.templete.AbstractUUID;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "verification_code")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "deleted=false")
@SQLDelete(sql = "update verification_code set deleted = false where id = ?")
public class VerificationCode extends AbstractUUID {
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "code", nullable = false)
    private String code;

    private boolean used;

    private Timestamp expireTime = new Timestamp(System.currentTimeMillis() + ((1000 * 60) * 3));

    public VerificationCode(String phoneNumber, String code) {
        this.phoneNumber = phoneNumber;
        this.code = code;
    }
}
