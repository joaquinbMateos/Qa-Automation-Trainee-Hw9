package Homework09.PageObject;

import Homework09.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SurveyPage extends BasePage {
    //locators:
    By survey_button = By.xpath("//a[contains(text(),'Take our survey')]");
    By questions = By.xpath("//div[contains(@class,' question-single-choice-radio qn question vertical question-required')]");
    By radio_buttons = By.xpath("//input[contains(@class,'radio-button-input ')]");

    By finish_button = By.xpath("//button[contains(@class,'btn small done-button survey-page-button user-generated notranslate')]");

    By feedback_message = By.xpath("//div[contains(@class,'thanks-message question-body-font-theme user-generated clearfix')]");

    By radio_button1 = By.xpath("//div[contains(@data-qnumber,1)]//child::input");
    By radio_button2 = By.xpath("//div[contains(@data-qnumber,2)]//child::input");
    By radio_button3 = By.xpath("//div[contains(@data-qnumber,3)]//child::input");
    //Constructor:
    public SurveyPage(WebDriver driver) {
        super(driver);
    }

    public WebElement findSurvey(){
        return find(survey_button);
    }

    public WebElement getRadioButton() { return find(radio_buttons);}

    public List<WebElement> getQuestionsList(){
        List<WebElement> elements = driver.findElements(questions);
        return elements;
    }

    public List<List> getListOfAnswers(){
        List<WebElement> answers1 = driver.findElements(radio_button1);
        List<WebElement> answers2 = driver.findElements(radio_button2);
        List<WebElement> answers3 = driver.findElements(radio_button3);

        List<List> allAnswers = new ArrayList<List>();
        allAnswers.add(answers1);
        allAnswers.add(answers2);
        allAnswers.add(answers3);

        return allAnswers;
    }
    public void finishButton(){
        click(finish_button);
    }
    public WebElement message(){
        return find(feedback_message);
    }

    public void clickRadioButton(){
        click(radio_buttons);
    }
}
