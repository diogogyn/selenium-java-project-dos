package br.com.dos.leilao.login;


import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private LoginPage paginaLogin;

    @BeforeEach
    public void beforeEach(){
        this.paginaLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
        this.paginaLogin.fechar();
    }
    @Test
    public void deveriaEfetuarLoginComDados(){
        this.paginaLogin.preencheFormularioLogin("fulano", "pass");
        this.paginaLogin.efetuaLogin();
        Assert.assertFalse(this.paginaLogin.isPaginaLogin());
        Assert.assertEquals("fulano", this.paginaLogin.getNomeUsuarioLogado());

    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos(){
        this.paginaLogin.preencheFormularioLogin("invalido", "invalido");
        this.paginaLogin.efetuaLogin();

        Assert.assertTrue(this.paginaLogin.isPaginaLoginComDadosInvalidos());
        Assert.assertNull(this.paginaLogin.getNomeUsuarioLogado());
        Assert.assertTrue(this.paginaLogin.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemLogar(){
        this.paginaLogin.navegaParaPaginaDeLance();
        Assert.assertTrue(this.paginaLogin.isPaginaLogin());
        Assert.assertFalse(this.paginaLogin.contemTexto("Dados do Leilão"));
    }
}
