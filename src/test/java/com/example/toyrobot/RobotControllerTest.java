package com.example.toyrobot;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Robot controller tests
 * 
 * @author <a href="mailto:mokhlisse_badre@yahoo.fr">Badre-Edine Mokhlisse</a>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
public class RobotControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RobotControllerTest.class);
	private static final String TEST_FILE_PATTERN = "classpath:test*.in";

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ResourceLoader resourceLoader;

	@Before
	public void setup() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}

	/**
	 * tests robot actions by submitting multiples commands stored on resources
	 * files (test1.in, test2.in ...) and compare output within correspondent
	 * output file (test1.out, test2.out ...)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRobot() throws Exception {

		Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
				.getResources(TEST_FILE_PATTERN);
		for (Resource resource : resources) {
			String inContent = new String(Files.readAllBytes(Paths.get(resource.getURI())), StandardCharsets.UTF_8);
			String inFile = resource.getFile().getPath();
			String outFile = inFile.replace(".in", ".out");
			String outContent = new String(Files.readAllBytes(Paths.get(outFile)), StandardCharsets.UTF_8);
			try {
				testCase(inContent, outContent);
			} catch (Exception e) {
				LOGGER.error("error testing file " + inFile);
			}
		}

	}

	/**
	 * send an input commands to rest service and check if it's equal to
	 * expectedOutput
	 * 
	 * @param input
	 * @param output
	 * @throws Exception
	 */
	private void testCase(String input, String expectedOutput) throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/").contentType(MediaType.TEXT_PLAIN)
				.content(input);
		this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(expectedOutput));
	}
}
