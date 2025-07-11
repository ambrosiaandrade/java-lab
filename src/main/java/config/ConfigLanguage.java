package config;

import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

/**
 * `ConfigLanguage` é uma classe de configuração estática responsável por determinar
 * e gerenciar a **localidade (locale)** da aplicação. Ela define o idioma e a região
 * padrão que serão utilizados para a internacionalização (i18n) de mensagens e
 * formatações em todo o sistema.
 *
 * A localidade é carregada **automaticamente na inicialização da classe**
 * (via bloco estático) a partir de um arquivo de configuração `.env`.
 *
 * **Mecanismo de Configuração:**
 * - A classe tenta ler a propriedade `app.profile` do arquivo `.env`.
 * - Se `app.profile` for "pt", a localidade é definida como `pt-BR` (Português do Brasil).
 * - Para qualquer outro valor (incluindo "en" ou se a propriedade estiver ausente),
 * a localidade padrão é `en-US` (Inglês dos EUA).
 * - Em caso de falha ao carregar o arquivo `.env` ou se ocorrer qualquer outra exceção,
 * a localidade é automaticamente definida como `en-US`, e uma mensagem de erro
 * é impressa no console.
 *
 * **Uso:**
 * - Outras partes da aplicação (como `MessageProvider`) podem consultar a localidade
 * atual através do método estático `getLocale()`, garantindo que as mensagens
 * e formatações sejam consistentes com a configuração de idioma desejada.
 */
public class ConfigLanguage {

    private static final String CONFIG_FILE = ".env";
    private static Locale locale;

    static {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            String profile = properties.getProperty("app.profile", "en").toLowerCase();
            switch (profile) {
                case "pt":
                    locale = Locale.forLanguageTag("pt-BR");
                    break;
                case "en":
                default:
                    locale = Locale.forLanguageTag("en-US");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            locale = Locale.forLanguageTag("en-US");
            System.out.println("Failed to load configuration file. Defaulting to: " + locale);
        }
    }

    public static Locale getLocale() {
        return locale;
    }

}
