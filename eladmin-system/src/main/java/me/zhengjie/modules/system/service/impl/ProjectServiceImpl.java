/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.Project;
import me.zhengjie.modules.system.repository.ProjectRepository;
import me.zhengjie.modules.system.service.dto.ProjectDto;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.service.ProjectService;
import me.zhengjie.modules.system.service.dto.ProjectQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.ProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author neptu
* @date 2020-10-31
**/
@Service("projectService")
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public Map<String,Object> queryAll(ProjectQueryCriteria criteria, Pageable pageable){
        Page<Project> page = projectRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectMapper::toDto));
    }

    @Override
    public List<ProjectDto> queryAll(ProjectQueryCriteria criteria){
//        List<Project> projects = projectRepository.findAll();
        List<Project> projects = projectRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder));
        return projectMapper.toDto(projects);
    }

    @Override
    @Transactional
    public ProjectDto findById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseGet(Project::new);
        ValidationUtil.isNull(project.getId(),"Project","projectId",projectId);
        return projectMapper.toDto(project);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectDto create(Project resources) {
        return projectMapper.toDto(projectRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Project resources) {
        Project project = projectRepository.findById(resources.getId()).orElseGet(Project::new);
        ValidationUtil.isNull( project.getId(),"Project","id",resources.getId());
        project.copy(resources);
        projectRepository.save(project);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long projectId : ids) {
            projectRepository.deleteById(projectId);
        }
    }

    @Override
    public void download(List<ProjectDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectDto project : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" name",  project.getName());
            map.put(" description",  project.getDescription());
            map.put(" startTime",  project.getStartTime());
            map.put(" endTime",  project.getEndTime());
            map.put(" leader",  project.getLeader());
            map.put("创建者", project.getCreateBy());
            map.put("更新着", project.getUpdatedBy());
            map.put("创建日期", project.getCreateTime());
            map.put("更新时间", project.getUpdateTime());
            map.put(" status",  project.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}