import os
import re

def to_pascal_case(text):
    return ''.join(word.capitalize() for word in re.split(r'[-_]', text))

def to_java_package(text):
    return text.replace('-', '_').lower()

def create_file(path, content=''):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as file:
        file.write(content)

def generate_java_lab(subject):
    class_name = to_pascal_case(subject)
    package_name = to_java_package(subject)
    base_path = f"{subject}"
    class_name = to_pascal_case(subject)

    # Java files
    create_file(f"{base_path}/Lab{class_name}ToDo.java", f"""\
package {package_name};

import config.CustomPrint;
import config.MessageProvider;

public class Lab{class_name}ToDo {{

    static {{
        MessageProvider.setModuleName("{class_name.lower()}");
        CustomPrint.greeting();
    }}

    public static void main(String[] args) {{

        CustomPrint.colored("exercise1");
        exercise1();
        // ...
    }}

    public static void exercise1() {{
    }}

    public static void exercise2() {{
    }}

    public static void exercise3() {{
    }}

    public static void exercise4() {{
    }}

    public static void exercise5() {{
    }}

    public static void exercise6() {{
    }}

    public static void exercise7() {{
    }}

    public static void exercise8() {{
    }}

    public static void exercise9() {{
    }}

    public static void exercise10() {{
    }}

    public static void exercise11() {{
    }}

    public static void exercise12() {{
    }}

    public static void exercise13() {{
    }}

    public static void exercise14() {{
    }}
}}
""")

    create_file(f"{base_path}/Lab{class_name}Done.java", f"""\
package {package_name};

import config.CustomPrint;
import config.MessageProvider;

public class Lab{class_name}Done {{

    static {{
        MessageProvider.setModuleName("{class_name.lower()}");
        CustomPrint.greeting();
    }}

    public static void main(String[] args) {{

        CustomPrint.colored("exercise1");
        exercise1();
        // ...
    }}

    public static void exercise1() {{
    }}

    public static void exercise2() {{
    }}

    public static void exercise3() {{
    }}

    public static void exercise4() {{
    }}

    public static void exercise5() {{
    }}

    public static void exercise6() {{
    }}

    public static void exercise7() {{
    }}

    public static void exercise8() {{
    }}

    public static void exercise9() {{
    }}

    public static void exercise10() {{
    }}

    public static void exercise11() {{
    }}

    public static void exercise12() {{
    }}

    public static void exercise13() {{
    }}

    public static void exercise14() {{
    }}
}}
""")

    # Markdown files
    create_file(f"{base_path}/cheatcode.pt-BR.md", "# 💡 Cheatsheet - pt-BR\n\n> Dicas e atalhos úteis sobre o tema.")
    create_file(f"{base_path}/exercise.en-pt.md", "# 🧪 Exercises\n\n> Instructions in English + Português")
    create_file(f"{base_path}/README.pt-BR.md", f"# 📘 {class_name}\n\n> Descrição em português sobre `{subject}`.")
    create_file(f"{base_path}/README.en-US.md", f"# 📗 {class_name}\n\n> English description about `{subject}`.")

    # i18n folder and files
    i18n_path = f"{base_path}/i18n"
    create_file(f"{i18n_path}/messages_en_US.properties", "exercise1=Filter people older than 30")
    create_file(f"{i18n_path}/messages_pt_BR.properties", "exercise1=Filtrar pessoas com idade maior que 30")

    print(f"✅ Estrutura criada para: {subject}")


if __name__ == "__main__":
    import sys

    if len(sys.argv) != 2:
        print("❌ Use: python generator.py <nome_do_assunto>")
    else:
        subject = sys.argv[1].lower()
        generate_java_lab(subject)
