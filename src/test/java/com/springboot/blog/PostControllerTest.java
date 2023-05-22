package com.springboot.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.springboot.blog.controller.PostController;
import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PostControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    Post post_1= new Post(88L,"one","_one","__one");
    Post post_2= new Post(99L,"two","_two","__two");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void getAllRecords_success() throws Exception{
    List<Post> posts=new ArrayList<>(Arrays.asList(post_1,post_2));
        when(postRepository.findAll()).thenReturn(posts);
    }
   @Test
    public void getMealById_success() throws Exception {
        Mockito.when(postRepository.findById(post_1.getId())).thenReturn(java.util.Optional.of(post_1));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/88")
           .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk());
    }

    @Test
    public void deleteRecord_success()throws Exception{
        Mockito.when(postRepository.findById(post_2.getId())).thenReturn(java.util.Optional.of(post_2));
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/posts/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testHttpGet() throws Exception {
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk()) ;
    }


}
