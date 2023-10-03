package com.elec.alumnicycle.common;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 简单分页模型
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "分页数据")
public class Page<T> implements IPage<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 查询数据列表
     */
    @ApiModelProperty(value = "数据列表")
    private List<T> records = Collections.emptyList();
    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "每页显示条数", required = true)
    private long size = 10;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private long current = 1;
    /**
     * SQL 排序 ASC 数组
     */
    @ApiModelProperty(value = "SQL 排序 ASC 数组")
    private String[] ascs;
    /**
     * SQL 排序 DESC 数组
     */
    @ApiModelProperty(value = "SQL 排序 DESC 数组")
    private String[] descs;
    /**
     * 自动优化 COUNT SQL
     */
    private boolean optimizeCountSql = true;
    /**
     * 是否进行 count 查询
     */
    private boolean isSearchCount = true;

    /**
     * 字段合计集合(针对不同表字段的数值合计)
     */
    private Map<String, Object> addUpMap = Collections.emptyMap();

    public Page() {
        // to do nothing
    }

    /**
     * 分页构造函数
     *
     * @param current 当前页
     * @param size    每页显示条数
     */
    public Page(long current, long size) {
        this(current, size, 0);
    }

    public Page(long current, long size, long total) {
        this(current, size, total, true);
    }

    public Page(long current, long size, boolean isSearchCount) {
        this(current, size, 0, isSearchCount);
    }

    public Page(long current, long size, long total, boolean isSearchCount) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
        this.isSearchCount = isSearchCount;
    }

    /**
     * 是否存在上一页
     *
     * @return true / false
     */
    public boolean hasPrevious() {
        return this.current > 1;
    }

    /**
     * 是否存在下一页
     *
     * @return true / false
     */
    public boolean hasNext() {
        return this.current < this.getPages();
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public Page<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public Page<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public Page<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public Page<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

//    @Override
//    public String[] ascs() {
//        return ascs;
//    }
//
//    public Page<T> setAscs(List<String> ascs) {
//        if (CollectionUtils.isNotEmpty(ascs)) {
//            this.ascs = ascs.toArray(new String[0]);
//        }
//        return this;
//    }


    /**
     * 升序
     *
     * @param ascs 多个升序字段
     */
    public Page<T> setAsc(String... ascs) {
        this.ascs = ascs;
        return this;
    }

//    @Override
//    public String[] descs() {
//        return descs;
//    }
//
//    public Page<T> setDescs(List<String> descs) {
//        if (CollectionUtils.isNotEmpty(descs)) {
//            this.descs = descs.toArray(new String[0]);
//        }
//        return this;
//    }

    /**
     * 降序
     *
     * @param descs 多个降序字段
     */
    public Page<T> setDesc(String... descs) {
        this.descs = descs;
        return this;
    }

    @Override
    public List<OrderItem> orders() {
        return null;
    }

    @Override
    public boolean optimizeCountSql() {
        return optimizeCountSql;
    }

    @Override
    public boolean isSearchCount() {
        if (total < 0) {
            return false;
        }
        return isSearchCount;
    }

    public Page<T> setSearchCount(boolean isSearchCount) {
        this.isSearchCount = isSearchCount;
        return this;
    }

    public Page<T> setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }
}
