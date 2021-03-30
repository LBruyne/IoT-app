package com.hinsliu.iotapp.web.controller;

import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.domain.view.ProjectItemDTO;
import com.hinsliu.iotapp.biz.project.ProjectItemManager;
import com.hinsliu.iotapp.web.annotation.AuthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: Controller for project items.
 * @author: liuxuanming
 * @date: 2021/03/26 11:23 上午
 */
@RestController
@RequestMapping(value = "/app/project")
public class ProjectItemController {

    private static Logger logger = LoggerFactory.getLogger(ProjectItemController.class);

    @Resource
    private ProjectItemManager projectItemManager;

    @AuthToken
    @RequestMapping(value = "/detail", method = {RequestMethod.GET})
    public RpcResult<ProjectItemDTO> queryDetail(@RequestParam(value = "id", required = false) Integer id,
                                                 @RequestParam(value = "itemId", required = true) Integer itemId) {
        return projectItemManager.searchItemInfo(itemId);
    }
}
