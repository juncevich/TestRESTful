package DAO;

import dao.DBManagerMySQL;
import entities.Field;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class DBManagerMySQLTest {
    DBManagerMySQL dbManagerMySQL = new DBManagerMySQL();

    @Test
    public void getConnection() throws Exception {

        Assert.assertNotNull(DBManagerMySQL.createConnection());
    }

    @Test
    public void queryCreate() throws Exception {


        String result = DBManagerMySQL.queryCreate("testDB","test_table_1");
        String result2 = "CREATE TABLE `test_table_1` (\n" +
                "  `test_1` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `test_2` int(11) DEFAULT NULL,\n" +
                "  `test_3` int(11) DEFAULT NULL,\n" +
                "  `test_4` int(11) DEFAULT NULL,\n" +
                "  `test_5` int(11) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`test_1`),\n" +
                "  UNIQUE KEY `test_table_1_test_1_uindex` (`test_1`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=koi8r";
        assertEquals("Testing value",result, result2);
    }

    @Test
    public void querySelect() throws Exception {
        List<Field> fieldList= DBManagerMySQL.querySelect("testDB","test_table_1");
        assertEquals(fieldList.get(0).getName(),"test_1");
        assertEquals(fieldList.get(0).getPrime(), true);
    }

    @Test
    public void createConnection() throws Exception {

    }

    @Test
    public void isCorrectTable() throws Exception {
        assertEquals(DBManagerMySQL.isCorrectTable("testDB","test_table_1"), true);
    }

    @Test
    public void isCorrectTableWithIncorrectDb() throws Exception {
        assertEquals(DBManagerMySQL.isCorrectTable("notTestDB","test_table_1"), false);
    }

    @Test
    public void isCorrectTableWithIncorrectTable() throws Exception {
        assertEquals(DBManagerMySQL.isCorrectTable("testDB","not_test_table_1"), false);
    }
}