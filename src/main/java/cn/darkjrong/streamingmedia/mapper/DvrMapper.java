package cn.darkjrong.streamingmedia.mapper;

import cn.darkjrong.streamingmedia.common.pojo.entity.Dvr;
import cn.darkjrong.streamingmedia.common.pojo.query.DvrQuery;

import java.util.List;

/**
 * DVR 持久层接口
 * @date 2020/12/28
 * @author Rong.Jia
 */
public interface DvrMapper {

    /**
     * 根据ID 删除录像
     * @param id 主键ID
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 0：失败，1：成功
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增录像
     * @param dvr 录像信息
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 0：失败，1：成功
     */
    int insert(Dvr dvr);

    /**
     * 新增录像
     * @param dvr 录像信息
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 0：失败，1：成功
     */
    int insertSelective(Dvr dvr);

    /**
     * 根据ID 查询录像
     * @param id 主键
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 录像
     */
    Dvr selectByPrimaryKey(Long id);

    /**
     * 修改录像
     * @param dvr 录像信息
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 0：失败，1：成功
     */
    int updateByPrimaryKeySelective(Dvr dvr);

    /**
     * 修改录像
     * @param dvr 录像信息
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 0：失败，1：成功
     */
    int updateByPrimaryKey(Dvr dvr);

    /**
     * 过滤查询 录像信息
     * @param dvrQuery 过滤条件
     * @return 录像信息
     */
    List<Dvr> findDvrs(DvrQuery dvrQuery);




}