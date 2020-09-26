package jsf.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

//@ManagedBean (name="Main") // определение managed bean и его наименования поэтому мы его не описываем в faces-config.xml
//@SessionScoped             // аннотация определения времени жизни - managed bean сессионной
public class MainBean
{
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
	}

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}
 
    /* Метод простейшей авторизации.
     * Выполняется проверка имени и пароля пользователя. 
     * Результат проверки - наименование страницы перехода
     */
    public String checkLogin(){
        if(login.equalsIgnoreCase("alex") && password.equalsIgnoreCase("qwerty")){
	        return "loginsuccess?faces-redirect=true";
        } else {
	        return "loginfailed?faces-redirect=true";
	    }
    }
}
