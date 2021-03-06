package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>, JpaSpecificationExecutor<Project> {
}
