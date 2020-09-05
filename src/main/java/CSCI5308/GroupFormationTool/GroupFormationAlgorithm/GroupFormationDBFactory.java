package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

public class GroupFormationDBFactory {
	private static GroupFormationDBFactory gFormationDbFactory = null;

	public static GroupFormationDBFactory FactorySingleton() {
		if (gFormationDbFactory == null) {
			gFormationDbFactory = new GroupFormationDBFactory();
		}
		return gFormationDbFactory;
	}

	public IGroupAlgorithmDB createGroupFormationDB() {
		return new GroupAlgorithmDB();
	}
}
