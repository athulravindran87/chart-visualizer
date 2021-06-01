package com.iscout.excelreader;

import com.iscout.excelreader.bean.AllBeanTest;
import com.iscout.excelreader.model.AllModelTest;
import com.iscout.excelreader.service.AllServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllBeanTest.class,
        AllModelTest.class,
        AllServiceTest.class,
        AllServiceTest.class
})
public class AllTests {
}
