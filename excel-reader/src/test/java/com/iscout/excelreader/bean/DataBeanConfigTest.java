package com.iscout.excelreader.bean;

import org.junit.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DataBeanConfigTest {

    private DataBeanConfig testObj = new DataBeanConfig();

    @Test
    public void objectMapper() {
        assertNotNull(this.testObj.objectMapper());
        assertThat(this.testObj.objectMapper().getRegisteredModuleIds(), hasItem("com.fasterxml.jackson.datatype.jsr310.JavaTimeModule"));
    }
}