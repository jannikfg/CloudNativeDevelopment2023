package thi.cnd;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
@AnalyzeClasses(packages = "thi.cnd")
public class DependencyTest {

  @ArchTest
  static final ArchRule REST_SHOULD_NOT_ACCESS_JPA = noClasses().that()
      .resideInAnyPackage("..adapter.api..").should().accessClassesThat()
      .resideInAPackage("..adapter.jpa..");

  @Test
  void testSomething() {
  }

}
