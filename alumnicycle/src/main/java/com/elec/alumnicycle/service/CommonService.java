package com.elec.alumnicycle.service;


import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Vo.GlobalSearchVo;

import java.util.List;

public interface CommonService {

    AjaxRes<List<GlobalSearchVo>> search(String keyword);

}
