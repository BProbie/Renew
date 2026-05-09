package com.probie.renew;

import org.junit.jupiter.api.Test;
import com.probie.renew.Renew.Renew;

public class MainTest {

    @Test
    void main() {
        Main.main(new String[]{});
    }

    @Test
    void turnVersionToNumber() {
        System.out.println(Renew.getInstance().turnVersionToNumber(Renew.getInstance().VERSION));
    }

}