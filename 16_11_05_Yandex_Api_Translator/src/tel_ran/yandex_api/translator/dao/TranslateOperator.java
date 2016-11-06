package tel_ran.yandex_api.translator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import tel_ran.yandex_api.translator.entities.Translation;
import tel_ran.yandex_api.translator.repo.TranslationsRepository;

public class TranslateOperator {

	@Autowired // 
	TranslationsRepository translationsRepo;

	/**
	 * Adding translation results in Mongo DB database if value doesn't contains
	 * in db
	 * 
	 * @param translation
	 * @return
	 */
	public boolean add(Translation translation) {
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

	
	public Translation get(String textFrom) {
		Translation res = translationsRepo.findOne(textFrom);
		if (res == null)
			try {
				res = translationsRepo.findByTextTo(textFrom).iterator().next();
			} catch (Exception e) {
			}
		return res;
	}

	public boolean remove(String textFrom) {
		if (translationsRepo.exists(textFrom)) {
			translationsRepo.delete(textFrom);
			return true;
		}
		return false;
	}

	public boolean remove(Translation tr) {
		if (translationsRepo.exists(tr.getTextFrom())) {
			translationsRepo.delete(tr);
			return true;
		}
		return false;
	}

}
