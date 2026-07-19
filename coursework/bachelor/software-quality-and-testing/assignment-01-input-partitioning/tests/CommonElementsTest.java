import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

//C1: List1 is not null. 2 tests: C1=T; C1=F;
//C2: List2 is not null. 2 tests: C2=T; C2=F;
//C3: Returns non-null list of common elements. 5 tests: C1=T, C2=T, C3=T;
//                                   C1=T, C2=T, C3=F;
//                                   C1=T, C2=F, C3=F;
//                                   C1=F, C2=T, C3=F;
//                                   C1=F, C2=F, C3=F;
//C4: The new list will have all the elements in common with list2.
//                                           6 tests: C1=T, C2=T, C3=T, C4=T;
//                                                    C1=T, C2=T, C3=T, C4=F;
//                                                    C1=T, C2=T, C3=F, C4=F;
//                                                    C1=T, C2=F, C3=F, C4=F;
//                                                    C1=F, C2=T, C3=F, C4=F;
//                                                    C1=F, C2=F, C3=F, C4=F;





public class CommonElementsTest {
    private List<String> list1;
    private List<String> list2;
    private CommonElements commonElements;
    //so @BeforeEach ne pominuvaat 2 testovi
    @Before
    public void setUp(){
        commonElements = new CommonElements();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        list1.add("Marce");
        list1.add("Darce");
        list1.add("Zmajce");
        list1.add("Krste");
        list1.add("Ariton");

        list2.add("Sreten");
        list2.add("Pator");
        list2.add("Matej");
        list2.add("Krste");
    }

    //Test1: C1=T
    @Test
    public void testC1_True(){
         assertNotNull(list1);
    }

    //Test2: C1=F
    @Test(expected = NullPointerException.class)
    public void testC1_False(){
        List<String> list = null;
        CommonElements.commonElements(list, list2);
    }

    //Test3: C2=T
    @Test
    public void testC2_True () {
        assertNotNull(list2);
    }

    //Test4: C2=F
    @Test(expected = NullPointerException.class)
    public void testC2_False (){
        List<String> list = null;
        assertNull(CommonElements.commonElements(list1, list));
    }

    //Test5: C1=T, C2=T, C3=T
    @Test
    public void testC1C2C3_True() {
        List<String> list = CommonElements.commonElements(list1, list2);
        assertNotNull(list);
    }

    //Test6: C1=T, C2=T, C3=F;
    @Test(expected = NullPointerException.class)
    public void testC1C2C3_TTF() {
        //This will happen when there are no common elements in list1 and list2
        List<String> newList = CommonElements.commonElements(list1, list2);
        assertNull(newList);
    }

    //Test7: C1=T, C2=F, C3=F;
   @Test(expected = NullPointerException.class)
   public void testC1C2C3_TFF() {
       //This will happen when List2 is null and will throw NullPointerException
       List<String> list = null;
       List<String> newList = CommonElements.commonElements(list1, list);
       assertNull(newList);
   }

    //Test8: C1=F, C2=T, C3=F;
    @Test(expected = NullPointerException.class)
    public void testC1C2C3_FTF(){
        //This will happen when List1 is null and will throw NullPointerException
        List<String> list = null;
        List<String> newList = CommonElements.commonElements(list, list2);
        assertNull(newList);
    }

    //Test9: C1=F, C2=F, C3=F;
    @Test(expected = NullPointerException.class)
    public void testC1C2C3_FFF(){
        //This will happen when both List1 and List2 are null and will throw NullPointerException
        List<String> list = null;
        List<String> llist = null;
        List<String> newList = CommonElements.commonElements(list, llist);
        assertNull(newList);
    }

    //Test10: C1=T, C2=T, C3=T, C4=T;
    @Test
    public void testC1C2C3C4_TTTT(){
        //This will happen when list1 is identical to list2

        assertEquals(list1, list2);
    }

    //Test11: C1=T, C2=T, C3=T, C4=F;
    @Test
    public void testC1C2C3C4_TTTF(){
        List<String> newList = CommonElements.commonElements(list1, list2);
        assertNotNull(newList);
    }

    //Test12: C1=T, C2=T, C3=F, C4=F;
    @Test(expected = NullPointerException.class)
    public void testC1C2C3C4_TTFF(){
        List<String> list = CommonElements.commonElements(list1, list2);
        assertNull(list);
    }

    //Test13: C1=T, C2=F, C3=F, C4=F;
    @Test(expected = NullPointerException.class)
    public void testC1C2C3C4_TFFF(){
        //This will happen when Set2 is null and will throw NullPointerException
        List<String> newList2 = null;
        List<String> newList = CommonElements.commonElements(list1, newList2);
        assertNull(newList);
        assertNotEquals(list1.size(), newList.size());
    }

    //Test14: C1=F, C2=T, C3=F, C4=F;
    @Test(expected = NullPointerException.class)
    public void testC1C2C3C4_FTFF(){
        //This will happen when Set1 is null and will throw NullPointerException
        List<String> newList1 = null;
        List<String> newList = CommonElements.commonElements(newList1, list2);
        assertNull(newList);
        assertNotEquals(list1.size(), newList.size());
    }

    //Test15: C1=F, C2=F, C3=F, C4=F;
    @Test(expected = NullPointerException.class)
    public void testC1C2C3C4_FFFF(){
        //This will happen when both Set1 and Set2 are null and will throw NullPointerException
        List<String> newList1 = null;
        List<String> newList2 = null;
        List<String> newList = CommonElements.commonElements(newList1, newList2);
        assertNull(newList);
        assertNotEquals(list1.size(), newList.size());
    }

}
