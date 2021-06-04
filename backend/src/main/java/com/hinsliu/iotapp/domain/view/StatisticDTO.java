package com.hinsliu.iotapp.domain.view;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Description: 统计数据视图
 * @author: liuxuanming
 * @date: 2021/05/25 10:59 下午
 */
@Data
public class StatisticDTO {

    private List<Integer> typeData;

    private BarGraphDTO barData;

    private LineGraphDTO lineData;

    private PieGraphDTO pieData;

}
