package config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * `MessageProvider` é uma classe utilitária estática fundamental para a
 * **internacionalização (i18n)** de aplicações Java. Ela centraliza e gerencia
 * a recuperação de mensagens e textos localizados a partir de _bundles_ de
 * recursos,
 * adaptando o conteúdo para diferentes idiomas e regiões com base na localidade
 * atual e em um nome de módulo configurável.
 *
 * Esta classe é crucial para garantir que todo o texto exibido ao usuário —
 * como rótulos, mensagens de feedback, prompts e mensagens de erro — possa ser
 * facilmente traduzido sem que seja necessário modificar a lógica de negócio
 * principal
 * da aplicação.
 *
 * **Principais Responsabilidades:**
 * - **Recuperação de Mensagens (`get(String key)`):** Fornece o método para
 * obter
 * uma string localizada usando uma `chave` específica. O método determina
 * qual `ResourceBundle` utilizar com base no nome do módulo (`moduleName`)
 * e na `Locale` atualmente configurada via `ConfigLanguage.getLocale()`.
 * - **Gerenciamento do Nome do Módulo (`setModuleName`, `getModuleName`):**
 * Permite
 * definir e recuperar o nome do módulo em tempo de execução. Esse nome é vital
 * para que o `MessageProvider` possa localizar corretamente o _bundle_ de
 * recursos
 * apropriado (e.g., `seuNomeModulo.i18n.messages`).
 * - **Integração com Localidade:** Colabora com a classe `ConfigLanguage` para
 * assegurar que as mensagens sejam sempre apresentadas no idioma e formato
 * preferidos pelo usuário.
 *
 * Ao padronizar o acesso às mensagens da aplicação, `MessageProvider` promove
 * a reutilização de código, simplifica enormemente os esforços de localização
 * e garante a consistência de todo o conteúdo textual da aplicação.
 */
public class MessageProvider {

    private static ResourceBundle bundle;
    private static String moduleName;

    public static String get(String key) {
        Locale locale = ConfigLanguage.getLocale();
        String baseName = moduleName + ".i18n.messages"; // ex: "src.streams.i18n.messages"
        bundle = ResourceBundle.getBundle(baseName, locale);
        return bundle.getString(key);
    }

    public static void setModuleName(String moduleName) {
        MessageProvider.moduleName = moduleName;
    }

    public static String getModuleName() {
        return moduleName;
    }

}
