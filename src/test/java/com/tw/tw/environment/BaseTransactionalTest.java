package com.tw.tw.environment;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provide possibility to create new transaction for each test and revert after.
 * <p>
 * Needed to ensure independence of tests.
 */
@RunWith(SpringRunner.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public abstract class BaseTransactionalTest {
}
