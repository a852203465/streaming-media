package cn.darkjrong.streamingmedia.common.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * SRS 回调DTO 信息
 * @author Rong.Jia
 * @date 2020/12/13 23:49
 */

@NoArgsConstructor
@Data
public class SrsCallbackDTO implements Serializable {

    private static final long serialVersionUID = -486321142328568652L;

    private String action;

    @JSONField(name = "client_id")
    private Integer clientId;
    private String ip;
    private String vhost;
    private String app;
    private String stream;
    private String tcUrl;

    @JSONField(name = "send_bytes")
    private Integer sendBytes;

    @JSONField(name = "recv_bytes")
    private Integer recvBytes;

    private String file;
    private String cwd;

}
