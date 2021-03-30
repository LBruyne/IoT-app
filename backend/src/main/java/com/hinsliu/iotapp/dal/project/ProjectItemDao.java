package com.hinsliu.iotapp.dal.project;

import com.hinsliu.iotapp.domain.model.project.ProjectItemDO;
import com.hinsliu.iotapp.domain.query.ProjectItemQuery;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description: DAO for project item info.
 * @author: liuxuanming
 * @date: 2021/03/26 11:45 上午
 */
@Repository
public interface ProjectItemDao {

    /**
     * @description: 主键查询
     * @author: liuxuanming
     * @date: 2021/3/26 4:27 下午
     * @params: [id]
     * @return: ProjectItemDO
     */
    ProjectItemDO selectByPrimaryKey(Integer id);
}
