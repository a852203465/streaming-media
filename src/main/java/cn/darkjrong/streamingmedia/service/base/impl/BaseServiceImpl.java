package cn.darkjrong.streamingmedia.service.base.impl;

import cn.darkjrong.streamingmedia.service.base.BaseService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 业务层 （父级）接口 实现类
 * @author Rong.Jia
 * @date 2020/05/21 18:59
 */
@Slf4j
public class BaseServiceImpl<S, T> implements BaseService<S, T> {

    @Override
    public T objectConversion(S s) {

        Class<T> tClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];

        return BeanUtil.copyProperties(s, tClass);
    }

    @Override
    public List<T> objectConversion(List<S> sList) {

        if (CollectionUtil.isNotEmpty(sList)) {

            List<T> tList = new ArrayList<>();
            for (S s : sList) {
                T t = this.objectConversion(s);
                Optional.ofNullable(t).ifPresent(tList::add);
            }

            return tList;
        }

        return null;
    }

    @Override
    public T objectConversion(S s, String... ignoreProperties) {

        try {

            T t = ((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[1]).newInstance();
            BeanUtil.copyProperties(s, t, ignoreProperties);
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            log.error(ExceptionUtil.getMessage(e));
        }
        return null;
    }

    @Override
    public List<T> objectConversion(List<S> sList, String... ignoreProperties) {

        if (CollectionUtil.isNotEmpty(sList)) {

            List<T> toList = CollectionUtil.newArrayList();
            for (S s : sList) {
                T t = this.objectConversion(s, ignoreProperties);
                Optional.ofNullable(t).ifPresent(toList::add);
            }

            return toList;
        }

        return null;
    }
}
