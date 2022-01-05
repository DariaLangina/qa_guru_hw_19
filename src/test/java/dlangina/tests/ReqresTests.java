package dlangina.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReqresTests extends TestBase {

  @Test
  @DisplayName("Запрос информации о пользователе по id")
  void getUserInfo() {
    System.out.println("Информация о пользователе id:1 : " + api.getInformationOfUserById(1));
  }

  @Test
  @DisplayName("Запрос кол-ва страниц с пользователями")
  void getAmountPagesWithUsers() {
    System.out.println("Кол-во страниц: " + api.getTotal());
  }

  @Test
  @DisplayName("Добавление нового пользователя по API")
  void createUserForTests() {
    api.createUser("Daria", "R-vision");
  }

  @Test
  @DisplayName("Изменение имени и места работы пользователю")
  void changingNameAndJob() {
    api.updateUser(2, "morpheus", "zion resident");
  }

  @Test
  @DisplayName("Проверка выполнения регистрации пользователя по API")
  void registration() {
    api.registrationByApi();
  }
}
