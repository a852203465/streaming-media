package cn.darkjrong.streamingmedia.common.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  DVR DTO 对象
 * @author Rong.Jia
 * @date 2020/12/18 19:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DvrDTO extends SrsRawDTO implements Serializable {

    private static final long serialVersionUID = -7165809342603822256L;

    private String data;

}
