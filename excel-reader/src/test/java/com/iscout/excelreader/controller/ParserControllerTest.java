package com.iscout.excelreader.controller;

import com.iscout.excelreader.service.ParserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ParserControllerTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @InjectMocks
    private ParserController testObj;

    @Mock
    private ParserService service;

    private MockMvc mockMvc;
    private File tempFolder;

    @Before
    public void setUp() throws Exception {
        tempFolder = temporaryFolder.newFolder();
        ReflectionTestUtils.setField(this.testObj, "location", tempFolder.getAbsolutePath());
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.testObj).build();
        when(this.service.getJsonFromExcel(any())).thenReturn("sample json");
    }


    @After
    public void tearDown() throws Exception {
        tempFolder.delete();
        temporaryFolder.delete();
    }

    @Test
    public void testParseExcelFile() throws Exception {
        MockMultipartFile file =
                new MockMultipartFile("file", "Summary Program.xlsx",
                                      MediaType.MULTIPART_FORM_DATA_VALUE,
                                      new FileInputStream(ResourceUtils.getFile("classpath:test/Summary Program.xlsx")));

        ResponseEntity<String> responseEntity = this.testObj.uploadFile(file);
        assertThat(responseEntity.getBody(), equalTo("sample json"));
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void testParseExcelFile_Exception() throws Exception {
        MockMultipartFile file =
                new MockMultipartFile("file", "Summary Program.xlsx",
                                      MediaType.MULTIPART_FORM_DATA_VALUE,
                                      new FileInputStream(ResourceUtils.getFile("classpath:test/Summary Program.xlsx")));
        when(this.service.getJsonFromExcel(any())).thenThrow(new RuntimeException("some problem"));

        ResponseEntity<String> responseEntity = this.testObj.uploadFile(file);
        assertThat(responseEntity.getBody(), equalTo("file upload failed"));
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.EXPECTATION_FAILED));
    }

    @Test
    public void testParseIncorrectFileExtn() throws Exception {
        MockMultipartFile file =
                new MockMultipartFile("file", "chart.json",
                                      MediaType.MULTIPART_FORM_DATA_VALUE,
                                      new FileInputStream(ResourceUtils.getFile("classpath:test/chart.json")));

        assertThat(this.testObj.uploadFile(file).getBody(), equalTo("Only xlsx and xls extension files can be processed"));
    }


    @Test
    public void testMockMvcHappyPathXlsxFile() throws Exception {
        MockMultipartFile file =
                new MockMultipartFile("file", "Summary Program.xlsx",
                                      MediaType.MULTIPART_FORM_DATA_VALUE,
                                      new FileInputStream(ResourceUtils.getFile("classpath:test/Summary Program.xlsx")));

        this.mockMvc.perform(multipart("/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("sample json"));
    }
}