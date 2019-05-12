package com.springcourse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PageModel<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int totalElenents;
    private int pageSize;
    private int totalPage;
    private List<T> elements;

}
