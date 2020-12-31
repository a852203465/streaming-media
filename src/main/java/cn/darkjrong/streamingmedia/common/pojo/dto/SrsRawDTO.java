package cn.darkjrong.streamingmedia.common.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 *  SRS 顶层参数对象
 * @author Rong.Jia
 * @date 2020/12/18 19:48
 */
@Data
public class SrsRawDTO implements Serializable {

    private static final long serialVersionUID = 6859754509426388552L;

    private String scope;

    private String value;

    private String rpc;

    private String param;



}
