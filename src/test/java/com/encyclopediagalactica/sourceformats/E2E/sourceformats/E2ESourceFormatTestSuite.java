package com.encyclopediagalactica.sourceformats.E2E.sourceformats;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("SourceFormat Endpoint E2E")
@SelectClasses(
    {
        AddEndpointE2ETests.class,
        DeleteByIdEndpointE2ETests.class,
        FindByIdEndpointE2ETests.class,
        GetAllEndpointE2ETests.class,
        UpdateEndpointE2ETests.class
    })
public class E2ESourceFormatTestSuite {
}
