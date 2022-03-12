package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PositiveCardDeliveryDateTest {

    String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

    // TEST 1: DATE ON THE BOARD OF ALLOWABLE VALUE (+3 days after delivery date)
    @Test
    public void shouldLoadFormV1() {

        int days = 3;

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Майкоп");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(generateDate(days));
        $("[data-test-id=name] input").setValue("Немирович-Данченко Даниил");
        $("[data-test-id=phone] input").setValue("+79654321789");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(Condition.ownText(generateDate(days)));
    }

    // TEST 2: A LONG TIME AFTER DATE OF DELIVERY (+ one year after delivery date)
    @Test
    public void shouldLoadFormV2() {

        int days = 365;

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Улан-Удэ");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(generateDate(days));
        $("[data-test-id=name] input").setValue("Крылов ИИ");
        $("[data-test-id=phone] input").setValue("+79654321789");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(Condition.ownText(generateDate(days)));
    }

    // TEST 3: AVERAGE TIME AFTER A DATE OF DELIVERY (+ 2 month after delivery date)
    @Test
    public void shouldLoadFormV3() {

        int days = 61;

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(generateDate(days));
        $("[data-test-id=name] input").setValue("Крючкова Софья");
        $("[data-test-id=phone] input").setValue("+79654321789");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(Condition.ownText(generateDate(days)));
    }
}
