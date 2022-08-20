package com.encyclopediagalactica.sourceformats.E2E;

import com.encyclopediagalactica.sourceformats.E2E.sourceformats.AddEndpointE2ETests;
import com.encyclopediagalactica.sourceformats.E2E.sourceformats.DeleteByIdEndpointE2ETests;
import com.encyclopediagalactica.sourceformats.E2E.sourceformats.FindByIdEndpointE2ETests;
import com.encyclopediagalactica.sourceformats.E2E.sourceformats.GetAllEndpointE2ETests;
import com.encyclopediagalactica.sourceformats.E2E.sourceformats.UpdateEndpointE2ETests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("End-to-End Test Suite")
@SelectClasses(
    {
        // SourceFormat
        AddEndpointE2ETests.class,
        DeleteByIdEndpointE2ETests.class,
        FindByIdEndpointE2ETests.class,
        GetAllEndpointE2ETests.class,
        UpdateEndpointE2ETests.class
    })
public class E2ESuite {
}
