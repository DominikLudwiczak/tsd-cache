package put.poznan.tsdcache.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import put.poznan.tsdcache.TestcontainersConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TaskNo2Test {

    @Autowired
    private RedisBadLoginAttemptsStorage storage;

    @Test
    void contextLoads() {}

    @Test
    void shouldStoreAndRetrieveValue() {
        //when
        storage.put("user1", 3);
        Integer value = storage.get("user1");
        //then
        assertThat(value).isEqualTo(3);
    }

    @Test
    void shouldIncrementFromZero() {
        //when
        storage.increment("user2");
        //then
        assertThat(storage.get("user2")).isEqualTo(1);
    }

    @Test
    void shouldIncrementExistingValue() {
        //when
        storage.put("user1", 4);
        storage.increment("user1");
        //then
        assertThat(storage.get("user1")).isEqualTo(5);
    }

    @Test
    void shouldRemoveKey() {
        //when
        storage.put("user1", 10);
        storage.remove("user1");
        //then
        assertThat(storage.get("user1")).isEqualTo(0);
    }
}
