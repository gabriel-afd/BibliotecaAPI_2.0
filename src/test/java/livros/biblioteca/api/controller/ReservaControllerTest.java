package livros.biblioteca.api.controller;

import livros.biblioteca.api.domain.livros.Categoria;
import livros.biblioteca.api.domain.reserva.AgendaReservas;
import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ReservaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosReservaLivro> dadosReservaLivroJson;

    @Mock
    private AgendaReservas agendaReservas;

    @InjectMocks
    private ReservaController reservaController;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(reservaController).build();
    }


    @Test
    @DisplayName("API utiliza o Bean Validation. Deverá devolver o código http 400 quando as informações estiverem inválidas")
    @WithMockUser
    void reservarLivro_cenario1() throws Exception{
        var response = mvc.perform(post("/reserva"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver o código 200 quando as informações do json são válidas")
    @WithMockUser
    void reservarLivro_cenario2() throws Exception{
        var data = LocalDateTime.now().plusHours(1);
        var category = Categoria.AVENTURA;
        var title = "ABC";

        var dadosReservaLivro = new DadosReservaLivro(null, 6l,2l,data,5l, category);
        when(agendaReservas.reservar(any())).thenReturn(dadosReservaLivro);

        var response = mvc.perform(post("/reserva")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((dadosReservaLivroJson.write(new DadosReservaLivro(null,6l,2l,data,5l, category)).getJson())))

                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosReservaLivroJson.write(dadosReservaLivro).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}