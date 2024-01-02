package thi.cnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class TestUtil {

  public static BaseMatcher<String> createJsonMatcher(String expected) {
    return new JsonMatcher(expected);
  }

  /**
   * Load an existing resource file at the given relative path.
   *
   * @param relativeResourcePath a relative path to src/test/resources
   * @return The resource file content as text
   */
  public static String loadTestResource(String relativeResourcePath) {
    Path path = Paths.get("src/test/resources", relativeResourcePath);

    File file = new File(path.toUri());

    try {
      return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.out.println("Your resource path is probably invalid. Path was: " + path.toUri());
      throw new RuntimeException(e);
    }
  }

  private static class JsonMatcher extends BaseMatcher<String> {

    private final String expected;

    public JsonMatcher(String expected) {
      this.expected = expected;
    }

    @Override
    public boolean matches(Object actual) {
      ObjectMapper mapper = new ObjectMapper();

      try {
        // we use the Jackson mapper to avoid indentation errors
        // this allows us to provide expectation files in a readable format
        Assertions.assertThat(mapper.readTree((String) actual))
            .isEqualTo(mapper.readTree(expected));
        return true;
      } catch (JsonProcessingException e) {
        System.err.println("Received: " + actual.toString());
        System.err.println("Expected: " + expected);
        throw new RuntimeException(e);
      }
    }

    @Override
    public void describeTo(Description description) {
      description.appendText("Expected: ");
      description.appendText(expected);
    }
  }

}
