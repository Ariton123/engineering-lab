import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VacationPlansTest {
    private VacationPlans vacationPlans = new VacationPlans();

//    @Before
//    public void init() {
//        vacationPlans = new VacationPlans();
//    }


    //reach 1
    @BeforeEach
    public void reach1() {
        vacationPlans.makePTrue();
        vacationPlans.makeQTrue();
        vacationPlans.makeRTrue();
    }
    // reach2
    public void reach2() {
        vacationPlans.makePTrue();
        vacationPlans.makeQTrue();
        vacationPlans.makeRFalse();
    }

    // reach3
    public void reach3() {
        vacationPlans.makePTrue();
        vacationPlans.makeQFalse();
        vacationPlans.makeRTrue();
    }

    // reach4
    public void reach4() {
        vacationPlans.makePTrue();
        vacationPlans.makeQFalse();
        vacationPlans.makeRFalse();
    }

    // reach5
    public void reach5() {
        vacationPlans.makePFalse();
        vacationPlans.makeQTrue();
        vacationPlans.makeRTrue();
    }

    // reach6
    public void reach6() {
        vacationPlans.makePFalse();
        vacationPlans.makeQTrue();
        vacationPlans.makeRFalse();
    }

    // reach7
    public void reach7() {
        vacationPlans.makePFalse();
        vacationPlans.makeQFalse();
        vacationPlans.makeRTrue();
    }

    // reach8
    public void reach8() {
        vacationPlans.makePFalse();
        vacationPlans.makeQFalse();
        vacationPlans.makeRFalse();
    }

    @Test
    public void test1() throws Exception{
        // 1: T T T
        this.reach1();

        VacationDates vd = vacationPlans.vd;
        Person person = vacationPlans.person;

        assertTrue(vacationPlans.canGoOnVacation(person, vd));
    }
    @Test
    public void test2() throws Exception{
        // 1: T T F
        this.reach2();

        VacationDates vd = vacationPlans.vd;
        Person person = vacationPlans.person;

        assertTrue(vacationPlans.canGoOnVacation(person, vd));
    }

    @Test
    public void test3() throws Exception{
        // 1: T F T
        this.reach3();

        VacationDates vd = vacationPlans.vd;
        Person person = vacationPlans.person;

        assertTrue(vacationPlans.canGoOnVacation(person, vd));
    }

    @Test
    public void test4() throws Exception{
        // 1: T F F
        this.reach4();

        VacationDates vd = vacationPlans.vd;
        Person person = vacationPlans.person;

        assertFalse(vacationPlans.canGoOnVacation(person, vd));
    }


    // 1 2 3 4 5
    @Test
    public void test5() throws Exception{
        // 1: T T T
        this.reach1();

        // 2: T T F
        this.reach2();

        // 3: T F T
        this.reach3();

        // 4: T F F
        this.reach4();

        // 5: F T T
        this.reach5();


        VacationDates vd = vacationPlans.vd;

        Person person = vacationPlans.person;
        assertFalse(vacationPlans.canGoOnVacation(person, vd));
    }


    // 1 2 3 4 5 6
    @Test
    public void test6() throws Exception{
        // 1: T T T
        this.reach1();

        // 2: T T F
        this.reach2();

        // 3: T F T
        this.reach3();

        // 4: T F F
        this.reach4();

        // 5: F T T
        this.reach5();

        // 6: F T F
        this.reach6();


        VacationDates vd = vacationPlans.vd;

        Person person = vacationPlans.person;
        assertFalse(vacationPlans.canGoOnVacation(person, vd));
    }

    // 1 2 3 4 5 6 7
    @Test
    public void test7() throws Exception{
        // 1: T T T
        this.reach1();

        // 2: T T F
        this.reach2();

        // 3: T F T
        this.reach3();

        // 4: T F F
        this.reach4();

        // 5: F T T
        this.reach5();

        // 6: F T F
        this.reach6();

        // 7: F F T
        this.reach7();


        VacationDates vd = vacationPlans.vd;

        Person person = vacationPlans.person;
        assertFalse(vacationPlans.canGoOnVacation(person, vd));
    }

    // 2 3 4 6
    @Test
    public void test8() throws Exception{

        // 2: T T F
        this.reach2();

        // 3: T F T
        this.reach3();

        // 4: T F F
        this.reach4();

        // 6: F T F
        this.reach6();


        VacationDates vd = vacationPlans.vd;

        Person person = vacationPlans.person;
        assertFalse(vacationPlans.canGoOnVacation(person, vd));
    }

    // 2 3 4 7
    @Test
    public void test9() throws Exception{

        // 2: T T F
        this.reach2();

        // 3: T F T
        this.reach3();

        // 4: T F F
        this.reach4();

        // 7: F F T
        this.reach7();

        VacationDates vd = vacationPlans.vd;

        Person person = vacationPlans.person;
        assertFalse(vacationPlans.canGoOnVacation(person, vd));
    }

    // 2 3 4 6 7
    @Test
    public void test10() throws Exception{

        // 2: T T F
        this.reach2();

        // 3: T F T
        this.reach3();

        // 4: T F F
        this.reach4();

        // 6: F T F
        this.reach6();

        // 7: F F T
        this.reach7();

        VacationDates vd = vacationPlans.vd;

        Person person = vacationPlans.person;
        assertFalse(vacationPlans.canGoOnVacation(person, vd));
    }




}




