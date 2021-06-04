package com.hinsliu.iotapp.domain.view;

import lombok.Data;

import java.util.List;

/**
 * @Description: 饼图DTO
 * @author: liuxuanming
 * @date: 2021/05/25 11:03 下午
 */
@Data
public class PieGraphDTO {

    private List<String> names;

    private List<Integer> numbers;

}
