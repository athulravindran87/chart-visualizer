package com.iscout.excelreader.model;

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

public class SummarySheetTest {

    @Test
    public void pojoTest() {
        PojoClass testObj = PojoClassFactory.getPojoClass(SummarySheet.class);
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
}