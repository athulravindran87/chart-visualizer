package com.iscout.excelreader;

import com.iscout.excelreader.bean.AllBeanTest;
import com.iscout.excelreader.controller.ParserControllerTest;
import com.iscout.excelreader.model.AllModelTest;
import com.iscout.excelreader.service.AllServiceTest;
import com.iscout.excelreader.util.FileHelperTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllBeanTest.class,
        AllModelTest.class,
        AllServiceTest.class,
        ParserControllerTest.class,
        FileHelperTest.class
})
public class AllTests {
}
