package com.iscout.excelreader.model;

import com.iscout.excelreader.bean.DataBeanConfig;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule;
import com.openpojo.validation.rule.impl.SerializableMustHaveSerialVersionUIDRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.rule.impl.TestClassMustBeProperlyNamedRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
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
                .with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new GetterTester())
                .with(new SetterTester())
                .with(new EqualsAndHashCodeMatchRule())
                .build();
        validator.validate(testObj);
    }

    @Test
    public void testJsonConversion() throws Exception {
        String value = new DataBeanConfig().objectMapper().writeValueAsString(sampleData());
        assertThat(value, equalTo(this.jsonSample()));
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
        return "{\"category\":\"My Category\",\"pBarText\":\"ex. bar text\",\"pCaption\":\"\",\"pClass\":\"ggroupblack\",\"pComp\":0,\"pCost\":1000,\"pDepend\":\"\",\"pEnd\":\"2017-03-17\",\"pGroup\":0,\"pID\":1,\"pLink\":\"\",\"pMile\":0,\"pName\":\"Test JSGantt Char\",\"pNotes\":\"Some Notes text\",\"pOpen\":1,\"pParent\":0,\"pPlanEnd\":\"2017-04-15 12:00\",\"pPlanStart\":\"2017-04-01\",\"pRes\":\"Brian\",\"pStart\":\"2017-02-25\",\"sector\":\"Finance\"}";
    }
}