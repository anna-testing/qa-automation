package junitTests;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({
        MethodExamplesTest.class
})
@IncludeEngines("junit-jupiter")
public class FirstSuite {
}
