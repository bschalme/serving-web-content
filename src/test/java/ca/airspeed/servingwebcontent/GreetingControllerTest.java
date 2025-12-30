package ca.airspeed.servingwebcontent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.jsoup.Jsoup.parse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void defaultGreeting() throws Exception {
        // When:
        MvcResult result = mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andReturn();

        // Then:
        String html = result.getResponse().getContentAsString();
        Document doc = parse(html);
        Element head = doc.head();
        Elements titles = head.getElementsByTag("title");
        assertThat("<title> tags;", titles, hasSize(1));
        assertThat("Title;", titles.get(0).text(), is("Getting Started: Serving Web Content"));
        Element body = doc.body();
        assertThat("Body;", body, notNullValue());
        Elements paragraphs = body.getElementsByTag("p");
        assertThat("<p> tags;", paragraphs, hasSize(greaterThan(0)));
        assertThat("Greeting text;", paragraphs.get(0).text(), is("Salutations, World!"));
    }

    @Test
    void namedGreeting() throws Exception {
        // When:
        MvcResult result = mockMvc.perform(get("/greeting?name=Brian"))
                .andExpect(status().isOk())
                .andReturn();

        // Then:
        String html = result.getResponse().getContentAsString();
        Document doc = parse(html);
        Element body = doc.body();
        assertThat("Body;", body, notNullValue());
        Elements paragraphs = body.getElementsByTag("p");
        assertThat("<p> tags;", paragraphs, hasSize(greaterThan(0)));
        assertThat("Greeting text;", paragraphs.get(0).text(), is("Salutations, Brian!"));
    }
}
