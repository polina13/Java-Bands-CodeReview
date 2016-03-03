import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Rule;

public class TaskTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Task.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionAretheSame() {
    Task firstTask = new Task("Mow the lawn", false);
    Task secondTask = new Task("Mow the lawn", false);
    assertTrue(firstTask.equals(secondTask));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Task myTask = new Task("Mow the lawn", false);
    myTask.save();
    Task savedTask = Task.all().get(0);
    assertTrue(savedTask.equals(myTask));
  }

  @Test
  public void save_assignsIdToObject() {
    Task myTask = new Task("Mow the lawn", false);
    myTask.save();
    Task savedTask = Task.all().get(0);
    assertEquals(myTask.getId(), savedTask.getId());
  }

  @Test
  public void find_findsTaskInDatabase_true() {
    Task myTask = new Task("Mow the lawn", false);
    myTask.save();
    Task savedTask = Task.find(myTask.getId());
    assertTrue(myTask.equals(savedTask));
  }

  @Test
  public void addCategory_addsCategoryToTask() {
    Category myCategory = new Category("Household chores");
    myCategory.save();

    Task myTask = new Task("Mow the lawn", false);
    myTask.save();

    myTask.addCategory(myCategory);
    Category savedCategory = myTask.getCategories().get(0);
    assertTrue(myCategory.equals(savedCategory));
}

  @Test
  public void getCategories_returnsAllCategories_ArrayList() {
    Category myCategory = new Category("Household chores");
    myCategory.save();

    Task myTask = new Task("Mow the lawn", false);
    myTask.save();

    myTask.addCategory(myCategory);
    List savedCategories = myTask.getCategories();
    assertEquals(savedCategories.size(), 1);
}

  @Test
  public void delete_deletesAllTasksAndListsAssoicationes() {
    Category myCategory = new Category("Household chores");
    myCategory.save();

    Task myTask = new Task("Mow the lawn", false);
    myTask.save();

    myTask.addCategory(myCategory);
    myTask.delete();
    assertEquals(myCategory.getTasks().size(), 0);
}

  @Test
  public void completeTask_checksThatTaskIsCompleted() {
    Task myTask = new Task("Mow the lawn", false);
    myTask.completeTask();
    assertEquals(myTask.getStatus(), true);
}

  // @Test
  // public void save_savesCategoryIdIntoDB_true() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn", myCategory.getId());
  //   myTask.save();
  //   Task savedTask = Task.find(myTask.getId());
  //   assertEquals(savedTask.getCategoryId(), myCategory.getId());
  // }

}
