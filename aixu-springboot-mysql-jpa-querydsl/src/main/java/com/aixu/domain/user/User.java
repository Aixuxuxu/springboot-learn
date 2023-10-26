package com.aixu.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "user")
@DynamicInsert
public class User {

    @Id
    private long id;

    private String user;

    private String account;

    @Version
    @Column(columnDefinition = " INT DEFAULT 0 COMMENT '版本号' ",nullable = false)
    private Integer version;
}
