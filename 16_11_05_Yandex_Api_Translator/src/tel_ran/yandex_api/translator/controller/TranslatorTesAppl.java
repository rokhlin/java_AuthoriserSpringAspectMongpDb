package tel_ran.yandex_api.translator.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.Format;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.support.StringMultipartFileEditor;

import tel_ran.yandex_api.translator.dao.TranslateOperator;
import tel_ran.yandex_api.translator.entities.Translation;

public class TranslatorTesAppl {
	private static final String API_KEY = "key=trnsl.1.1.20161105T103928Z.6864446e94a68f27.c3ab919183d27e2479bb5e9935ec1d2c515e3616";
	private static final String WELCOME = "It's my translator.\n"
						+"1-Enter translation command [language] [Text] "
						+"\n  example:ru Hello World!\n"
						+"2- [GetAll] - looking all translations from database\n"
						+"3- [help] - show help\n"
						+"4- [exit] - exit app.\n";
	static RestTemplate restTemplate = new RestTemplate();
	static String url = "https://translate.yandex.net/api/v1.5/tr/translate?";
	static TranslateOperator db;
	public static void main(String[] args) {
		
		try(AbstractApplicationContext ctx =new FileSystemXmlApplicationContext("beans.xml");	
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));){
			db = ctx.getBean(TranslateOperator.class);
			System.out.println(WELCOME);
			
		while(true) {
			System.out.println("Enter your comand or [help]:");
			String line = console.readLine().trim();
			Object result = null;
			if(line == null || line.equalsIgnoreCase("exit"))
				break;
			
			String[] operands = line.split(" ");
			
			if(operands[0].toLowerCase().equals("getall")
					||operands[0].toLowerCase().equals("all")
						||operands[0].toLowerCase().equals("list")) {
				result = db.getTranslations();	
			}
			else if(line!=""&&operands[0].length()==2) {
				result = translate(line);
			}
			else {
				System.out.println("command error, try again.");
			}
			display(result);	
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
	private static Translation translate(String line) {
		String[] operands = line.split(" ");
		String textFrom = line.substring(operands[0].length()+1);
		Translation res =db.get(textFrom);//Trying to find 

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
	private static Translation translateUsingYandex(String textFrom, String langTo) {
		String text = "&text="+textFrom;
		String lang = "&lang="+langTo;
		//System.out.println(url+API_KEY+lang+text);
		String response = restTemplate.postForObject(url+API_KEY+lang+text, text, String.class);
		//System.out.println(response);
		Translation res = new Translation(textFrom,response);
		db.add(res);
		return res;
	}



}
