package cc.chensoul.jgiven;

import com.tngtech.jgiven.integration.spring.EnableJGiven;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableJGiven
@ComponentScan(basePackages = {"cc.chensoul.zendesk"})
public class JGivenTestConfig {

}
