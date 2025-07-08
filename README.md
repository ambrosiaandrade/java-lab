# Java Lab

Repository with practical and didactic examples of Java language features, focused on fundamentals without using external frameworks. Ideal for those who want to learn Java in practice, understanding basic and intermediate concepts.

> 🇧🇷 Este projeto também está disponível em Português: [README.pt-BR.md](README.pt-BR.md)

---

## 🌐 Internationalization (i18n)

This project supports multiple languages. To switch between English and Portuguese, just change the `.env` file:

```sh
app.profile=en   # For English
# app.profile=pt  # For Portuguese
```

### 🧩 Helper classes for internationalization

- **[CustomPrint](src/main/java/config/CustomPrint.java)** – Customized console printing with color and formatting support.
- **[MessageProvider](src/main/java/config/MessageProvider.java)** – Loads and manages messages according to the language.
- **[ConfigLanguage](src/main/java/config/ConfigLanguage.java)** – Global settings related to language and localization.

<details>
<summary><b>Images</b></summary>

![stream-pt](./images/stream-pt.png) ![stream-en](./images/stream-en.png)

</details>

---

## 🚀 Technologies Used

[![Java][java-shield]][java-url] [![Internationalization][i18n-shield]][i18n-url] [![Build with Gradle][gradle-shield]][gradle-url]

---

## 📚 Content and Structure

| Status | Topic                      | README                                                                                             | Cheat Sheet                               | Exercises                                     |
| ------ | -------------------------- | -------------------------------------------------------------------------------------------------- | ----------------------------------------- | --------------------------------------------- |
| ☑️     | **Streams**                | [Portuguese 🇧🇷](docs/streams/README.pt-BR.md) / [English 🇺🇸](docs/streams/README.en-US.md)         | [🇧🇷](docs/streams/cheatcode.pt-BR.md)     | [🇧🇷 / 🇺🇸](docs/streams/exercise.en-pt.md)     |
| ☑️     | **Records**                | [Portuguese 🇧🇷](docs/records/README.pt-BR.md) / [English 🇺🇸](docs/records/README.en-US.md)         | [🇧🇷](docs/records/cheatcode.pt-BR.md)     | [🇧🇷 / 🇺🇸](docs/records/exercise.en-pt.md)     |
| 🚧     | **Optionals**              | [Portuguese 🇧🇷](docs/optionals/README.pt-BR.md) / [English 🇺🇸](docs/optionals/README.en-US.md)     | [🇧🇷](docs/optionals/cheatcode.pt-BR.md)   | [🇧🇷 / 🇺🇸](docs/optionals/exercise.en-pt.md)   |
| 🚧     | **Lambdas**                | [Portuguese 🇧🇷](docs/lambdas/README.pt-BR.md) / [English 🇺🇸](docs/lambdas/README.en-US.md)         | [🇧🇷](docs/lambdas/cheatcode.pt-BR.md)     | [🇧🇷 / 🇺🇸](docs/lambdas/exercise.en-pt.md)     |
| 🚧     | **Collections**            | [Portuguese 🇧🇷](docs/collections/README.pt-BR.md) / [English 🇺🇸](docs/collections/README.en-US.md) | [🇧🇷](docs/collections/cheatcode.pt-BR.md) | [🇧🇷 / 🇺🇸](docs/collections/exercise.en-pt.md) |
| 🚧     | **Date time**              | [Portuguese 🇧🇷](docs/date_time/README.pt-BR.md) / [English 🇺🇸](docs/date_time/README.en-US.md)     | [🇧🇷](docs/date_time/cheatcode.pt-BR.md)   | [🇧🇷 / 🇺🇸](docs/date_time/exercise.en-pt.md)   |
| 🚧     | **Concurrency**            | [Portuguese 🇧🇷](docs/concurrency/README.pt-BR.md) / [English 🇺🇸](docs/concurrency/README.en-US.md) | [🇧🇷](docs/concurrency/cheatcode.pt-BR.md) | [🇧🇷 / 🇺🇸](docs/concurrency/exercise.en-pt.md) |
| 🚧     | **Generics**               | [Portuguese 🇧🇷](docs/generics/README.pt-BR.md) / [English 🇺🇸](docs/generics/README.en-US.md)       | [🇧🇷](docs/generics/cheatcode.pt-BR.md)    | [🇧🇷 / 🇺🇸](docs/generics/exercise.en-pt.md)    |
| 🚧     | **IO NIO**                 | [Portuguese 🇧🇷](docs/io_nio/README.pt-BR.md) / [English 🇺🇸](docs/io_nio/README.en-US.md)           | [🇧🇷](docs/io_nio/cheatcode.pt-BR.md)      | [🇧🇷 / 🇺🇸](docs/io_nio/exercise.en-pt.md)      |
| 🚧     | **HttpClient**             | [Portuguese 🇧🇷](docs/http_client/README.pt-BR.md) / [English 🇺🇸](docs/http_client/README.en-US.md) | [🇧🇷](docs/http_client/cheatcode.pt-BR.md) | [🇧🇷 / 🇺🇸](docs/http_client/exercise.en-pt.md) |
| 🚧     | **JDBC**                   | [Portuguese 🇧🇷](docs/jdbc/README.pt-BR.md) / [English 🇺🇸](docs/jdbc/README.en-US.md)               | [🇧🇷](docs/jdbc/cheatcode.pt-BR.md)        | [🇧🇷 / 🇺🇸](docs/jdbc/exercise.en-pt.md)        |
| 🚧     | **Functional Programming** | [Portuguese 🇧🇷](docs/func_prog/README.pt-BR.md) / [English 🇺🇸](docs/func_prog/README.en-US.md)     | [🇧🇷](docs/func_prog/cheatcode.pt-BR.md)   | [🇧🇷 / 🇺🇸](docs/func_prog/exercise.en-pt.md)   |
| 🚧     | **Benchmarks**             | [Portuguese 🇧🇷](docs/benchmarks/README.pt-BR.md) / [English 🇺🇸](docs/benchmarks/README.en-US.md)   | [🇧🇷](docs/benchmarks/cheatcode.pt-BR.md)  | [🇧🇷 / 🇺🇸](docs/benchmarks/exercise.en-pt.md)  |
| 🚧     | **Exceptions**             | [Portuguese 🇧🇷](docs/exceptions/README.pt-BR.md) / [English 🇺🇸](docs/exceptions/README.en-US.md)   | [🇧🇷](docs/exceptions/cheatcode.pt-BR.md)  | [🇧🇷 / 🇺🇸](docs/exceptions/exercise.en-pt.md)  |

