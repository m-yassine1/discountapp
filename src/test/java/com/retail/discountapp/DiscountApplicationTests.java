package com.retail.discountapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscountApplicationTests {

    @Test
    public void applicationContextTest() {
        DiscountApplication.main(new String[] {});
        assertTrue("If I run, Means I'm Okay!", true);
    }
}
