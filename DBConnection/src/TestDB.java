import DB.DBManager;

public class TestDB {

	public static void main(String[] args) {
		
		DBManager dbManager = new DBManager();
		
		dbManager.createTable();

		dbManager.insertDB(1, "name1", "surname1", "123");
		dbManager.insertDB(2, "name2", "surname2", "1234");
		dbManager.commitDB();
		dbManager.printDB();

		dbManager.updateDB("name1", "222");
		dbManager.commitDB();
		dbManager.printDB();

		dbManager.deleteDB(1);
		dbManager.commitDB();
		dbManager.printDB();
		
		dbManager.closeDB();
	}

}
