package com.probie.renew;

import org.junit.jupiter.api.Test;
import com.probie.renew.renew.Renew;

public class MainTest {

    @Test
    void main() {
        Main.main(new String[]{});
    }

    @Test
    void turnVersionToNumber() {
        System.out.println(Renew.getInstance().turnVersionToNumber(Renew.getInstance().VERSION));
    }

    @Test
    void compareVersionWithSmallerVersion() {
        System.out.println(Renew.getInstance().compareVersionWithSmallerVersion(Renew.getInstance().VERSION, "v1.0.0"));
    }

    @Test
    void compareVersionWithBiggerVersion() {
        System.out.println(Renew.getInstance().compareVersionWithBiggerVersion(Renew.getInstance().VERSION, "v1.0.0"));
    }

}