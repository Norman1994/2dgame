import javax.faces.bean.ManagedProperty;

import Lib.LibService;
import jsf.common.MainBean;

public class bla {

	public static void Main(String[] args)
	{
		LibService libserv = new LibService();
		System.out.println(libserv.get(1).getKodSotr());
	}
}
