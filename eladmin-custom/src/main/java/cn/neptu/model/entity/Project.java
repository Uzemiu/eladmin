package cn.neptu.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import me.zhengjie.base.BaseEntity;
//import me.zhengjie.modules.system.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "custom_project")
public class Project extends BaseEntity implements Serializable {

    @Id
    @Column(name = "project_id")
    @NotNull(groups = {Update.class})
    @ApiModelProperty(value = "ID", hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Timestamp startTime;

    private Timestamp endTime;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User leader;
//
//    @OneToMany
//    @JoinColumn(name = "user_id")
//    private Set<User> member;
}
