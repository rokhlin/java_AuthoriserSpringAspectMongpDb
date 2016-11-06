package tel_ran.yandex_api.translator.repo;

import org.springframework.data.repository.CrudRepository;

import tel_ran.yandex_api.translator.entities.Translation;

public interface TranslationsRepository  extends CrudRepository<Translation, String>  {
	Iterable<Translation> findByTextTo(String textTo);
}
