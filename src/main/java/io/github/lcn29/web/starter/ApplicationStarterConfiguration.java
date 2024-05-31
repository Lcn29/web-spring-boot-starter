package io.github.lcn29.web.starter;

import io.github.lcn29.web.starter.config.WebBaseConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 应用配置
 *
 * @author canxin.li
 * @date 2024-05-28 20:16:38
 */
@Configuration
@Import(value = {WebBaseConfiguration.class})
public class ApplicationStarterConfiguration {
}
