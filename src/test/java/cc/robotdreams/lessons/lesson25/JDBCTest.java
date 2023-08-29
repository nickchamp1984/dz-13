package cc.robotdreams.lessons.lesson25;

import cc.robotdreams.automation.DB;
import cc.robotdreams.automation.Session;
import cc.robotdreams.automation.base.BaseTestNG;
import cc.robotdreams.automation.db.Category;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCTest extends BaseTestNG
{

    public static final String selectRequest = "SELECT * FROM oc_category WHERE parent_id=?;";
    public static final String insertRequest = "";
    public static final String updateRequest = "";
    public static final String deleteRequest = "";
    public static final Integer id = 30;
    public static final Integer parameter = 1;
    public static final String columnLabel = "category_id";

    @Test
    public void test() {
        List<Category.Item> topCategories = DB.category.getTopCategories();
        for (Category.Item category : topCategories) {
            System.out.println(category.id + " - " + category.name);
        }

        List<Category.Item> allCategories = DB.category.getAllCategories();
        for (Category.Item child : allCategories)
            printItem("-", child);

        executeQuery(selectRequest, 1, 25, "category_id");

    }



    @Test
    void insertNewItem() {
        queryRequest(insertRequest);
    }

    @Test
    void selectNewItem() {
        queryRequest(selectRequest);
    }

    @Test
    void updateNewItem() {
        queryRequest(updateRequest);
    }
    @Test
    void deleteNewItem() {
        queryRequest(deleteRequest);
    }

    @Step
    private static void executeQuery(String request, Integer parameter, Integer id, String columnLabel) {
        try {
            PreparedStatement statement = Session.get().mysql().preparedStatement(request);
            statement.setInt(parameter, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                System.out.println(resultSet.getInt(columnLabel));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Step
    private static void queryRequest(String method) {
        executeQuery(method, parameter, id, columnLabel);
    }

    private void printItem(String prefix, Category.Item item) {
        System.out.println(prefix + " " + item.name);
        for (Category.Item child : item.childs())
            printItem(prefix + "-", child);
    }
}
