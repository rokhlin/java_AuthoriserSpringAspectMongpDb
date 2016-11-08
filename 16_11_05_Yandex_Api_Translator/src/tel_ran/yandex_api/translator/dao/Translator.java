package tel_ran.yandex_api.translator.dao;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import tel_ran.yandex_api.translator.entities.Translation;
import tel_ran.yandex_api.translator.interfaces.ITranslator;
import tel_ran.yandex_api.translator.repo.TranslationsRepository;

public class Translator implements ITranslator {

	@Autowired // 
	TranslationsRepository translationsRepo;
	RestTemplate restTemplate = new RestTemplate();
	private static final String API_KEY = "key=trnsl.1.1.20161105T103928Z.6864446e94a68f27.c3ab919183d27e2479bb5e9935ec1d2c515e3616";
	private static final String WELCOME = "It's my translator.\n"
						+"1-Enter translation command [language] [Text] "
						+"\n  example:ru Hello World!\n"
						+"2- [GetAll] - looking all translations from database\n"
						+"3- [help] - show help\n"
						+"4- [exit] - exit app.\n";
	static String url = "https://translate.yandex.net/api/v1.5/tr/translate?";
	/**
	 * Adding translation results in Mongo DB database if value doesn't contains
	 * in db
	 * 
	 * @param translation
	 * @return
	 */
	private boolean add(Translation translation) {
		if (translation == null || translationsRepo.exists(translation.getTextFrom())
				|| translationsRepo.exists(translation.getTextTo()))
			return false;

		if (translationsRepo.save(translation) != null)
			return true;
		return false;
	}

	/**
	 * Getting all translatons from Mongo DB
	 * @return 
	 */
	public Iterable<Translation> getTranslations() {
		return translationsRepo.findAll();
	}

	
	private Translation get(String textFrom) {
		Translation res = translationsRepo.findOne(textFrom);
		if (res == null)
			try {
				res = translationsRepo.findByTextTo(textFrom).iterator().next();
			} catch (Exception e) {
			}
		return res;
	}

	private boolean remove(String textFrom) {
		if (translationsRepo.exists(textFrom)) {
			translationsRepo.delete(textFrom);
			return true;
		}
		return false;
	}

	private boolean remove(Translation tr) {
		if (translationsRepo.exists(tr.getTextFrom())) {
			translationsRepo.delete(tr);
			return true;
		}
		return false;
	}
	
	/**
	 * At first trying to find from the database results if returns null
	 * translate from Yandex Translator 
	 * @param line
	 * @return
	 */
	private Translation translate(String line) {
		String[] operands = line.split(" ");
		String textFrom = line.substring(operands[0].length()+1);
		
		Translation res = get(textFrom);//Trying to find 

		if(res == null)
			res = translateUsingYandex(textFrom,operands[0].toLowerCase());
		return res;
	}

	/**
	 * Translating text with Yandex-Translator API 
	 * @param textFrom - text for translate
	 * @param langTo - translating language
	 * @return the best compatible text meaning
	 */
	private Translation translateUsingYandex(String textFrom, String langTo) {
		String text = "&text="+textFrom;
		String lang = "&lang="+langTo;
		//System.out.println(url+API_KEY+lang+text);
		String response = restTemplate.postForObject(url+API_KEY+lang+text, text, String.class);
		//System.out.println(response);
		Translation res = new Translation(textFrom,response);
		add(res);
		return res;
	}
	
	private static void display(Object result) {
		String newString=result.toString();
		try {
			newString = new String(result.toString().getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		String res = newString.toString().replace("], ", "], \n");
			System.out.println(res+"\n");
	}
	
	/**
	 * At first trying to find from the database results if returns null
	 * translate from Yandex Translator 
	 * @param line
	 * @return
	 */
	public Translation translateIt(String textFrom, String langTo) {
		Translation res = get(textFrom);//Trying to find 
		if(res == null)
			res = translateUsingYandex(textFrom,langTo);
		return res;
	}

}
