package junitTests;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        TestsExampleJunit.class
        // manual-solution: ранее сюда также входили NameAgeValidatorTest и PalindromeCheckerTest,
        // хотя по заданию сьют должен запускать только TestsExampleJunit.
        // ai-assisted-solution: состав сьюта ограничен классом TestsExampleJunit согласно заданию.
})
@IncludeEngines("junit-jupiter")
public class SecondSuite {
}
