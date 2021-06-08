package com.iscout.excelreader.model;

import com.iscout.excelreader.bean.DataBeanConfig;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule;
import com.openpojo.validation.rule.impl.SerializableMustHaveSerialVersionUIDRule;
import com.openpojo.validation.rule.impl.TestClassMustBeProperlyNamedRule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UIModelTest {
    @Test
    public void pojoTest() {
        PojoClass testObj = PojoClassFactory.getPojoClass(UIModel.class);
        Validator validator = ValidatorBuilder.create()
                .with(new SerializableMustHaveSerialVersionUIDRule())
                .with(new TestClassMustBeProperlyNamedRule())
                .with(new NoPublicFieldsExceptStaticFinalRule())
                .with(new EqualsAndHashCodeMatchRule())
                .build();
        validator.validate(testObj);
    }

    @Test
    public void testJsonConversion() throws Exception {
        UIModel sampleData = sampleData();
        String value = new DataBeanConfig().objectMapper().writeValueAsString(sampleData);
        UIModel pojo = new DataBeanConfig().objectMapper().readValue(value, UIModel.class);
        assertThat(sampleData, equalTo(pojo));
    }

    private UIModel sampleData() {
        return UIModel.builder().pID(1).pName("Test JSGantt Char")
                .pStart("2017-02-25")
                .pEnd("2017-03-17")
                .pPlanStart("2017-04-01")
                .pPlanEnd("2017-04-15 12:00")
                .pClass("ggroupblack")
                .pLink("")
                .pMile(0)
                .pRes("Brian")
                .pComp(0)
                .pGroup(0)
                .pParent(0)
                .pOpen(1)
                .pDepend("")
                .pCaption("")
                .pCost(1000)
                .pNotes("Some Notes text")
                .pBarText("ex. bar text")
                .category("My Category")
                .sector("Finance")
                .build();
    }

    private String jsonSample() {
        return "{\"category\":\"My Category\",\"sector\":\"Finance\",\"pcost\":1000,\"pbarText\":\"ex. bar text\",\"pcaption\":\"\",\"pclass\":\"ggroupblack\",\"pdepend\":\"\",\"pend\":\"2017-03-17\",\"pgroup\":0,\"pid\":1,\"plink\":\"\",\"pmile\":0,\"pname\":\"Test JSGantt Char\",\"pnotes\":\"Some Notes text\",\"popen\":1,\"pparent\":0,\"pplanEnd\":\"2017-04-15 12:00\",\"pres\":\"Brian\",\"pplanStart\":\"2017-04-01\",\"pstart\":\"2017-02-25\",\"pcomp\":0}";
    }
}