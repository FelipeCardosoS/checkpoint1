package br.com.fiap.checkpoint1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PacienteControllerIT {

    @LocalServerPort
    int port;

    TestRestTemplate rest = new TestRestTemplate();

    String url(String p) {
        return "http://localhost:" + port + p;
    }

    @Test
    void crudCompleto() {
        // CREATE
        var novo = Map.of(
                "id", null,
                "nome", "Pedro",
                "endereco", "Rua X",
                "bairro", "Centro",
                "email", "pedro@teste.com",
                "telefone", "11999999999");
        var created = rest.postForEntity(url("/pacientes"), novo, Map.class);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(created.getBody()).isNotNull();
        var id = Long.valueOf(created.getBody().get("id").toString());

        // READ one
        var got = rest.getForEntity(url("/pacientes/" + id), Map.class);
        assertThat(got.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(got.getBody().get("nome")).isEqualTo("Pedro");

        // LIST
        var list = rest.getForEntity(url("/pacientes"), Object.class);
        assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);

        // UPDATE
        var upd = Map.of(
                "id", id,
                "nome", "Pedro Atualizado",
                "endereco", "Rua X",
                "bairro", "Centro",
                "email", "pedro@teste.com",
                "telefone", "11999999999");
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var updated = rest.exchange(url("/pacientes/" + id), HttpMethod.PUT, new HttpEntity<>(upd, headers), Map.class);
        assertThat(updated.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updated.getBody().get("nome")).isEqualTo("Pedro Atualizado");

        // DELETE
        var deleted = rest.exchange(url("/pacientes/" + id), HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        // READ 404
        var notFound = rest.getForEntity(url("/pacientes/" + id), String.class);
        assertThat(notFound.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}