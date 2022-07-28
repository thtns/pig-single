package com.thtns.cloud.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lengleng
 * @date 2020/10/4
 * <p>
 * 网关配置文件
 */
@Data
@ConfigurationProperties("gateway")
@Component
public class GatewayConfigProperties {

    /**
     * 网关解密登录前端密码 秘钥 {@link }
     */
    private String encodeKey;

    /**
     * 网关不需要校验验证码的客户端 {@link }
     */
    private List<String> ignoreClients;

}
