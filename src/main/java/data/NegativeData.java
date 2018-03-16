package data;

import org.testng.annotations.DataProvider;

/**
 * Created by Artem on 30.11.2016.
 */
public class NegativeData {

    @DataProvider(name = "lessSixCharacter")
    public static Object[][] lessSixCharacter() {
        return new Object[][]{
                {"user@gmail.com", "12345", "Пожалуйста, введите как минимум 6 символов"},
                {"user@gmail.com", " 12345", "Пожалуйста, введите как минимум 6 символов"},
                {"user@gmail.com", "12345 ", "Пожалуйста, введите как минимум 6 символов"},
                {"user@gmail.com", " 12 45  ", "Пожалуйста, введите как минимум 6 символов"},
                {"user@gmail.com", " 12 45 ", "Пожалуйста, введите как минимум 6 символов"},
                {"user@gmail.com", " qwert", "Пожалуйста, введите как минимум 6 символов"},
                {"user@gmail.com", "qwert ", "Пожалуйста, введите как минимум 6 символов"},
                {"user@gmail.com", "qwert", "Пожалуйста, введите как минимум 6 символов"}

        };
    }
    @DataProvider(name = "emptyPassword")
    public static Object[][] emptyPassword() {
        return new Object[][]{
                {"user@gmail.com", "", "Поле обязательно для заполнения"},
                {"user@gmail.com", "  ", "Поле обязательно для заполнения"}

        };
    }
}
