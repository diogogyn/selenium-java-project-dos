package br.com.dos.leilao.login;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    public static void beforeAll(){

    }

    public void beforeEach(){

    }

    public void fechar() {
        this.browser.quit();
    }

    public void preencheFormularioLogin(String username, String password) {
        this.browser.findElement(By.id("username")).sendKeys(username);
        this.browser.findElement(By.id("password")).sendKeys(password);
    }

    public void efetuaLogin() {
        this.browser.findElement(By.id("login-form")).submit();
    }

    public boolean isPaginaLogin() {
        return this.browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public boolean isPaginaLoginComDadosInvalidos() {
        return this.browser.getCurrentUrl().equals(URL_LOGIN+"?error");
    }

    public Object getNomeUsuarioLogado() {
        try {
            return this.browser.findElement(By.id("usuario-logado")).getText();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public void navegaParaPaginaDeLance() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contemTexto(String texto) {
        return this.browser.getPageSource().contains(texto);
    }

}
