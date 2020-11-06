package me.zhengjie;

import me.zhengjie.AppRun;
import me.zhengjie.modules.system.domain.Project;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.service.ProjectService;
import me.zhengjie.modules.system.service.dto.ProjectDto;
import me.zhengjie.modules.system.service.dto.ProjectQueryCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DownloadTest {

    @Resource
    ProjectService projectService;

    @Test
    public void test(){
        ProjectQueryCriteria criteria = new ProjectQueryCriteria();
        List<ProjectDto> projectList = projectService.queryAll(criteria);
        for(ProjectDto projectDto : projectList){
//            User u = projectDto.getLeader();
            int i;
        }
        System.out.println(projectList);
    }
}
