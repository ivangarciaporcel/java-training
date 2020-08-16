package sia.tacocloud.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * user: IvànAlejandro
 * date: 16/8/2020 12:18 a. m.
 **/

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
public class OrderProperties {

    private int pageSize = 20;
}
