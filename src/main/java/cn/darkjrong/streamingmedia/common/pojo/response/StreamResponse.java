package cn.darkjrong.streamingmedia.common.pojo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *  streams信息
 * @author Rong.Jia
 * @date 2020/12/12 05:24
 */
@NoArgsConstructor
@Data
public class StreamResponse implements Serializable {

    private static final long serialVersionUID = -1304605456366958662L;

    /**
     * code : 0
     * server : 3
     * streams : [{"id":10,"name":"Y2EzMzY3N2ItYjhlZC00YTQ3LWFjOTktODE3MTBiYWZkMWJj","vhost":4,"app":"live","live_ms":1607721989167,"clients":1,"frames":25296,"send_bytes":3927180,"recv_bytes":259224994,"kbps":{"recv_30s":56586,"send_30s":536},"publish":{"active":true,"cid":496},"video":{"codec":"H264","profile":"High","level":"4.1","width":1280,"height":720},"audio":{"codec":"AAC","sample_rate":44100,"channel":2,"profile":"LC"}}]
     */

    private Integer code;
    private Integer server;
    private List<StreamsBean> streams;

    @Data
    public static class StreamsBean implements Serializable {

        private static final long serialVersionUID = 1715201873412011471L;

        /**
         * id : 10
         * name : Y2EzMzY3N2ItYjhlZC00YTQ3LWFjOTktODE3MTBiYWZkMWJj
         * vhost : 4
         * app : live
         * live_ms : 1607721989167
         * clients : 1
         * frames : 25296
         * send_bytes : 3927180
         * recv_bytes : 259224994
         * kbps : {"recv_30s":56586,"send_30s":536}
         * publish : {"active":true,"cid":496}
         * video : {"codec":"H264","profile":"High","level":"4.1","width":1280,"height":720}
         * audio : {"codec":"AAC","sample_rate":44100,"channel":2,"profile":"LC"}
         */

        private Long id;
        private String name;
        private Long vhost;
        private String app;
        private Long live_ms;
        private Long clients;
        private Long frames;
        private Long send_bytes;
        private Long recv_bytes;
        private KbpsBean kbps;
        private PublishBean publish;
        private VideoBean video;
        private AudioBean audio;

        @Data
        public static class VideoBean implements Serializable {

            private static final long serialVersionUID = 314194550788115992L;

            /**
             * codec : H264
             * profile : High
             * level : 4.1
             * width : 1280
             * height : 720
             */

            private String codec;
            private String profile;
            private String level;
            private int width;
            private int height;

        }

        @Data
        public static class AudioBean implements Serializable {

            private static final long serialVersionUID = -7459830248290023105L;

            /**
             * codec : AAC
             * sample_rate : 44100
             * channel : 2
             * profile : LC
             */

            private String codec;
            private int sample_rate;
            private int channel;
            private String profile;

        }

        @NoArgsConstructor
        @Data
        public static class KbpsBean {

            /**
             * recv_30s : 56586
             * send_30s : 536
             */

            private Long recv_30s;
            private Long send_30s;
        }

        @NoArgsConstructor
        @Data
        public static class PublishBean {

            /**
             * active : true
             * cid : 496
             */

            private Boolean active;
            private Long cid;
        }


    }

}