---

### 🤝 Contributing

Contributions are welcome!
If you want to help improve this project, feel free to fork it, create your branch, and open a pull request.

---

### 🐱‍👤 Author

**Ambrósia Andrade** 🇧🇷

[![GitHub][github-shield]][github-url] [![LinkedIn][linkedin-shield]][linkedin-url] [![Gmail][gmail-shield]][gmail-url] [![Instagram][instagram-shield]][instagram-url]

---

### 📝 License

This project is licensed under the **[MIT License](LICENSE)** — see the file for details.

---

> Made with ❤️ using Java. Ideal for learning or reference.

[![GitHub Stars](https://img.shields.io/github/stars/ambrosiaandrade/java-lab?style=social)](https://github.com/ambrosiaandrade/java-lab/stargazers)
[![Last Commit](https://img.shields.io/github/last-commit/ambrosiaandrade/java-lab?color=informational)](https://github.com/ambrosiaandrade/java-lab)

<!-- MARKDOWN LINKS & IMAGES -->

[java-shield]: https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=java&logoColor=white
[java-url]: https://www.oracle.com/java/
[i18n-shield]: https://img.shields.io/badge/Internationalization-i18n-blue?style=for-the-badge
[i18n-url]: https://en.wikipedia.org/wiki/Internationalization_and_localization
[gradle-shield]: https://img.shields.io/badge/Build%20with-Gradle-02303A.svg?style=for-the-badge&logo=gradle
[gradle-url]: https://gradle.org/

<!-- Social media -->

[instagram-shield]: https://img.shields.io/badge/-Instagram-E4405F?style=for-the-badge&logo=instagram&logoColor=white
[instagram-url]: https://www.instagram.com/ambrosia_andrade_br/
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=blue
[linkedin-url]: https://linkedin.com/in/ambrosiaandrade
[gmail-shield]: https://img.shields.io/badge/-Gmail-EA4335?style=for-the-badge&logo=gmail&logoColor=white
[gmail-url]: mailto:ambrosiaandrade.pe@gmail.com
[github-shield]: https://img.shields.io/badge/-GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white
[github-url]: https://github.com/ambrosiaandrade
