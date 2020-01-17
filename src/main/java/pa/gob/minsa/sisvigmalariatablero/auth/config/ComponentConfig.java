package pa.gob.minsa.sisvigmalariatablero.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="pa.gob.minsa.sisvigmalariatablero.auth.config", excludeFilters={ @Filter(Configuration.class)} )
public class ComponentConfig {

}
