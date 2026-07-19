public class VacationPlans {
    private boolean p;
    private boolean q;
    private boolean r;

        Person person =  new Person();
        VacationDates vd = new VacationDates();
        public void makePTrue() {
        person.moneySaved=300;
        person.moneyNeeded=200;
    }

        public void makePFalse() {
        person.moneySaved=200;
        person.moneyNeeded=300;
    }

        public void makeQTrue() {
            vd.datesAreWorkingDays = false;
    }

        public void makeQFalse() {
            vd.datesAreWorkingDays = true;
    }

        public void makeRTrue() {
        person.canUseVacationDays=1;
    }

        public void makeRFalse() {
        person.canUseVacationDays=0;
    }




    public boolean canGoOnVacation(Person person, VacationDates vacationDates)
    {
        boolean canGo = ((person.moneySaved > person.moneyNeeded)&&
                (!vacationDates.datesAreWorkingDays || (person.canUseVacationDays==1)));

        if(canGo)
            System.out.println("The person " + person.name + " " + person.surname + " can go on a vacation");
        else
            System.out.println("The person " + person.name + " " + person.surname + " can't go on a vacation");

        return canGo;
    }


}
