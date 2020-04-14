package nl.agility.customer.domain;

import nl.agility.customer.CustomerTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class CustomerJsonTest {

    @Autowired
    private JacksonTester<Customer> json;

    @Test
    void serializationWithIgnoredPropertiesResultsInJsonWithoutIgnoredProperties() throws Exception {
        Customer customer = CustomerTestUtils.getCustomers().get(0);

        JsonContent<Customer> result = this.json.write(customer);

        assertThat(result).hasJsonPath("$.id");
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).hasJsonPath("$.version");
        assertThat(result).extractingJsonPathNumberValue("$.version").isEqualTo(1);
        assertThat(result).doesNotHaveJsonPath("$.created");
        assertThat(result).doesNotHaveJsonPath("$.lastUpdated");
        assertThat(result).hasJsonPath("$.name");
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Robert C. Martin");
    }

    @Test
    void serializationWithoutIgnoredPropertiesResultsInJsonWithoutIgnoredProperties() throws Exception {
        Customer customer = CustomerTestUtils.getCustomers().get(0);
        customer.setCreated(null);
        customer.setLastUpdated(null);

        JsonContent<Customer> result = this.json.write(customer);

        assertThat(result).hasJsonPath("$.id");
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).hasJsonPath("$.version");
        assertThat(result).extractingJsonPathNumberValue("$.version").isEqualTo(1);
        assertThat(result).doesNotHaveJsonPath("$.created");
        assertThat(result).doesNotHaveJsonPath("$.lastUpdated");
        assertThat(result).hasJsonPath("$.name");
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Robert C. Martin");
    }

    @Test
    void deserializationWithIgnoredPropertiesResultsInInstanceWithNullValues() throws Exception {
        String jsonContent = "{\"id\": 1, \"version\": 1, \"created\":\"2020-04-10 01:02:03.123456789\", " +
            "\"lastUpdated\":\"2020-04-12 21:40:19.987654321\", \"name\":\"Robert C. Martin\"}";

        Customer result = this.json.parse(jsonContent).getObject();

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getVersion()).isEqualTo(1);
        assertThat(result.getCreated()).isNull();
        assertThat(result.getLastUpdated()).isNull();
        assertThat(result.getName()).isEqualTo("Robert C. Martin");
    }

    @Test
    void deserializationWithoutIgnoredPropertiesResultsInInstanceWithNullValues() throws Exception {
        String jsonContent = "{\"id\": 1, \"version\": 1, \"name\":\"Robert C. Martin\"}";

        Customer result = this.json.parse(jsonContent).getObject();

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getVersion()).isEqualTo(1);
        assertThat(result.getCreated()).isNull();
        assertThat(result.getLastUpdated()).isNull();
        assertThat(result.getName()).isEqualTo("Robert C. Martin");
    }

}
