package nz.co.airNZ;


import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by jingbai on 12/12/17.
 */
@RunWith(Cucumber.class)
@Cucumber.Options (
        format ={"json:target/cucumber.json"},
        features = {"src/test/resource" },
        glue = { "nz.co.airNZ"},
        tags = { "@flight"}
)
public class runnerTest {


}