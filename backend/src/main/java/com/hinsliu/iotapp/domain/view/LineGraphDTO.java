package com.hinsliu.iotapp.domain.view;

import lombok.Data;

import java.util.List;

/**
 * @Description: 折线图的DTO
 * @author: liuxuanming
 * @date: 2021/05/25 11:02 下午
 */
@Data
public class LineGraphDTO {

    private List<String> timeline;

    private List<Integer> offlineNum;

    private List<Integer> onlineNum;

    private List<Integer> totalNum;

}
