package portfolio.aqa;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite //это runner
@IncludeEngines("cucumber") //создаёт JUnit-набор тестов
@SelectClasspathResource("features") //указывает папку feature-файлов
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "portfolio.aqa") //Через glue Cucumber находит Step Definitions и hooks.
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @diagnostic") //Фильтр not @diagnostic исключает намеренно падающий сценарий из обычного регресса
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" //Allure plugin сохраняет шаги, ошибки и скриншоты для отчёта.
)
public class CucumberTest {
}

