package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="encurtadorURL")
public class EncurtadorURL implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer urlId;
	
	@Column(nullable = false)
	@Pattern(regexp="^http:\\/\\/|(www\\.)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$/", message="URL invalido forado Padrao")
	private String url;
	
	@Column(nullable = false)
	private String urlEncurtada;
	
	public EncurtadorURL() {
	}

	public EncurtadorURL(Integer urlId, String url, String urlEncurtada) {
		this.urlId = urlId;
		this.url = url;
		this.urlEncurtada = urlEncurtada;
	}

	@Override
	public String toString() {
		return "EncurtadorURL [urlId=" + urlId + ", url=" + url + ", urlEncurtada=" + urlEncurtada + "]";
	}

	public Integer getUrlId() {
		return urlId;
	}

	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlEncurtada() {
		return urlEncurtada;
	}

	public void setUrlEncurtada(String urlEncurtada) {
		this.urlEncurtada = urlEncurtada;
	}

}
