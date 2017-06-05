import java.util.Comparator;

public class AccountNameComparator implements Comparator<Account> {

	@Override
	public int compare(Account arg0, Account arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}

}