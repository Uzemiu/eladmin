package me.zhengjie.modules.system.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "sys_project")
public class Project extends BaseEntity implements Serializable {

    @Id
    @Column(name = "project_id")
    @NotNull(groups = {Update.class})
    @ApiModelProperty(value = "ID", hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Timestamp startTime;

    @NotNull
    private Timestamp endTime;

    @Column(columnDefinition = "tinyint not null")
    private Integer status;

    @OneToOne
    @JoinColumn(name = "leader")
    private User leader;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_project_users",
            joinColumns = {@JoinColumn(name = "project_id",referencedColumnName = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")})
    private Set<User> member;

    public void copy(Project source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
