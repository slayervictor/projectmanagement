// Written by Maria
package cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(plugin="summary"
		 ,features = {
			        "features/Login.feature",
			        "features/Logout.feature"
			    }
		 ,snippets = SnippetType.CAMELCASE
		 ,publish= false
		 )

public class UserLoginLogoutTest {

}
