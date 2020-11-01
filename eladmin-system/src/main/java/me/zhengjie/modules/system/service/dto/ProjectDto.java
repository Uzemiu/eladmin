package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseDTO;
import me.zhengjie.modules.system.domain.User;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
public class ProjectDto extends BaseDTO {

    private Long id;

    private String name;

    private String description;

    private Timestamp startTime;

    private Timestamp endTime;

    private User leader;

    private Set<User> member;

    private Integer status;
}
