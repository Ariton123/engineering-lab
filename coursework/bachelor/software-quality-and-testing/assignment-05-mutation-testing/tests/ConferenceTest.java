package classes;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class ConferenceTest {
    private List<Person> attendees;
    private int cap=3;

    private Conference conference = new Conference(cap);

    public ConferenceTest(){}

    @BeforeEach
    @Test
    public void calculatePriceForOneAffiliateAndOneOtherRole()
    {
        Person p1 = new Person("Ariton", "Verus", Role.STUDENT, 18);
        p1.setAge(21);
        p1.setRole(Role.AFFILIATE);
        p1.setSurname("Verush");

        Person p2 = new Person("Sreten", "Strezovski", Role.OTHER, 22);

        conference.addAttendeeToConference(p1);
        conference.addAttendeeToConference(p2);

        double price = conference.calculateTotalPricePaid();

        Assert.assertEquals(((1 - conference.AFFILIATE_DISCOUNT) * conference.TICKET_PRICE)+conference.TICKET_PRICE, price, 0);
    }

    @Test
    public void calculatePriceForOneAffiliateAndOneFacultyEmployee()
    {
        String name="Ariton";
        String surname = "Verush";
        Role role = Role.OTHER;
        int age=21;

        Person p1 = new Person(name, surname, role, age);
        p1.setRole(Role.AFFILIATE);


        Person p2 = new Person("Sreten", "Strezovski", Role.FACULTY_EMPLOYEE, 35);

        conference.addAttendeeToConference(p1);
        conference.addAttendeeToConference(p2);

        double price = conference.calculateTotalPricePaid();

        Assert.assertEquals(((1 - conference.AFFILIATE_DISCOUNT) * conference.TICKET_PRICE)+(1 - conference.FACULTY_EMPLOYEE_DISCOUNT) *
                conference.TICKET_PRICE, price, 0);
    }

    @Test
    public void checkCapacityAndDoubleIt()
    {

        Person p1 = new Person("Ariton", "Verush", Role.STUDENT, 21);
        Person p2 = new Person("Sreten", "Strezovski", Role.FACULTY_EMPLOYEE, 35);
        Person p3 = new Person("Fyodor", "Dostoevski", Role.ORGANIZER, 47);

        conference.addAttendeeToConference(p1);
        conference.addAttendeeToConference(p2);
        conference.addAttendeeToConference(p3);

        Person p4 = new Person("Ana", "Darieva", Role.OTHER, 25);

        int temp=1;
        if(conference.getCapacity()!=0)
            temp = conference.getCapacity();


        if(!p4.toString().isEmpty() && conference.getCapacity()!=0 && !conference.getAttendees().isEmpty())
        {
            if(conference.addAttendeeToConference(p4))
            {
                //in the function, attendee is added when the capacity doubled is under or equal to 10000 because the condition is always true that way
                //doubleCapacity() is called and attendees can be added until the capacity reaches beyond 10000
                System.out.println("Cant add person" + p4.toString() + " to conference. Capacity is: " + conference.getCapacity() + ".\n Attendees: " + conference.getAttendees());
            }


        }


         Assert.assertEquals(conference.getCapacity(), 2*temp);

    }

    //when the capacity doubled reaches beyond 10000, attendee can't be added
    @Test
    public void canAddAttendee()
    {
        List<Person> attendees;
        int cap=5000;

        Conference conference = new Conference(cap);
        for(int i=0; i<cap; i++)
        {
            Person p = new Person("Ariton"+i, "Verush"+i, Role.STUDENT, 21);
            conference.addAttendeeToConference(p);
        }

        Person temp = new Person("Krste", "Gjorceski", Role.FACULTY_EMPLOYEE, 40);
        boolean flag = conference.addAttendeeToConference(temp);

        Assert.assertEquals(flag, true);


    }

    @Test
    public void cantAddAttendee()
    {
        List<Person> attendees;
        int cap=5001;

        Conference conference = new Conference(cap);
        for(int i=0; i<cap; i++)
        {
            Person p = new Person("Ariton"+i, "Verush"+i, Role.STUDENT, 21);
            conference.addAttendeeToConference(p);
        }

        Person temp = new Person("Krste", "Gjorceski", Role.FACULTY_EMPLOYEE, 40);
        boolean flag = conference.addAttendeeToConference(temp);

        Assert.assertEquals(flag, false);


    }



}
