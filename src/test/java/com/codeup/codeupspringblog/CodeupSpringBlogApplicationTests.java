package com.codeup.codeupspringblog;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodeupSpringBlogApplication.class)
@AutoConfigureMockMvc
public class CodeupSpringBlogApplicationTests {

	private User testUser;
	private HttpSession httpSession;

	@Autowired
	private MockMvc mvc;

	@Autowired
	UserRepository userDao;

	@Autowired
	PostRepository postDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Before
	public void setup() throws Exception {

		testUser = userDao.findByUsername("testUser");

		// Creates the test user if not exists
		if(testUser == null){
			User newUser = new User();
			newUser.setUsername("testUser");
			newUser.setPassword(passwordEncoder.encode("pass"));
			newUser.setEmail("testUser@codeup.com");
			testUser = userDao.save(newUser);
		}

		// Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
		httpSession = this.mvc.perform(post("/login").with(csrf())
						.param("username", "testUser")
						.param("password", "pass"))
				.andExpect(status().is(HttpStatus.FOUND.value()))
				.andExpect(redirectedUrl("/posts"))
				.andReturn()
				.getRequest()
				.getSession();
	}

	@Test
	public void contextLoads() {
		assertNotNull(mvc);
	}
	@Test
	public void testIfUserSessionIsActive() throws Exception {
		// It makes sure the returned session is not null
		assertNotNull(httpSession);
	}


	//testing the creation of a post, look in the posts table
	@Test
	public void testCreateAd() throws Exception {
		//Makes a Post request to /posts/create and expect a redirection to the Ad
		this.mvc.perform(
				post("/posts/create").with(csrf()).session((MockHttpSession) httpSession)
				//Add all the required parameters to your request like this
						.param("title", "TEST").param("description", "for sale"))
				.andExpect(status().is3xxRedirection());
	}

	@Test
	public void testShowAd() throws Exception {

		Post existingPost = postDao.findAll().get(0);

		// Makes a Get request to /ads/{id} and expect a redirection to the Post show page
		this.mvc.perform(
				get("/posts/" + existingPost.getId()).with(csrf()).session((MockHttpSession) httpSession))
                .andExpect(status().isOk())
				// Test the dynamic content of the page
				.andExpect(content().string(containsString(existingPost.getDescription())));
	}


	@Test
	public void testPostsIndex() throws Exception {
		Post existingPost = postDao.findAll().get(0);

		//Makes a Get request to /posts and verifies that we get some of the static text of the posts/index.html template and at least the title from the first Post is present in the template
		this.mvc.perform(get("/posts")).andExpect(status().isOk())
				//Test the static content of the page
				.andExpect(content().string(containsString("sale")))
				//Test the dynamic content of the page
				.andExpect(content().string(containsString(existingPost.getTitle())));
	}

	@Test
	public void testEditPost() throws Exception {
		//Gets the first Post for tests purposes
		Post existingPost = postDao.findAll().get(0);

		//Makes a Post request to /posts/{id}/edit and expect a redirection to the Post show page
		this.mvc.perform(
				post("/posts/" + existingPost.getId() + "/edit").with(csrf())
						.session((MockHttpSession) httpSession)
						.param("title", "edited title")
						.param("description", "edited description"))
				.andExpect(status().is3xxRedirection());

		//Makes a GET request to /posts/{id} and expect a redirection to the Post show page
		this.mvc.perform(get("/posts/" + existingPost.getId()))
				.andExpect(status().isOk())
				//Test the dynamic content of the page
				.andExpect(content().string(containsString("edited title")))
				.andExpect(content().string(containsString("edited description")));
	}

}
