package com.ml.sell.form;

import lombok.Data;

/**
 * @author
 * @date 2018/11/8
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

}
