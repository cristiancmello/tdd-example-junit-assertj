
import org.assertj.core.util.Maps;
import org.example.Dog;
import org.example.Person;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class ExampleTest {
    @Test
    public void whenComparingReferences_thenNotEqual() {
        Dog fido = new Dog("Fido", 5.15f);
        Dog fidosClone = new Dog("Fido", 5.15f);

        assertThat(fido).isNotEqualTo(fidosClone);
    }

    @Test
    public void whenComparingFields_thenEqual() {
        Dog fido = new Dog("Fido", 5.15f);
        Dog fidosClone = new Dog("Fido", 5.15f);

        assertThat(fido).usingRecursiveComparison().isEqualTo(fidosClone);
    }

    @Test
    public void whenCheckingForElement_thenContains() {
        List<String> list = Arrays.asList("1", "2", "3");

        assertThat(list).contains("1");
    }

    @Test
    public void whenCheckingForElement_thenMultipleAssertions() {
        List<String> list = Arrays.asList("1", "2", "3");

        assertThat(list).isNotEmpty();
        assertThat(list).startsWith("1");
        assertThat(list).doesNotContainNull();

        assertThat(list)
            .isNotEmpty()
            .contains("1")
            .startsWith("1")
            .doesNotContainNull()
            .containsSequence("2", "3");
    }

    @Test
    public void whenCheckingRunnable_theIsInterface() {
        assertThat(Runnable.class).isInterface();
    }

    @Test
    public void whenCheckingCharacter_thenIsUnicode() {
        char someCharacter = 'c';

        assertThat(someCharacter)
            .isNotEqualTo('a')
            .inUnicode()
            .isGreaterThanOrEqualTo('b')
            .isLowerCase();
    }

    @Test
    public void whenAssigningNSEExToException_thenIsAssignable() {
        assertThat(Exception.class)
            .isAssignableFrom(NoSuchElementException.class);
    }

    @Test
    public void whenCheckingString_then() {
        assertThat("".isEmpty()).isTrue();
    }

    @Test
    public void whenCheckingFile_then() throws Exception {
        final File someFile = File.createTempFile("aaa", "bbb");
        someFile.deleteOnExit();

        assertThat(someFile)
            .exists()
            .isFile()
            .canRead()
            .canWrite();
    }

    @Test
    public void whenCheckingIS_then() {
        InputStream given = new ByteArrayInputStream("foo".getBytes());
        InputStream expected = new ByteArrayInputStream("foo".getBytes());

        assertThat(given).hasSameContentAs(expected);
    }

    @Test
    public void whenGivenMap_then() {
        Map<Integer, String> map = Maps.newHashMap(2, "a");

        assertThat(map)
            .isNotEmpty()
            .containsKey(2)
            .doesNotContainKeys(10)
            .contains(entry(2, "a"));
    }

    @Test
    public void whenGivenException_then() {
        Exception exception = new Exception("abc");

        assertThat(exception)
            .hasNoCause()
            .hasMessageEndingWith("c");
    }

    @Disabled // JUNIT 5: @Disabled; JUNIT 4: @Ignore
    @Test
    public void whenRunningAssertion_thenDescribed() {
        Person person = new Person("Alex", 34);

        assertThat(person.getAge())
            .as("%s's age should be equal to 100")
            .isEqualTo(100);
    }
}
