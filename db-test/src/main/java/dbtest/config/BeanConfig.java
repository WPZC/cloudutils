package dbtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import redistemplate.utils.RedisTemplateUtils;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/19 11:14
 */
@Configuration
@Import({RedisTemplateUtils.class})
public class BeanConfig {
}
