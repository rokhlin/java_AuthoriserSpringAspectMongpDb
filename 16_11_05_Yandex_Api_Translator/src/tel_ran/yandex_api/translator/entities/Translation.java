package tel_ran.yandex_api.translator.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "translation-colections")
public class Translation {

	@Id
	String textFrom;
	String textTo;
	String langFrom;
	String langTo;
	
	public Translation(String lang, String langTo, String textTo) {
		super();
		parseLangs(lang);
		
		this.langTo = langTo;
		this.textTo = textTo;
	}
	
	public Translation() {
	}
	
	public Translation(String textFrom, String response) {
		super();
		parseLangs(getLangs(response));
		parceTextTo(response);
		this.textFrom = textFrom;
		
	}
	
	private void parceTextTo(String response) {
		int size = "<text>".toCharArray().length;
		int indexFrom = response.lastIndexOf("<text>")+size;
		int indexTo = response.indexOf("</text>");
		textTo = getValue(indexFrom,indexTo,response);
		
	}

	private static String getValue(int indexFrom, int indexTo, String response) {
		return response.substring(indexFrom, indexTo);
		
	}

	public static String[] getLangsParsed(String response) {
		String langs = getLangs(response);
		return langs.split("-");
	}
	private void parseLangs(String lang) {
		String[] langs = lang.trim().split("-");
		langFrom = langs[0];
		langTo = langs[1];
	}
	
	
	
	public String getLangFrom() {
		return langFrom;
	}
	public void setLangFrom(String langFrom) {
		this.langFrom = langFrom;
	}
	public String getTextFrom() {
		return textFrom;
	}
	public void setTextFrom(String textFrom) {
		this.textFrom = textFrom;
	}
	public String getLangTo() {
		return langTo;
	}
	public void setLangTo(String langTo) {
		this.langTo = langTo;
	}
	public String getTextTo() {
		return textTo;
	}
	public void setTextTo(String textTo) {
		this.textTo = textTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((textFrom == null) ? 0 : textFrom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Translation other = (Translation) obj;
		if (textFrom == null) {
			if (other.textFrom != null)
				return false;
		} else if (!textFrom.equals(other.textFrom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return langFrom + "-" + langTo +": ["+ textFrom+" - " + textTo + "]";
	}

	public static String getLangs(String response) {
		int size = "code=\"200\" lang=\"".toCharArray().length;
		int indexFrom = response.lastIndexOf("code=\"200\" lang=\"")+size;
		int indexTo = response.indexOf("\"><text>");
		return getValue(indexFrom,indexTo,response);
	}
	
	
	
}
