package cn.darkjrong.streamingmedia.common.pojo.bean;

import cn.darkjrong.streamingmedia.common.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *  公共属性抽层类
 * @author Rong.Jia
 * @date 2020/12/14 14:57
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = 9023375960996498537L;

    /**
     *  主键
     */
    @NotNull(groups = IdGroupValidator.class, message = "id 不能为空")
    private Long id;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String updatedUser;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Long updatedTime;

    /**
     * 添加时间
     */
    @ApiModelProperty("添加时间")
    private Long createdTime;

    /**
     * 添加人
     */
    @ApiModelProperty("添加人")
    private String createdUser;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
