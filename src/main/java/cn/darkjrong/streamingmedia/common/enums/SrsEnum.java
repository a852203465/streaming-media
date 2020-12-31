package cn.darkjrong.streamingmedia.common.enums;

/**
 *  SRS 枚举类
 * @author Rong.Jia
 * @date 2020/12/18 19:50
 */
public class SrsEnum {

    public static enum SwitchEnum {
        disable,enable
    }

    public static enum ScopeEnum{
        dvr,vhost,pithy_print_ms,srs_log_file,srs_log_tank,srs_log_level,ff_log_dir,chunk_size,pid,listen,minimal,global
    }

    public static enum RpcEnum {
        reload,raw,query,update
    }



}
