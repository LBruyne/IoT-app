package com.hinsliu.iotapp.domain.view;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Description: 柱状图DTO
 * @author: liuxuanming
 * @date: 2021/05/25 11:04 下午
 */
@Data
public class BarGraphDTO {

    private List<String> timeline;

    private List<Map<String, List<Integer>>> data;

}
