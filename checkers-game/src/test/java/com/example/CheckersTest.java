package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CheckersTest {

    WebDriver driver;

    @Test
    public void checkerTest() {
        driver = new ChromeDriver();
        driver.get("https://www.gamesforthebrain.com/game/checkers/");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("game/checkers/")) {
            System.out.println("Url is open");
        } else {
            System.out.println("Url is not open");
        }
        Actions action = new Actions(driver);

        WebElement dragable1 = driver.findElement(By.cssSelector("img[onclick='didClick(2, 2)']"));
        dragable1.click();
        WebElement dropable1 = driver.findElement(By.cssSelector("img[onclick='didClick(1, 3)']"));
        dropable1.click();
        action.dragAndDrop(dragable1, dropable1).build().perform();

        String expresult1 = "Make a move.";
        String actresult1 = driver.findElement(By.xpath("//p[@id='message']")).getText();
        if (expresult1.equals(actresult1)) {
            System.out.println("I can move to next");
        } else {
            System.out.println("I am not able to move");
        }

        WebElement dragable2 = driver.findElement(By.xpath("//img[@name='space42']"));
        dragable2.click();
        WebElement dropable2 = driver.findElement(By.xpath("//img[@name='space53']"));
        dropable2.click();
        action.dragAndDrop(dragable2, dropable2).build().perform();

        String expresult2 = "Make a move.";
        String actresult2 = driver.findElement(By.xpath("//p[@id='message']")).getText();
        if (expresult2.equals(actresult2)) {
            System.out.println("I can move to next");
        } else {
            System.out.println("I am not able to move");
        }

        WebElement dragable3 = driver.findElement(By.xpath("//img[@name='space31']"));
        dragable3.click();
        WebElement dropable3 = driver.findElement(By.xpath("//img[@name='space42']"));
        dropable3.click();
        action.dragAndDrop(dragable3, dropable3).build().perform();

        String expresult3 = "Make a move.";
        String actresult3 = driver.findElement(By.xpath("//p[@id='message']")).getText();
        if (expresult3.equals(actresult3)) {
            System.out.println("I can move to next");
        } else {
            System.out.println("I am not able to move");
        }

        WebElement dragable4 = driver.findElement(By.cssSelector("img[name='space11']"));
        dragable4.click();
        WebElement dropable4 = driver.findElement(By.cssSelector("img[name='space33']"));
        dropable4.click();
        action.dragAndDrop(dragable4, dropable4).build().perform();

        String expresult4 = "Make a move.";
        String actresult4 = driver.findElement(By.xpath("//p[@id='message']")).getText();
        if (expresult4.equals(actresult4)) {
            System.out.println("I can move to next");
        } else {
            System.out.println("I am not able to move");
        }

        WebElement dragable5 = driver.findElement(By.cssSelector("img[name='space33']"));
        dragable5.click();
        WebElement dropable5 = driver.findElement(By.cssSelector("img[name='space44']"));
        dropable5.click();
        action.dragAndDrop(dragable5, dropable5).build().perform();

        String expresult5 = "Make a move.";
        String actresult5 = driver.findElement(By.xpath("//p[@id='message']")).getText();
        if (expresult5.equals(actresult5)) {
            System.out.println("I can move to next");
        } else {
            System.out.println("I am not able to move");
        }

        driver.findElement(By.xpath("//a[text()='Restart...']")).click();
        String expectedconfirmText = "Select an orange piece to move.";
        String actualconfirmText = driver.findElement(By.xpath("//p[@id='message']")).getText();

        if (expectedconfirmText.equals(actualconfirmText)) {
            System.out.println("Restart has been successfully confirmed");
        } else {
            System.out.println("Restart unsuccessful");
        }
        driver.close();
    }

}
