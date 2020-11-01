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
package me.zhengjie.modules.system.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.system.domain.Project;
import me.zhengjie.modules.system.service.ProjectService;
import me.zhengjie.modules.system.service.dto.ProjectQueryCriteria;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author neptu
* @date 2020-10-31
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "Project管理")
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('project:list')")
    public void download(HttpServletResponse response, ProjectQueryCriteria criteria) throws IOException {
        projectService.download(projectService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询Project")
    @ApiOperation("查询Project")
    @PreAuthorize("@el.check('project:list')")
    public ResponseEntity<Object> query(ProjectQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增Project")
    @ApiOperation("新增Project")
    @PreAuthorize("@el.check('project:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Project resources){
        return new ResponseEntity<>(projectService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改Project")
    @ApiOperation("修改Project")
    @PreAuthorize("@el.check('project:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Project resources){
        projectService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除Project")
    @ApiOperation("删除Project")
    @PreAuthorize("@el.check('project:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        projectService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}