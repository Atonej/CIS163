package campingsystem;

import java.util.Comparator;

public class SortByName implements Comparator<Site> {

public static Comparator<Site> COMPARE_BY_NAME = new Comparator<Site>() {
@Override
public int compare(Site o1, Site o2) {
 return o1.getNameReserving().compareTo(o2.getNameReserving());
}
};

@Override
public int compare(Site o1, Site o2) {
	// TODO Auto-generated method stub
	return 0;
}

}

