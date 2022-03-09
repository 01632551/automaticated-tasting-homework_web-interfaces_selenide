package ru.netology.delivery;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PositiveCardDeliveryNameTest {

    public String needDate () {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(7);
        int dayOfMonth = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        String rightDate;
        if (dayOfMonth < 10 && month < 10) {
            return String.format("0%d.0%d.%d", dayOfMonth, month, year);
        }
        if (dayOfMonth < 10 && month >=10) {
            return String.format("0%d.%d.%d", dayOfMonth, month, year);
        }
        if (dayOfMonth >= 10 && month < 10) {
            return String.format("%d.0%d.%d", dayOfMonth, month, year);
        } else {
            return String.format("%d.%d.%d", dayOfMonth, month, year);
        }
    }

    // (SMP = simple, CMP = compound)

// TEST 1: SMP SURNAME & NAME
    @Test
    public void shouldLoadFormV1 (){

        open("http://localhost:7777/");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(needDate());
        $("[data-test-id=name] input").setValue("Крючкова Софья");
        $("[data-test-id=phone] input").setValue("+79654321789");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
    }

// TEST 2: CMP SURNAME & SMP NAME
    @Test
    public void shouldLoadFormV2 (){

        open("http://localhost:7777/");
        $("[data-test-id=city] input").setValue("Майкоп");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(needDate());
        $("[data-test-id=name] input").setValue("Немирович-Данченко Даниил");
        $("[data-test-id=phone] input").setValue("+79159635457");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
    }

// TEST 3: SMP SURNAME & CMP NAME
    @Test
    public void shouldLoadFormV3 (){

        open("http://localhost:7777/");
        $("[data-test-id=city] input").setValue("Йошкар-Ола");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(needDate());
        $("[data-test-id=name] input").setValue("Ефанова Анна-Мария");
        $("[data-test-id=phone] input").setValue("+79369704560");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
    }

// TEST 4: CMP SURNAME & СMP NAME
    @Test
    public void shouldLoadFormV4 (){

        open("http://localhost:7777/");
        $("[data-test-id=city] input").setValue("Кызыл");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(needDate());
        $("[data-test-id=name] input").setValue("Иванова-Зыкова Ирина-Любовь");
        $("[data-test-id=phone] input").setValue("+78005553535");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
    }
}
