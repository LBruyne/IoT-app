package com.hinsliu.iotapp.biz.project;

import com.hinsliu.iotapp.dal.project.ProjectItemDao;
import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.domain.model.project.ProjectItemDO;
import com.hinsliu.iotapp.domain.view.ProjectItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: liuxuanming
 * @date: 2021/03/22 17:12
 */

@Service
public class ProjectItemManager {

    private static final Logger logger = LoggerFactory.getLogger(ProjectItemManager.class);

    private static final Integer FIRST_ITEM_INDEX = 0;

    @Resource
    private ProjectItemDao projectItemDao;

    public RpcResult<ProjectItemDTO> searchItemInfo(Integer itemId) {
        RpcResult<ProjectItemDTO> itemInfoRpcResult = RpcResult.errorResult("No such item exists.");
        try {
            ProjectItemDTO dtoItem = new ProjectItemDTO();
            ProjectItemDO doItem = projectItemDao.selectByPrimaryKey(itemId);

            if(doItem != null) {
                BeanUtils.copyProperties(doItem, dtoItem);
                itemInfoRpcResult.setSuccess(true, dtoItem);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return itemInfoRpcResult;
    }
}
