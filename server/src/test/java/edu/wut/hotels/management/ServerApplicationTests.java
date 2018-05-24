package edu.wut.hotels.management;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {
    @Test
    public void contextLoads() {
    }

    @Test
    public void testWhyBigDecimalIsBetterForMoney() {
        double a = 0.02;
        double b = 0.03;
        double c = b - a;
        System.out.println("doublke" + c);

        BigDecimal _a = new BigDecimal("0.02");
        BigDecimal _b = new BigDecimal("0.03");
        BigDecimal _c = _b.subtract(_a);
        System.out.println(_c);

        System.out.println();
    }
}
