package com.team.piickle;

import com.team.piickle.auth.jwt.TokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ParameterStorePropertiesTest {
    @Autowired
    private TokenProvider properties;

    @Test
    void local_파라미터를_가져온다() throws Exception {
        assertThat(properties.getSecret()).isEqualTo("VlwEyVBsYt9V7zq57TejMnVUyzblYcfPQye08f7MGVA9XkHa");
    }
}
