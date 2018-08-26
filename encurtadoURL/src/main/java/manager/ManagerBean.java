package manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import entity.EncurtadorURL;
import persistence.EncurtadorURLDao;
import util.URLShortener;

@ManagedBean(name = "mb")
@ViewScoped
public class ManagerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private EncurtadorURL encurtadorURL;
	private List<EncurtadorURL> encurtadorURLs;
	
	private String urldig;
	private String urlencur;
	
	

	public String getUrlencur() {
		return urlencur;
	}

	public void setUrlencur(String urlencur) {
		this.urlencur = urlencur;
	}

	public String getUrldig() {
		return urldig;
	}

	public void setUrldig(String urldig) {
		this.urldig = urldig;
	}

	@PostConstruct
	public void init() {
		encurtadorURL = new EncurtadorURL();
	}

	public EncurtadorURL getEncurtadorURL() {
		return encurtadorURL;
	}

	public void setEncurtadorURL(EncurtadorURL encurtadorURL) {
		this.encurtadorURL = encurtadorURL;
	}

	public List<EncurtadorURL> getEncurtadorURLs() {
		encurtadorURLs = new EncurtadorURLDao().findAll();
		return encurtadorURLs;
	}

	public void setEncurtadorURLs(List<EncurtadorURL> encurtadorURLs) {
		this.encurtadorURLs = encurtadorURLs;
	}

	public void gravar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			EncurtadorURLDao dao = new EncurtadorURLDao();
			dao.create(encurtadorURL);
			init();
			fc.addMessage(null, new FacesMessage("Dados Gravados..."));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));
		}
	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new EncurtadorURLDao().delete(encurtadorURL);
			init();
			fc.addMessage(null, new FacesMessage("URL Excluida ... "));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void alterar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			
			new EncurtadorURLDao().update(encurtar(encurtadorURL.getUrl()));
			encurtadorURL = new EncurtadorURL();
			
			fc.addMessage(null, new FacesMessage("Dados Alterados da URL ..."));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}
	
	public EncurtadorURL encurtar(String url) {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			urlencur = new URLShortener().shortenURL(url);
			fc.addMessage(null, new FacesMessage("URL Encurtada ..."));
			encurtadorURL.setUrl(url);
			encurtadorURL.setUrlEncurtada(urlencur);
			System.out.println(new URLShortener().shortenURL(url));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
		return encurtadorURL;
	}
	
	public void encurtar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			urlencur = new URLShortener().shortenURL(urldig);
			fc.addMessage(null, new FacesMessage("Dados Alterados da URL ..."));
			encurtadorURL.setUrl(urldig);
			encurtadorURL.setUrlEncurtada(urlencur);
			System.out.println(new URLShortener().shortenURL(urldig));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}
	
	

}
