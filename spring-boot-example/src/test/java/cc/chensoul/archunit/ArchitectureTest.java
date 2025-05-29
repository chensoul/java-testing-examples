package cc.chensoul.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchitectureTest {

  @Test
  void servicesShouldNotDependOnControllers() {
    JavaClasses importedClasses = new ClassFileImporter().importPackages("cc.chensoul.customer");

    noClasses()
      .that().resideInAPackage("cc.chensoul.customer.service")
      .should().dependOnClassesThat().resideInAPackage("cc.chensoul.customer.controller")
      .check(importedClasses);
  }

  @Test
  void localDateNowShouldUseClock() {
    ArchRule rule = noClasses().should()
      .callMethod(LocalDate.class, "now");

    rule.check(new ClassFileImporter()
      .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
      .importPackages("cc.chensoul.customer"));
  }

  @Test
  void noCyclicDependencies() {
    JavaClasses importedClasses = new ClassFileImporter().importPackages("cc.chensoul.customer");

    SlicesRuleDefinition.slices()
      .matching("cc.chensoul.customer.(*)..")
      .should().beFreeOfCycles()
      .check(importedClasses);
  }

  @Test
  void serviceClassesShouldHaveProperNaming() {
    JavaClasses importedClasses = new ClassFileImporter().importPackages("cc.chensoul.customer");

    ArchRuleDefinition.classes()
      .that().resideInAPackage("cc.chensoul.customer.service")
      .should().haveSimpleNameEndingWith("Service")
      .check(importedClasses);
  }

  @Test
  void shouldNotUseJavaUtilLogging() {
    JavaClasses importedClasses = new ClassFileImporter()
      .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
      .importPackages("cc.chensoul.customer");

    noClasses()
      .should().dependOnClassesThat().belongToAnyOf(java.util.logging.Logger.class)
      .check(importedClasses);
  }

  public static final ArchRule serviceLayerShouldNotAccessRepositoriesDirectly =
    noClasses()
      .that().resideInAPackage("cc.chensoul.customer")
      .should().dependOnClassesThat().resideInAPackage("cc.chensoul.customer");
}
