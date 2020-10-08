package features

import cucumber.api.CucumberOptions
import io.cucumber.junit.platform.engine.Cucumber

@Cucumber
@CucumberOptions(
        features=["src/test/resources/features/accountCreation.feature"]
)
class RunTests {
    //myTestAccount@gmail.com
    //this_is-secret1!
}