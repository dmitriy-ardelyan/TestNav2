package trash;

public class SeleniumQuestions {
    private Question[] seleniumQuestions;

    SeleniumQuestions(){
        Question q1 = new Question();

        q1.setQuestion("Which command we should use to view a page via an instance of a web driver?");
        String[] q1answers = {"driverChrome.get(\"http://google.com\");",
                            "driverChrome.set(\"http://google.com\");",
                            "driverChrome.put(\"http://google.com\");",
                            "driverChrome.run(\"http://google.com\");"};
        q1.setAnswers(q1answers);
        q1.setInfo("<html>To view a page via an instance of a web driver we should use command:<br>" +
                " driverChrome.get(\"http://google.com\");</html>");

        Question q2 = new Question();
        q2.setQuestion("<html>Before using an instance of a driver we need to setup it.<br>Please select proper setup string.</html>");
        String[] q2answers = {"System.setProperty(\"webdriver.chrome.driver\", \"C:\\\\chromedriver\\\\chromedriver_win32\\\\chromedriver.exe\");",
        "System.setInstance(\"webdriver.chrome.driver\", \"C:\\\\chromedriver\\\\chromedriver_win32\\\\chromedriver.exe\");",
        "Driver.setVariable(\"webdriver.chrome.driver\", \"C:\\\\chromedriver\\\\chromedriver_win32\\\\chromedriver.exe\");",
        "WebDriver.setPath(\"webdriver.chrome.driver\", \"C:\\\\chromedriver\\\\chromedriver_win32\\\\chromedriver.exe\");"};
        q2.setAnswers(q2answers);
        q2.setInfo("<html>Starting from selenium 3 we need special selenium driver; for example we set a property and create a driver obj:" +
                "<br>System.setProperty(\"webdriver.chrome.driver\", \"C:\\\\chromedriver\\\\chromedriver_win32\\\\chromedriver.exe\");<br>" +
                "WebDriver driverChrome = new ChromeDriver();</html>");

        Question q3 = new Question();

        q3.setQuestion("How can we maximize browser window?");
        String[] q3answers = {"driver.manage().window().maximize();","driver.window().setMaximumSize();","driver.manage().maximumSize();",
        "driver.maximize().window();"};
        q3.setAnswers(q3answers);
        q3.setInfo("<html>To maximize window:<br>\"driver.manage().window().maximize();</html>");

        this.seleniumQuestions = new Question[3];
        this.seleniumQuestions[0] = q1;
        this.seleniumQuestions[1] = q2;
        this.seleniumQuestions[2] = q3;
    }

    public Question[] getSeleniumQuestions(){
        return this.seleniumQuestions;
    }

    public int getAmountOfQuestions(){
        return  this.seleniumQuestions.length;
    }
}
