package cn.darkjrong.streamingmedia.stream.handler;

import cn.darkjrong.streamingmedia.stream.manager.CommandManager;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 任务消息输出处理器
 *
 * @author eguid
 */
@Slf4j
public class OutHandler extends Thread {

    /**
     * 控制状态
     */
    private volatile boolean desstatus = true;

    /**
     * 读取输出流
     */
    private BufferedReader br = null;

    /**
     * 任务ID
     */
    private String id = null;

    private OutHandlerMethod ohm = SpringUtil.getBean(OutHandlerMethod.class);

    /**
     * 创建输出线程（默认立即开启线程）
     *
     * @param is
     * @param id
     * @return
     */
    public static OutHandler create(InputStream is, String id) {
        return create(is, id, true);
    }

    /**
     * 创建输出线程
     *
     * @param is
     * @param id
     * @param start-是否立即开启线程
     * @return
     */
    public static OutHandler create(InputStream is, String id, boolean start) {
        OutHandler out = new OutHandler(is, id);
        if (start) {
            out.start();
        }

        return out;
    }

    public void setDesStatus(boolean desStatus) {
        this.desstatus = desStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OutHandler(InputStream is, String id) {
        br = new BufferedReader(new InputStreamReader(is));
        this.id = id;
    }

    /**
     * 重写线程销毁方法，安全的关闭线程
     */
    @Override
    public void destroy() {
        setDesStatus(false);
    }

    /**
     * 执行输出线程
     */
    @Override
    public void run() {
        String msg = null;
        try {

            while (desstatus && (msg = br.readLine()) != null) {
                ohm.parse(id, msg);
                if (ohm.isbroken()) {
					log.info("检测到中断，提交重启任务给保活处理器 {}", id);
                    //如果发生异常中断，立即进行保活
                    //把中断的任务交给保活处理器进行进一步处理
                    KeepAliveHandler.add(id);
                }
            }
        } catch (IOException e) {
			log.info("发生内部异常错误，自动关闭线程 {}",  this.getId());
            destroy();
        } finally {
            if (this.isAlive()) {
                destroy();
            }
        }
    }

}
