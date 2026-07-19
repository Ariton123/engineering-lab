import java.util.ArrayList;
import java.util.List;

public class CommonElements {

    public static List<String> commonElements(List<String> list1, List<String> list2)
    {
        List<String> list = new ArrayList<>();

        if(list1==null || list2==null)
            throw new NullPointerException();

        if(list1.equals(list2))
            return list1;

        for(int i=0; i<list1.size(); i++)
        {
            for(int j=0; j<list2.size(); j++)
            {
                if(list1.get(i).equals(list2.get(j)));
                {
                    list.add(list1.get(i));
                }
            }
        }
        return list;

    }

}